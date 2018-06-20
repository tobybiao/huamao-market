package com.example.huamao.controller;

import com.example.huamao.pojo.ParamTemplateNode;
import com.example.huamao.service.BusinessGoodsParamsTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/** 入住商家商品规格模板controller
 * @author toby tobytb@163.com
 * @date 2018/6/1 11:44
 */
@RestController
@RequestMapping("/businessGoods")
public class BusinessGoodsParamsTemplateController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessGoodsParamsTemplateController.class);
    private BusinessGoodsParamsTemplateService businessGoodsParamsTemplateService;

    @Autowired
    public BusinessGoodsParamsTemplateController(BusinessGoodsParamsTemplateService businessGoodsParamsTemplateService) {
        this.businessGoodsParamsTemplateService = businessGoodsParamsTemplateService;
    }
    @RequestMapping(value = "paramsTemplate", method = RequestMethod.GET)
    public List<ParamTemplateNode> getParamsTemplateInformationByCategoryId(String categoryId) {
        logger.info("选择的分类id：{}", categoryId);
        return this.businessGoodsParamsTemplateService.getInformationByCategoryId(categoryId);
    }
}
