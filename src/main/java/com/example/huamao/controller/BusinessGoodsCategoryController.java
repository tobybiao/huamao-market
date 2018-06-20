package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.business.ViewBusinessProductCategory;
import com.example.huamao.service.BusinessProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/** 入住商家商品分类Controller
 * @author toby tobytb@163.com
 * @date 2018/5/31 20:10
 */
@RestController
@RequestMapping("/businessGoods")
public class BusinessGoodsCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessGoodsCategoryController.class);
    private BusinessProductCategoryService businessProductCategoryService;

    @Autowired
    public BusinessGoodsCategoryController(BusinessProductCategoryService businessProductCategoryService) {
        this.businessProductCategoryService = businessProductCategoryService;
    }
    @RequestMapping(value = "/category", method = RequestMethod.POST)
    public GenericResult addBusinessGoodsCategory(@RequestBody ViewBusinessProductCategory viewBusinessProductCategory) {
        logger.info("视图层传来商家商品分类的数据: " + viewBusinessProductCategory);
        return GenericResult.ok(this.businessProductCategoryService.addNewCategory(viewBusinessProductCategory));
    }

    /**
     * 获取入住商家商品分类信息
     * @param businessId 入住商家id 分类所属商家id MongoDB ObjectId
     * @return 包含商家商品分类信息
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    public GenericResult getAllCategoryByBusinessId(String businessId) {
        logger.info("获取商家id为： " + businessId + " 的商品分类信息");
        return GenericResult.ok(this.businessProductCategoryService.getAllCategoryByBusinessId(businessId));
    }
}
