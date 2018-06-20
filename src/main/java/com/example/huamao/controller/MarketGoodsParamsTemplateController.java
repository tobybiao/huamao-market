package com.example.huamao.controller;

import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.service.MarketGoodsParamsTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  商场商品规格模板控制器
 * @author toby tobytb@163.com
 * @date 2018/5/20 0:40
 */
@RestController
@RequestMapping("/market")
public class MarketGoodsParamsTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(MarketGoodsParamsTemplateController.class);
    private MarketGoodsParamsTemplateService marketGoodsParamsTemplateService;

    @Autowired
    public MarketGoodsParamsTemplateController(MarketGoodsParamsTemplateService marketGoodsParamsTemplateService) {
        this.marketGoodsParamsTemplateService = marketGoodsParamsTemplateService;
    }

    @RequestMapping(value = "paramsTemplate", method = RequestMethod.GET)
    public List<ParamTemplateNode> getParamsTemplateInformationByCategoryId(String categoryId) {
        logger.info("选择的分类id：{}", categoryId);
        return this.marketGoodsParamsTemplateService.getInformationByCategoryId(categoryId);
    }
}
