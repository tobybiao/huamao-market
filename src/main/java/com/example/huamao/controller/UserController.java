package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.VBusiness;
import com.example.huamao.pojo.VBusinessListNode;
import com.example.huamao.pojo.VMarketCashier;
import com.example.huamao.pojo.VMarketManager;
import com.example.huamao.service.BusinessService;
import com.example.huamao.service.MarketCashierService;
import com.example.huamao.service.MarketManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;

/** 用户管理
 * @author toby tobytb@163.com
 * @date 2018/5/23 20:24
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private MarketCashierService marketCashierService;
    private MarketManagerService marketManagerService;
    private BusinessService businessService;

    @Autowired
    public UserController(MarketCashierService marketCashierService, MarketManagerService marketManagerService, BusinessService businessService) {
        this.marketCashierService = marketCashierService;
        this.marketManagerService = marketManagerService;
        this.businessService = businessService;
    }
    @RequestMapping(value = "/marketManager", method = RequestMethod.POST)
    public GenericResult addMarketManager(@RequestBody VMarketManager marketManager) {
        logger.info("视图层传来的商场管理者数据：" + marketManager);
        return GenericResult.ok(this.marketManagerService.saveMarketManager(marketManager));
    }
    @RequestMapping(value = "/marketManager/page", method = RequestMethod.GET)
    public GenericResult getMarketManagerByPage(int pageIndex, int pageSize) {
        logger.info("查找商场所有商场管理者第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketManagerService.findAllByPage(pageIndex, pageSize));
    }
    @RequestMapping(value = "/marketManager/page/username", method = RequestMethod.GET)
    public GenericResult getMarketManagerByUserNameAndPage(String username, int pageIndex, int pageSize) {
        String name = "";
        try {
            name = new String(username.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("通过用户名: "+ name + " 查找商场所有商场管理者第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketManagerService.findAllByNameAndPage(name, pageIndex, pageSize));
    }
    @RequestMapping(value = "/marketCashier", method = RequestMethod.POST)
    public GenericResult addMarketCashier(@RequestBody VMarketCashier marketCashier) {
        logger.info("视图层传来的用户数据：" + marketCashier);
        this.marketCashierService.saveMarketCashier(marketCashier);
        return GenericResult.ok(marketCashier);
    }
    @RequestMapping(value = "/marketCashier/page", method = RequestMethod.GET)
    public GenericResult getMarketCashierByPage(int pageIndex, int pageSize) {
        logger.info("查找商场所有收银员第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketCashierService.findAllByPage(pageIndex, pageSize));
    }
    @RequestMapping(value = "/marketCashier/page/username", method = RequestMethod.GET)
    public GenericResult getMarketCashierByUserNameAndPage(String username, int pageIndex, int pageSize) {
        String name = "";
        try {
            name = new String(username.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("通过用户名: " + name + "查找商场所有收银员第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.marketCashierService.findAllByNameAndPage(name, pageIndex, pageSize));
    }

    /**
     *  添加入住商家
     * @param vBusiness 视图层入住商家信息
     * @return 包含保存到数据库中的入住商家信息
     */
    @RequestMapping(value = "/business", method = RequestMethod.POST)
    public GenericResult addBusiness(@RequestBody VBusiness vBusiness) {
        logger.info("视图层传来的入住商家数据：" + vBusiness);
        return GenericResult.ok(this.businessService.saveBusiness(vBusiness));
    }
    @RequestMapping(value = "/business/page", method = RequestMethod.GET)
    public GenericResult getBusinessByPage(int pageIndex, int pageSize) {
        logger.info("查找商场所有入住商家第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.businessService.findAllByPage(pageIndex, pageSize));
    }

    @RequestMapping(value = "/business/page/username", method = RequestMethod.GET)
    public GenericResult getBusinessByUserNameAndPage(String username, int pageIndex, int pageSize) {
        String name = "";
        try {
            name = new String(username.getBytes("iso8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        logger.info("通过用户名: " + name + "查找商场所有入住商家第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.businessService.findAllByNameAndPage(name, pageIndex, pageSize));
    }

    @RequestMapping(value = "/business", method = RequestMethod.PUT)
    public GenericResult updateBusinessById(@RequestBody VBusinessListNode vBusinessListNode) {
        logger.info("请求更新商家后的数据：" + vBusinessListNode);
        return GenericResult.ok(this.businessService.updateBusinessById(vBusinessListNode));
    }
}
