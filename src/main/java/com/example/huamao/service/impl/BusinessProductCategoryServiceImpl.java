package com.example.huamao.service.impl;

import com.example.huamao.db.BusinessProductCategoryRepository;
import com.example.huamao.po.BusinessProductCategory;
import com.example.huamao.pojo.business.ViewBusinessProductCategory;
import com.example.huamao.service.BusinessProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 入住商家商品分类服务
 * @author toby tobytb@163.com
 * @date 2018/5/31 20:08
 */
@Service
public class BusinessProductCategoryServiceImpl implements BusinessProductCategoryService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessProductCategoryServiceImpl.class);
    private BusinessProductCategoryRepository businessProductCategoryRepository;

    @Autowired
    public BusinessProductCategoryServiceImpl(BusinessProductCategoryRepository businessProductCategoryRepository) {
        this.businessProductCategoryRepository = businessProductCategoryRepository;
    }

    /**
     * 由视图层ViewBusinessProductCategory商家商品分类转换为数据库BusinessProductCategory商家商品分类信息
     * @param viewBusinessProductCategory 视图层ViewBusinessProductCategory商家商品分类
     * @return 数据库BusinessProductCategory商家商品分类
     */
    private BusinessProductCategory getBusinessProductCategoryFromViewBusinessProductCategory (ViewBusinessProductCategory viewBusinessProductCategory) {
        BusinessProductCategory businessProductCategory = new BusinessProductCategory();
        businessProductCategory.setName(viewBusinessProductCategory.getName());
        businessProductCategory.setDescription(viewBusinessProductCategory.getDescription());
        businessProductCategory.setBusinessId(viewBusinessProductCategory.getBusinessId());
        businessProductCategory.setBusinessName(viewBusinessProductCategory.getBusinessName());
        businessProductCategory.setParamTemplates(viewBusinessProductCategory.getParamTemplate());
        // 设置分类
        businessProductCategory.setParent(false); // 默认为false 没有子分类
        List<String>  parentAndAncestors = viewBusinessProductCategory.getParent();
        if(parentAndAncestors != null && parentAndAncestors.size() > 0) {
            // 存在父分类和祖先分类
            List<Map<String, String>> parentAndAncestorsIdAndNameMapList = new ArrayList<>();
            for(String rawCategoryIdAndName: parentAndAncestors) {
                String[] parentAndAncestorsIdAndNameArr = rawCategoryIdAndName.split("\\|");
                if(parentAndAncestorsIdAndNameArr.length != 2) {
                    logger.error("入住商家添加自己商品分类时候，视图传来的父分类数据格式不正确");
                    throw new RuntimeException("入住商家添加自己商品分类时候，视图传来的父分类数据格式不正确");
                } else {
                    Map<String, String> parentAndAncestorsMap = new HashMap<>();
                    parentAndAncestorsMap.put("id", parentAndAncestorsIdAndNameArr[0]);
                    parentAndAncestorsMap.put("name", parentAndAncestorsIdAndNameArr[1]);
                    // 设置祖先分类和直接父分类含有子分类
                    this.setBusinessProductCategoryIsParent(parentAndAncestorsIdAndNameArr[0]);
                    parentAndAncestorsIdAndNameMapList.add(parentAndAncestorsMap);
                }
            }
            businessProductCategory.setAncestors(parentAndAncestorsIdAndNameMapList);
            Map<String, String> dirParentIdAndNameMap = businessProductCategory.getAncestors().get(businessProductCategory.getAncestors().size()-1); // 最后一个为直接父分类
            businessProductCategory.setParentId(dirParentIdAndNameMap.get("id"));
            businessProductCategory.setParentName(dirParentIdAndNameMap.get("name"));
        }
        return  businessProductCategory;
    }

    /**
     * 根据分类id 设置分类含有子分类
     * @param cid 分类id
     */
    private void setBusinessProductCategoryIsParent(String cid) {
        BusinessProductCategory businessProductCategory = this.businessProductCategoryRepository.findOne(cid);
        if(!businessProductCategory.isParent()) {
            businessProductCategory.setParent(true);
            this.businessProductCategoryRepository.save(businessProductCategory);
        }
    }
    /**
     * 添加入住商家商品新分类
     *
     * @param viewBusinessProductCategory 视图层传来的入住商家商品分类信息
     * @return BusinessProductCategory 保存到数据库的入住商家商品新分类
     */
    @Override
    public BusinessProductCategory addNewCategory(ViewBusinessProductCategory viewBusinessProductCategory) {
        BusinessProductCategory businessProductCategory =
                this.getBusinessProductCategoryFromViewBusinessProductCategory(viewBusinessProductCategory);
        this.businessProductCategoryRepository.save(businessProductCategory);
        logger.info("保存到数据的入住商家商品新分类信息：" + businessProductCategory);
        return businessProductCategory;
    }

    /**
     * 获取入住商家自家商品分类信息
     * [{
     * value: '5a5e07d772cb620e64ef485f|食品', // ObjectId
     * label: '食品',
     * children: [
     * {
     * value: '5a5e07d772cb620e64ef487k|新鲜水果',
     * label: '新鲜水果',
     * children: [
     * {
     * value: '5a5e07d772cb620e64ef488e|苹果',
     * label: '苹果',
     * children: [
     * {
     * value: '5a5e07d772cb620e64ef483b|红富士',
     * label: '红富士'
     * },
     * {
     * value: '5a5e07d772cb620e64ef483a|青苹果',
     * label: '青苹果'
     * }
     * ]
     * }
     * ]
     * }
     * ]
     * }]
     * 备注：“|”为英文状态下"|"
     *
     * @param businessId 入住商家id 分类所属商家id MongoDB ObjectId
     * @return 商家自家商品分类信息
     */
    @Override
    public List<Map<String, Object>> getAllCategoryByBusinessId(String businessId) {
        // parentId = null 从顶层分类开始
        return this.getBusinessGoodsCategoryItemByBusinessIdAndParentId(businessId, null);
    }

    /**
     * 递归方法，通过商家id 和分类id 从某个商家商品分类的顶层分类开始遍历该商家所有分类信息，并递归调用自身获取子分类
     * @param businessId 分类所属商家id MongoDB ObjectId
     * @param parentId 分类id MongoDB ObjectId
     * @return 响应给客户端的数据
    [{
    value: '5a5e07d772cb620e64ef485f|食品', // ObjectId
    label: '食品',
    children: [
    {
    value: '5a5e07d772cb620e64ef487k|新鲜水果',
    label: '新鲜水果',
    children: [
    {
    value: '5a5e07d772cb620e64ef488e|苹果',
    label: '苹果',
    children: [
    {
    value: '5a5e07d772cb620e64ef483b|红富士',
    label: '红富士'
    },
    {
    value: '5a5e07d772cb620e64ef483a|青苹果',
    label: '青苹果'
    }
    ]
    }
    ]
    }
    ]
    }]
    备注：“|”为英文状态下"|"
     */
    private List<Map<String, Object>> getBusinessGoodsCategoryItemByBusinessIdAndParentId(String businessId, String parentId) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        List<BusinessProductCategory> businessProductCategoryList =
                this.businessProductCategoryRepository.findAllByBusinessIdAndParentId(businessId, parentId);
        for(BusinessProductCategory businessProductCategory: businessProductCategoryList) {
            Map<String, Object> resultNode = new HashMap<>();
            resultNode.put("value", businessProductCategory.getId() + "|" + businessProductCategory.getName()); // 英文 |
            resultNode.put("label", businessProductCategory.getName());
            // 设置子分类
            if(null == businessProductCategory.getParentId()) {
                // 某商家商品顶层分类
                resultNode.put("children",
                        this.getBusinessGoodsCategoryItemByBusinessIdAndParentId(businessId, businessProductCategory.getId()));
            } else {
                // 不是某个商家的顶层分类
                if(businessProductCategory.isParent()) {
                    // 含有子分类
                    resultNode.put("children",
                            this.getBusinessGoodsCategoryItemByBusinessIdAndParentId(businessId, businessProductCategory.getId()));
                }
            }
            resultList.add(resultNode);
        }
        return resultList;
    }
}
