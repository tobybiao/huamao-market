package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.business.ViewBusinessGoods;
import com.example.huamao.service.BusinessProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * 入住商家商品 controller
 * @author toby tobytb@163.com
 * @date 2018/6/1 20:19
 */
@RestController
@RequestMapping("/business")
public class BusinessGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessGoodsController.class);
    private BusinessProductService businessProductService;

    @Autowired
    public BusinessGoodsController(BusinessProductService businessProductService) {
        this.businessProductService = businessProductService;
    }

    /**
     * 入住商家添加自家商品信息
     * @param viewBusinessGoods 视图层添加的商品信息包含商家信息
     * @return GenericResult 包含保存到数据库信息的响应结果
     */
    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public GenericResult addNewGoods(@RequestBody ViewBusinessGoods viewBusinessGoods) {
        logger.info("界面传来的商品信息：{}", viewBusinessGoods);
        return GenericResult.ok(this.businessProductService.saveProduct(viewBusinessGoods));
    }

    /**
     * 通过商品id 来更新商品信息
     * @param viewBusinessGoods 视图层传来的商品信息包含商品id 信息
     * @return GenericResult 包含保存到数据库信息的响应结果
     */
    @RequestMapping(value = "/goods", method = RequestMethod.PUT)
    public GenericResult updateGoods(@RequestBody ViewBusinessGoods viewBusinessGoods) {
        logger.info("界面传来的商品信息：" + viewBusinessGoods);
        return GenericResult.ok(this.businessProductService.updateProduct(viewBusinessGoods));
    }

    /**
     * 通过商品id 来删除商品
     * @param id 商品id mongoDB ObjectId
     * @return GenericResult
     */
    @RequestMapping(value = "/goods/{id}", method = RequestMethod.DELETE)
    public GenericResult deleteGoods(@PathVariable String id) {
        logger.info("即将删除" + id);
        return GenericResult.ok(this.businessProductService.deleteById(id));
    }
    /**
     * 分页展示商家商品信息
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex 页码
     * @param pageSize 每页数据条数
     * @return
     */
    @RequestMapping(value = "/goods/page", method = RequestMethod.GET)
    public GenericResult getByPage(String businessId, int pageIndex, int pageSize) {
        logger.info("查找商家：" + businessId + " 所有商品第 " + pageIndex + " 页, " + "每页大小为：" + pageSize);
        return GenericResult.ok(this.businessProductService.findAllByPage(businessId, pageIndex, pageSize));
    }

    @RequestMapping(value = "/goods/pageByName")
    public GenericResult getByNameAndPage(String name, String businessId, Integer pageIndex, Integer pageSize) {
        String goodsName = "";
        try {
            // 解决get请求 中文乱码
            goodsName = new String(name.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("通过商品名称:" + goodsName +":查找商场商品，第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.businessProductService.findAllByNameAndPage(goodsName, businessId, pageIndex, pageSize));
    }

    /**
     * 通过唯一的商品sku 编码查找商品信息
     * @param sku 唯一的商品sku 编码
     * @return GenericResult
     */
    @RequestMapping(value = "/goods/{sku}", method = RequestMethod.GET)
    public GenericResult findGoodsBySku(@PathVariable String sku) {
        logger.info("通过sku" + sku + "查找商品信息");
        return GenericResult.ok(this.businessProductService.findBySku(sku));
    }
}
