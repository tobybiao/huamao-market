package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.po.MarketProduct;
import com.example.huamao.pojo.VMarketGoods;
import com.example.huamao.service.MarketProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/** 商场商品Controller
 * @author toby tobytb@163.com
 * @date 2018/5/21 9:58
 */
@RestController
@RequestMapping("/market")
public class MarketGoodsController {
    private static final Logger logger = LoggerFactory.getLogger(MarketGoodsController.class);
    private MarketProductService marketProductService;

    @Autowired
    public MarketGoodsController(MarketProductService marketProductService) {
        this.marketProductService = marketProductService;
    }

    @RequestMapping(value = "/goods", method = RequestMethod.POST)
    public GenericResult addNewGoods(@RequestBody VMarketGoods marketGoods) {
        logger.info("界面传来的商品信息：{}", marketGoods);
        this.marketProductService.saveProduct(marketGoods);
        return GenericResult.ok(marketGoods);
    }

    @RequestMapping(value = "/allGoods", method = RequestMethod.GET)
    public GenericResult getAllGoods() {
        return GenericResult.ok(this.marketProductService.findAll());
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public GenericResult getByPage(int pageIndex, int pageSize) {
        logger.info("查找商场所有商品第 " + pageIndex + " 页, " + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketProductService.findAllByPage(pageIndex, pageSize));
    }

    @RequestMapping(value = "/pageByName")
    public GenericResult getByNameAndPage(String name, int pageIndex, int pageSize) {
        String goodsName = "";
        try {
            // 解决get请求 中文乱码
            goodsName = new String(name.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("通过商品名称:" + goodsName +":查找商场商品，第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketProductService.findAllByNameAndPage(goodsName, pageIndex, pageSize));
    }
}
