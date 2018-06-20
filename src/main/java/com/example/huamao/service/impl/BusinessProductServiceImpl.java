package com.example.huamao.service.impl;

import com.example.huamao.common.util.IDUtils;
import com.example.huamao.db.BusinessProductRepository;
import com.example.huamao.po.BusinessProduct;
import com.example.huamao.pojo.business.ViewBusinessGoods;
import com.example.huamao.service.BusinessProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/1 20:16
 */
@Service
public class BusinessProductServiceImpl implements BusinessProductService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessProductServiceImpl.class);
    private BusinessProductRepository businessProductRepository;

    @Autowired
    public BusinessProductServiceImpl(BusinessProductRepository businessProductRepository) {
        this.businessProductRepository = businessProductRepository;
    }

    /**
     * 根据客户端传来的商品信息构成数据库相应结构后保存
     *
     * @param viewBusinessGoods 客户端传来的商品信息
     * @return 保存到数据库中商家商品数据
     */
    @Override
    public BusinessProduct saveProduct(ViewBusinessGoods viewBusinessGoods) {
        BusinessProduct businessProduct = new BusinessProduct();
        businessProduct.setBusinessId(viewBusinessGoods.getBusinessId());
        businessProduct.setBusinessName(viewBusinessGoods.getBusinessName());
        businessProduct.setName(viewBusinessGoods.getName());
        businessProduct.setDescription(viewBusinessGoods.getDescription());
        businessProduct.setStock(viewBusinessGoods.getStock());
        businessProduct.setSku(IDUtils.genBusinessGoodsSku());
        businessProduct.setPricing(viewBusinessGoods.getPricing());
        businessProduct.setDetails(viewBusinessGoods.getDetails());

        // 设置商品分类
//        List<String> categoryList = viewBusinessGoods.getCategory();// 包含所有分类的id和name
//        // 设置祖先分类，不含直接分类
//        List<Map<String, String>> categoryAncestors = new ArrayList<>();
//        for (int i = 0; i < categoryList.size() -1; i++) {
//            String[] categoryIdAndNameArr = categoryList.get(i).split("\\|"); // 数据格式  5b10288adef074e5b86fec8a|男装
//            if(2 != categoryIdAndNameArr.length) {
//                // 分类格式不正确
//                logger.error("视图层传来的分类格式不正确");
//                throw new RuntimeException("视图层传来的分类格式不正确");
//            } else {
//                Map<String, String> categoryAncestor = new HashMap<>();
//                categoryAncestor.put("id", categoryIdAndNameArr[0]);
//                categoryAncestor.put("name", categoryIdAndNameArr[1]);
//                categoryAncestors.add(categoryAncestor);
//            }
//        }
//        businessProduct.setCategoryAncestors(categoryAncestors);
//        // 设置直接分类
//        Map<String, String> dirCategoryIdAndNameMap = new HashMap<>();
//        String[] categoryIdAndNameArr = categoryList.get(categoryList.size() - 1).split("\\|");
//        if(2 != categoryIdAndNameArr.length) {
//            // 分类格式不正确
//            logger.error("视图层传来的分类格式不正确");
//            throw new RuntimeException("视图层传来的分类格式不正确");
//        } else {
//            dirCategoryIdAndNameMap.put("id", categoryIdAndNameArr[0]);
//            dirCategoryIdAndNameMap.put("name", categoryIdAndNameArr[1]);
//            businessProduct.setCategory(dirCategoryIdAndNameMap);
//        }
        // 设置商品分类
        this.changeCategoryFromView(businessProduct, viewBusinessGoods.getCategory());
        this.businessProductRepository.save(businessProduct);
        logger.info("保存到数据库的商家商品信息：" + businessProduct);
        return businessProduct;
    }

    /**
     * 从视图中转换商品分类
     * @param businessProduct 保存到数据库的商家商品
     * @param categoryList 包含所有分类的id和name id|name
     */
    private void changeCategoryFromView(BusinessProduct businessProduct, List<String> categoryList) {
        // 设置商品分类
        // 设置祖先分类，不含直接分类
        List<Map<String, String>> categoryAncestors = new ArrayList<>();
        for (int i = 0; i < categoryList.size() -1; i++) {
            String[] categoryIdAndNameArr = categoryList.get(i).split("\\|"); // 数据格式  5b10288adef074e5b86fec8a|男装
            if(2 != categoryIdAndNameArr.length) {
                // 分类格式不正确
                logger.error("视图层传来的分类格式不正确");
                throw new RuntimeException("视图层传来的分类格式不正确");
            } else {
                Map<String, String> categoryAncestor = new HashMap<>();
                categoryAncestor.put("id", categoryIdAndNameArr[0]);
                categoryAncestor.put("name", categoryIdAndNameArr[1]);
                categoryAncestors.add(categoryAncestor);
            }
        }
        businessProduct.setCategoryAncestors(categoryAncestors);
        // 设置直接分类
        Map<String, String> dirCategoryIdAndNameMap = new HashMap<>();
        String[] categoryIdAndNameArr = categoryList.get(categoryList.size() - 1).split("\\|");
        if(2 != categoryIdAndNameArr.length) {
            // 分类格式不正确
            logger.error("视图层传来的分类格式不正确");
            throw new RuntimeException("视图层传来的分类格式不正确");
        } else {
            dirCategoryIdAndNameMap.put("id", categoryIdAndNameArr[0]);
            dirCategoryIdAndNameMap.put("name", categoryIdAndNameArr[1]);
            businessProduct.setCategory(dirCategoryIdAndNameMap);
        }
    }

    /**
     * 根据客户端传来的商品信息更新商品信息
     *
     * @param viewBusinessGoods 客户端传来的商品信息 包含商品id
     * @return 保存到数据库中商家商品数据
     */
    @Override
    public BusinessProduct updateProduct(ViewBusinessGoods viewBusinessGoods) {
        String businessGoodsId = viewBusinessGoods.getId();
        BusinessProduct businessProduct = this.businessProductRepository.findOne(businessGoodsId);
        businessProduct.setName(viewBusinessGoods.getName());
        businessProduct.setDescription(viewBusinessGoods.getDescription());
        List<String> rawCatList = viewBusinessGoods.getCategory();
        this.changeCategoryFromView(businessProduct, rawCatList);
        this.businessProductRepository.save(businessProduct);
        logger.info("保存到数据库的商家商品信息：" + businessProduct);
        return businessProduct;
    }

    /**
     * 通过分页查找所有商品
     *
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(String businessId, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        BusinessProduct businessProduct = new BusinessProduct();
        businessProduct.setBusinessId(businessId);

        ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("businessId", match -> match.equals(businessId));

        Example<BusinessProduct> businessProductExample = Example.of(businessProduct, matcher);
        Page<BusinessProduct> businessProductPage = this.businessProductRepository.findAll(businessProductExample, pageable);
        result.put("totalElements", businessProductPage.getTotalElements());
        result.put("totalPages", businessProductPage.getTotalPages());
        result.put("content", this.PoBusinessProductListToViewBusinessGoodsListNodeList(businessProductPage.getContent()));
        logger.info("在数据库中分页显示入住商家所有商品信息返回给视图数据：" + result);
        return result;
    }

    /**
     * 通过商品名称模糊查询，分页展示
     * @param name       商品名称
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByNameAndPage(String name, String businessId, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        BusinessProduct businessProduct = new BusinessProduct();
        businessProduct.setBusinessId(businessId);
        businessProduct.setName(name);

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", match -> match.ignoreCase().contains())
                .withMatcher("businessId", match -> match.equals(businessId));

        Example<BusinessProduct> businessProductExample = Example.of(businessProduct, matcher);
        Page<BusinessProduct> businessProductPage = this.businessProductRepository.findAll(businessProductExample, pageable);
        result.put("totalElements", businessProductPage.getTotalElements());
        result.put("totalPages", businessProductPage.getTotalPages());
        result.put("content", this.PoBusinessProductListToViewBusinessGoodsListNodeList(businessProductPage.getContent()));
        logger.info("在数据库中分页显示入住商家所有商品信息返回给视图数据：" + result);
        return result;
    }

    /**
     * 通过商品id mongoDB ObjectId 删除商品
     *
     * @param id 商品id mongoDB ObjectId
     * @return 删除商品信息
     */
    @Override
    public BusinessProduct deleteById(String id) {
        BusinessProduct businessProduct = this.businessProductRepository.findOne(id);
        this.businessProductRepository.delete(id);
        logger.info("删除商品信息：" + businessProduct);
        return businessProduct;
    }

    /**
     * 通过唯一的 商品sku查找商品信息
     *
     * @param sku 商品sku
     * @return BusinessProduct
     */
    @Override
    public BusinessProduct findBySku(String sku) {
        return this.businessProductRepository.findBySku(sku);
    }

    /**
     * 将数据库中 商家商品列表转换为展示商家商品的列表
     * @param businessProductList 数据库中 商家商品列表
     * @return List<Map<String, Object>>
     [
    {
    id: '6a5e07d772cb620e64ef48ee',
    sku: '9901',
    name: '宏辉果蔬 山东烟台红富士苹果 12个 经典80号 约2.6kg 自营水果',
    description: '宏辉果蔬 山东烟台红富士苹果 12个 经典80号 约2.6kg 自营水果',
    pricing: {
    retail: 5990,
    sale: 2470
    },
    category: ['食品', '新鲜水果', '苹果', '红富士'],
    tags: ['京东团队', '产地直采', '脆甜多汁', '苹果', '红富士'],
    thumbs: ['thumb_default.jpg'],
    imgs: []
    }
    ]
     */
    private List<Map<String, Object>> PoBusinessProductListToViewBusinessGoodsListNodeList(List<BusinessProduct> businessProductList) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(BusinessProduct businessProduct: businessProductList) {
            Map<String, Object> resultNode = new HashMap<>();
            resultNode.put("id", businessProduct.getId());
            resultNode.put("sku", businessProduct.getSku());
            resultNode.put("name", businessProduct.getName());
            resultNode.put("description", businessProduct.getDescription());
            resultNode.put("stock", businessProduct.getStock());
            resultNode.put("pricing", businessProduct.getPricing());
            resultNode.put("details", businessProduct.getDetails()); // 规格详情
            // 设置分类
            List<String> catList = new ArrayList<>();
            List<String> catIdAndNameList = new ArrayList<>();
            //祖先分类
            List<Map<String, String>> ancestorsCatList =  businessProduct.getCategoryAncestors();
            for(Map<String, String> ancestorsCat: ancestorsCatList) {
                catList.add(ancestorsCat.get("name"));
                catIdAndNameList.add(ancestorsCat.get("id") + "|" + ancestorsCat.get("name")); // 5a5e07d772cb620e64ef485f|男装 英文的|
            }
            // 直接父分类
            Map<String, String> dirCat = businessProduct.getCategory();
            catList.add(dirCat.get("name"));
            catIdAndNameList.add(dirCat.get("id") + "|" + dirCat.get("name"));// 5a5e07d772cb620e64ef485f|T恤 英文的|

            resultNode.put("category", catList);
            resultNode.put("categoryIdAndName", catIdAndNameList);
            resultNode.put("tags", businessProduct.getTags());
            resultNode.put("thumbs", businessProduct.getThumbs());
            resultNode.put("imgs", businessProduct.getImgs());
            resultList.add(resultNode);
        }
        return  resultList;
    }
}
