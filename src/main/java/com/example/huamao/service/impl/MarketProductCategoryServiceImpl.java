package com.example.huamao.service.impl;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.db.MarketProductCategoryRepository;
import com.example.huamao.po.MarketProductCategory;
import com.example.huamao.pojo.MarketGoodsCategoryTreeNode;
import com.example.huamao.pojo.MarketGoodsNewCategory;
import com.example.huamao.service.MarketProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 商品商品分类服务实现类
 * @author toby tobytb@163.com
 * @date 2018/5/17 11:16
 */
@Service
public class MarketProductCategoryServiceImpl implements MarketProductCategoryService {
    private MarketProductCategoryRepository marketProductCategoryRepository;
    private static final Logger logger = LoggerFactory.getLogger(MarketProductCategoryServiceImpl.class);
    @Autowired
    public MarketProductCategoryServiceImpl(MarketProductCategoryRepository marketProductCategoryRepository) {
        this.marketProductCategoryRepository = marketProductCategoryRepository;
    }

    /**
     * 添加商场商品新分类
     *
     * @param newCategory 封装新分类的信息
     * @return 包含添加新分类的响应信息
     */
    @Override
    public GenericResult addNewCategory(MarketGoodsNewCategory newCategory) {
        MarketProductCategory productCategory = new MarketProductCategory();
        productCategory.setName(newCategory.getName());
        productCategory.setDescription(newCategory.getDescription());
        productCategory.setParamTemplates(newCategory.getParam_templates());
        productCategory.setParent(false); // 新添加的分类信息默认没有子分类
        // 设置祖先分类
        List<String> parent_ids = newCategory.getParent_ids();
        logger.info("祖先分类信息：" + parent_ids);
        if(null != parent_ids && parent_ids.size() > 0) {
            ArrayList<Map<String, String>> ancestors = new ArrayList<>();
            for (String parent_id : parent_ids) { // 需要保证有序以体现层级分类结构，数据库中第一个元素是顶级分类，往下是子分类
                Map<String, String> map = new HashMap<String, String>();
                String[] parentIdAndName = parent_id.split(","); // 数据格式：5a5e07d772cb620e64ef485f,食品
                map.put("name", parentIdAndName[1]);
                map.put("id", parentIdAndName[0]);
                this.setMarketProductCategoryIsParent(parentIdAndName[0]); // 设置祖先分类含有子分类
                ancestors.add(map);
            }
            // 设置直接父分类
            String[] directParentIdAndName = parent_ids.get(parent_ids.size() - 1).split(","); // 数据格式：5a5e07d772cb620e64ef485f,食品
            productCategory.setParentId(directParentIdAndName[0]);
            productCategory.setParentName(directParentIdAndName[1]);
            productCategory.setAncestors(ancestors);
        }
        this.marketProductCategoryRepository.save(productCategory);
        logger.info("添加新分类为：" + productCategory);
        return GenericResult.ok(productCategory);
    }

    /**
     * 根据分类id 设置分类含有子分类
     * @param cid 分类id
     */
    private void setMarketProductCategoryIsParent(String cid) {
        MarketProductCategory marketProductCategory = this.marketProductCategoryRepository.findOne(cid);
        if(!marketProductCategory.isParent()) {
            marketProductCategory.setParent(true);
            this.marketProductCategoryRepository.save(marketProductCategory);
        }
    }
    /**
     * 获取所有商场商品分类数据
     *
     * @return 响应包含分类数据
     */
    @Override
    public GenericResult getAllCategory() {
        // [{
        //     value: '5a5e07d772cb620e64ef485f,食品', // ObjectId
        //     label: '食品',
        //     children: [
        //         {
        //             value: '5a5e07d772cb620e64ef487k,新鲜水果',
        //             label: '新鲜水果',
        //             children: [
        //                 {
        //                     value: '5a5e07d772cb620e64ef488e,苹果',
        //                     label: '苹果',
        //                     children: [
        //                         {
        //                             value: '5a5e07d772cb620e64ef483b,红富士',
        //                             label: '红富士'
        //                         },
        //                         {
        //                             value: '5a5e07d772cb620e64ef483a,青苹果',
        //                             label: '青苹果'
        //                         }
        //                     ]
        //                 }
        //             ]
        //         }
        //     ]
        // }]
        List<MarketGoodsCategoryTreeNode> marketGoodsCategoryTreeNodeList = this.getMarketProductCategoryItemByParentId(null);
        return  GenericResult.ok(marketGoodsCategoryTreeNodeList);
    }

    private List<MarketGoodsCategoryTreeNode> getMarketProductCategoryItemByParentId(String parentId) {
        List<MarketProductCategory> marketProductCategoryList = this.marketProductCategoryRepository.findAllByParentId(parentId);
        List<MarketGoodsCategoryTreeNode> resultList = new ArrayList<>();
        for (MarketProductCategory item: marketProductCategoryList) {
            MarketGoodsCategoryTreeNode node = new MarketGoodsCategoryTreeNode();
            node.setValue(item.getId() + "," + item.getName());
            node.setLabel(item.getName());
            // 不是顶层分类
            if(item.getParentId() != null) {
                if(item.isParent()) { // 含有子分类
                    node.setChildren(this.getMarketProductCategoryItemByParentId(item.getId()));
                }
            } else {
                // 顶层分类
                node.setChildren(this.getMarketProductCategoryItemByParentId(item.getId())); // 递归调用
            }
            resultList.add(node);
        }
        return resultList;
    }
}
