package com.example.huamao.service.impl;

import com.example.huamao.db.MarketProductCategoryRepository;
import com.example.huamao.po.MarketProductCategory;
import com.example.huamao.pojo.ParamTemplate;
import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.service.MarketGoodsParamsTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 商场商品规格参数模板服务实现类
 * @author toby tobytb@163.com
 * @date 2018/5/20 0:47
 */
@Service
public class MarketGoodsParamsTemplateServiceImpl implements MarketGoodsParamsTemplateService {
    private MarketProductCategoryRepository marketProductCategoryRepository;

    @Autowired
    public MarketGoodsParamsTemplateServiceImpl(MarketProductCategoryRepository marketProductCategoryRepository) {
        this.marketProductCategoryRepository = marketProductCategoryRepository;
    }

    /**
     * 根据商品分类id 获取商品规格参数模板信息
     *
     * @param categoryId 商品分类id
     * @return List<ParamTemplateNode> 商品规格参数模板信息列表
     */
    @Override
    public List<ParamTemplateNode> getInformationByCategoryId(String categoryId) {
//        List<ParamTemplateNode> resultList = new ArrayList<>();
//
//        ParamTemplateNode node = new ParamTemplateNode();
//        node.setGroup("主体");
//        List<Map<String, String>> paramKeyAndValueList = new ArrayList<>();
//        Map<String, String> paramKeyAndValue = new HashMap<>();
//        paramKeyAndValue.put("key", "保质期");
//        paramKeyAndValue.put("value", "");
//        paramKeyAndValueList.add(paramKeyAndValue);
//        Map<String, String> paramKeyAndValue2 = new HashMap<>();
//        paramKeyAndValue2.put("key", "净含量");
//        paramKeyAndValue2.put("value", "2.6kg");
//        paramKeyAndValueList.add(paramKeyAndValue2);
//        node.setParams(paramKeyAndValueList);
//        resultList.add(node);
//
//        ParamTemplateNode node2 = new ParamTemplateNode();
//        node2.setGroup("特色功能");
//        List<Map<String, String>> paramKeyAndValueList2 = new ArrayList<>();
//        Map<String, String> paramKeyAndValue22 = new HashMap<>();
//        paramKeyAndValue22.put("key", "保质期2");
//        paramKeyAndValue22.put("value", "");
//        paramKeyAndValueList2.add(paramKeyAndValue22);
//        Map<String, String> paramKeyAndValue23 = new HashMap<>();
//        paramKeyAndValue23.put("key", "净含量2");
//        paramKeyAndValue23.put("value", "2.6kg");
//        paramKeyAndValueList2.add(paramKeyAndValue23);
//        node2.setParams(paramKeyAndValueList);
//        resultList.add(node2);
        List<ParamTemplateNode> resultList = new ArrayList<>();
        MarketProductCategory marketProductCategory = this.marketProductCategoryRepository.findOne(categoryId);
        ArrayList<ParamTemplate> paramTemplates = marketProductCategory.getParamTemplates();
        for(ParamTemplate paramTemplate: paramTemplates) { // 规格分组
            ParamTemplateNode node = new ParamTemplateNode();
            node.setGroup(paramTemplate.getGroup());
            ArrayList<String> params = paramTemplate.getParams();
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
