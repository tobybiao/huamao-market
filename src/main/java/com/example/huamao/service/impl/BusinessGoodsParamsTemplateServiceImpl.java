package com.example.huamao.service.impl;

import com.example.huamao.db.BusinessProductCategoryRepository;
import com.example.huamao.po.BusinessProductCategory;
import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.service.BusinessGoodsParamsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/1 11:58
 */
@Service
public class BusinessGoodsParamsTemplateServiceImpl implements BusinessGoodsParamsTemplateService {
    private BusinessProductCategoryRepository businessProductCategoryRepository;

    @Autowired
    public BusinessGoodsParamsTemplateServiceImpl(BusinessProductCategoryRepository businessProductCategoryRepository) {
        this.businessProductCategoryRepository = businessProductCategoryRepository;
    }

    /**
     * 根据商品分类id 获取商品规格参数模板信息
     *
     * @param categoryId 商品分类id
     * @return List<ParamTemplateNode> 商品规格参数模板信息列表
     */
    @Override
    public List<ParamTemplateNode> getInformationByCategoryId(String categoryId) {
        List<ParamTemplateNode> resultList = new ArrayList<>();
        BusinessProductCategory businessProductCategory = this.businessProductCategoryRepository.findOne(categoryId);
        List<Map<String, Object>> paramTemplateMaps = businessProductCategory.getParamTemplates();
        for(Map<String, Object> paramTemplateMap: paramTemplateMaps) { // 规格分组
            ParamTemplateNode node = new ParamTemplateNode();
            node.setGroup((String) paramTemplateMap.get("group"));
            List<String> params = (List<String>) paramTemplateMap.get("params");
            List<Map<String, String>> paramKeyAndValueList = new ArrayList<>();
            for(String param: params) { // 分组下规格项
                Map<String, String> paramKeyAndValue = new HashMap<>();
                paramKeyAndValue.put("key", param);
                paramKeyAndValue.put("value", "");
                paramKeyAndValueList.add(paramKeyAndValue);
            }
            node.setParams(paramKeyAndValueList);
            resultList.add(node);
        }
        return resultList;
    }
}
