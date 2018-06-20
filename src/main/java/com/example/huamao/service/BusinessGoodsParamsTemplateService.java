package com.example.huamao.service;

import com.example.huamao.pojo.ParamTemplateNode;

import java.util.List;

/** 入住商家商品规格参数模板服务
 * @author toby tobytb@163.com
 * @date 2018/6/1 11:56
 */
public interface BusinessGoodsParamsTemplateService {
    /**
     * 根据商品分类id 获取商品规格参数模板信息
     * @param categoryId 商品分类id
     * @return List<ParamTemplateNode> 商品规格参数模板信息列表
     */
    List<ParamTemplateNode> getInformationByCategoryId(String categoryId);
}
