package com.example.huamao.service;

import com.example.huamao.po.BusinessProductCategory;
import com.example.huamao.pojo.business.ViewBusinessProductCategory;

import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/31 20:04
 */
public interface BusinessProductCategoryService {
    /**
     * 添加入住商家商品新分类
     * @param viewBusinessProductCategory 视图层传来的入住商家商品分类信息
     * @return BusinessProductCategory 保存到数据库的入住商家商品新分类
     */
    BusinessProductCategory addNewCategory(ViewBusinessProductCategory viewBusinessProductCategory);

    /**
     * 获取入住商家自家商品分类信息
     * [{
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
     *@param businessId 分类所属商家id MongoDB ObjectId
     * @return 商家自家商品分类信息
     */
    List<Map<String,Object>> getAllCategoryByBusinessId(String businessId);
}
