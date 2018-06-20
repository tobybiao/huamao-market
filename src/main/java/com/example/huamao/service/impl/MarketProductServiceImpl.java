package com.example.huamao.service.impl;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.common.util.IDUtils;
import com.example.huamao.db.MarketProductRepository;
import com.example.huamao.po.MarketCashier;
import com.example.huamao.po.MarketProduct;
import com.example.huamao.pojo.VMarketGoods;
import com.example.huamao.pojo.VMarketGoodsListNode;
import com.example.huamao.service.MarketProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**商场商品服务
 * @author toby tobytb@163.com
 * @date 2018/5/23 8:43
 */
@Service
public class MarketProductServiceImpl implements MarketProductService {
    private static final Logger logger = LoggerFactory.getLogger(MarketProductServiceImpl.class);
    private MarketProductRepository marketProductRepository;

    @Autowired
    public MarketProductServiceImpl(MarketProductRepository marketProductRepository) {
        this.marketProductRepository = marketProductRepository;
    }

    /**
     * 根据客户端传来的商品信息构成数据库相应结构后保存
     *
     * @param marketGoods 客户端传来的商品信息
     * @return 包含商品信息的响应结果
     */
    @Override
    public GenericResult saveProduct(VMarketGoods marketGoods) {
        MarketProduct marketProduct = new MarketProduct();
        marketProduct.setName(marketGoods.getName());
        marketProduct.setDescription(marketGoods.getDescription());
        marketProduct.setStock(marketGoods.getStock());
        marketProduct.setSku(IDUtils.genMarketGoodsSku());
        marketProduct.setPricing(marketGoods.getPricing());
        marketProduct.setDetails(marketGoods.getDetails());
        List<String> categoryIdsAndNames = marketGoods.getCategoryIds();
        // 设置祖先分类，不含直接分类
        List<Map<String, String>> categoryAncestors = new ArrayList<>();
        for (int i = 0; i < categoryIdsAndNames.size() - 1; i++) {
            String[] categoryIdAndName = categoryIdsAndNames.get(i).split(",");
            Map<String, String> categoryAncestor = new HashMap<>();
            categoryAncestor.put("id", categoryIdAndName[0]);
            categoryAncestor.put("name", categoryIdAndName[1]);
            categoryAncestors.add(categoryAncestor);
        }
        marketProduct.setCategoryAncestors(categoryAncestors);
        // 设置直接分类
        Map<String, String> categoryIdAndNameMap = new HashMap<>();
        String[] categoryIdAndName = categoryIdsAndNames.get(categoryIdsAndNames.size() - 1).split(",");
        categoryIdAndNameMap.put("id", categoryIdAndName[0]);
        categoryIdAndNameMap.put("name", categoryIdAndName[1]);
        marketProduct.setCategory(categoryIdAndNameMap);
        logger.info("将要保存的商场商品信息：{}", marketProduct);
        return GenericResult.ok(this.marketProductRepository.save(marketProduct));
    }

    /**
     * 查找所有商品
     *
     * @return List<MarketProduct>
     */
    @Override
    public List<VMarketGoodsListNode> findAll() {
        List<MarketProduct> dbMarketProductList = this.marketProductRepository.findAll();
        List<VMarketGoodsListNode> vMarketGoodsList = this.MarketProductListToVMarketGoodsList(dbMarketProductList);
        logger.info("所有商品信息：" + vMarketGoodsList);
        return vMarketGoodsList;
    }

    /**
     * 数据库MarketProduct　列表转换成视图 商场商品列表 商品项信息 VMarketGoodsListNode 格式
     * @param marketProductList　 数据库MarketProduct列表
     * @return List<VMarketGoodsListNode>
     */
    private List<VMarketGoodsListNode> MarketProductListToVMarketGoodsList(List<MarketProduct> marketProductList) {
        List<VMarketGoodsListNode> vMarketGoodsList = new ArrayList<>();
        for (MarketProduct marketProduct: marketProductList) {
            VMarketGoodsListNode marketGoodsListNode = new VMarketGoodsListNode();
            marketGoodsListNode.setId(marketProduct.getId());
            // 设置分类
            // 商品直接父分类
            marketGoodsListNode.setPrimaryCategory(marketProduct.getCategory());
            // 祖先分类，不含直接分类
            marketGoodsListNode.setCategoryAncestors(marketProduct.getCategoryAncestors());
            List<String> categoryIds = new ArrayList<>();
            List<String> categoryNameList = new ArrayList<>();
            for(Map<String, String> categoryIdAndNameMap: marketGoodsListNode.getCategoryAncestors()) {
                categoryIds.add(categoryIdAndNameMap.get("id"));
                categoryNameList.add(categoryIdAndNameMap.get("name"));
            }
            categoryIds.add(marketGoodsListNode.getPrimaryCategory().get("id"));
            categoryNameList.add(marketGoodsListNode.getPrimaryCategory().get("name"));
            marketGoodsListNode.setCategory(categoryIds);
            marketGoodsListNode.setCategoryLabel(categoryNameList);

            marketGoodsListNode.setName(marketProduct.getName());
            marketGoodsListNode.setDescription(marketProduct.getDescription());
            marketGoodsListNode.setStock(marketProduct.getStock());
            marketGoodsListNode.setSku(marketProduct.getSku());
            marketGoodsListNode.setPricing(marketProduct.getPricing());
            marketGoodsListNode.setImgs(marketProduct.getImgs());
            marketGoodsListNode.setTags(marketProduct.getTags());
            marketGoodsListNode.setThumbs(marketProduct.getThumbs());
            vMarketGoodsList.add(marketGoodsListNode);
        }
        return vMarketGoodsList;
    }
    /**
     * 通过分页查找所有商品
     *
     * @param pageIndex 页码
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        if(pageIndex < 0 || pageSize <= 0 ) {
            result.put("totalElements", 0);
            result.put("totalPages", 0);
            result.put("content", null);
            return result;
        }
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<MarketProduct> marketProuctPage =  this.marketProductRepository.findAll(pageable);
        result.put("totalElements", marketProuctPage.getTotalElements());
        result.put("totalPages", marketProuctPage.getTotalPages());
        // 转换成视图接受的格式
        result.put("content", this.MarketProductListToVMarketGoodsList(marketProuctPage.getContent()));
        return result;
    }

    /**
     * 通过商品名称模糊查询，分页展示
     *
     * @param name      商品名称
     * @param pageIndex 页码
     * @param pageSize  每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByNameAndPage(String name, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);

        MarketProduct marketProduct = new MarketProduct();
        marketProduct.setName(name);
        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.ignoreCase().contains());
        Example<MarketProduct> example = Example.of(marketProduct, matcher);

        Page<MarketProduct> marketProductPage = this.marketProductRepository.findAll(example, pageable);
        result.put("totalElements", marketProductPage.getTotalElements());
        result.put("totalPages", marketProductPage.getTotalPages());
        result.put("content", this.MarketProductListToVMarketGoodsList(marketProductPage.getContent()));
        logger.info("在数据库中通过指定商品名称分页查找商品返回数据：" + result);
        return result;
    }

}
