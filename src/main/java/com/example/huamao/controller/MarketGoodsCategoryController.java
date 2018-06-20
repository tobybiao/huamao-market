package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.MarketGoodsNewCategory;
import com.example.huamao.service.MarketProductCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/** 商场商品分类控制器
 * @author toby tobytb@163.com
 * @date 2018/5/17 9:48
 */
@Controller
@RequestMapping("/market")
public class MarketGoodsCategoryController {
    private static final Logger logger = LoggerFactory.getLogger(MarketGoodsCategoryController.class);
    private MarketProductCategoryService marketProductCategoryService;

    @Autowired
    public MarketGoodsCategoryController(MarketProductCategoryService marketProductCategoryService) {
        this.marketProductCategoryService = marketProductCategoryService;
    }

    @RequestMapping(value = "/category", method = RequestMethod.POST)
    @ResponseBody
    public GenericResult addNewCategory(@RequestBody MarketGoodsNewCategory marketGoodsNewCategory) {
        logger.info("添加商场商品新分类信息:" + marketGoodsNewCategory);
        this.marketProductCategoryService.addNewCategory(marketGoodsNewCategory);
        return GenericResult.ok(marketGoodsNewCategory);
    }

    /**
     * 获取商场商品分类信息
     * @return 包含分类信息的响应
     */
    @RequestMapping(value = "/category", method = RequestMethod.GET)
    @ResponseBody
    public GenericResult getAllCategory() {
        return this.marketProductCategoryService.getAllCategory();
    }
}
