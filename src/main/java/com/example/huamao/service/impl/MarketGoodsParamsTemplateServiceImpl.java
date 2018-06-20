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
