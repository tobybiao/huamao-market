package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.MarketGoodsNewCategory;
import com.example.huamao.pojo.VBusinessCategory;
import com.example.huamao.service.BusinessCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 入住商家分类控制器
 * @author toby tobytb@163.com
 * @date 2018/5/29 20:06
 */
@RestController
@RequestMapping("/business")
public class BusinessCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(MarketGoodsCategoryController.class);
    private BusinessCategoryService businessCategoryService;

    @Autowired
    public BusinessCategoryController(BusinessCategoryService businessCategoryService) {
        this.businessCategoryService = businessCategoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public GenericResult addNewCategory(@RequestBody VBusinessCategory businessCategory) {
        logger.info("添加商场商品新分类信息:" + businessCategory);
        return GenericResult.ok(this.businessCategoryService.addNewCategory(businessCategory));
    }

    /**
     * 获取入住商家分类信息
     * @return 包含商家分类
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public GenericResult getAllCategory() {
        return GenericResult.ok(this.businessCategoryService.getAllCategory());
    }
}
