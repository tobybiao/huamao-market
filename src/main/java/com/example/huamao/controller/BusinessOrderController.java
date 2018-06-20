package com.example.huamao.controller;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.po.BusinessOrder;
import com.example.huamao.pojo.business.ViewBusinessOrder;
import com.example.huamao.service.BusinessOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/** 商家订单处理
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:22
 */
@RestController
@RequestMapping("/business")
public class BusinessOrderController {
    private static final Logger logger = LoggerFactory.getLogger(BusinessOrderController.class);
    private BusinessOrderService businessOrderService;

    @Autowired
    public BusinessOrderController(BusinessOrderService businessOrderService) {
        this.businessOrderService = businessOrderService;
    }

    /**
     *  商家提交订单
     * @param viewBusinessOrder 视图层商家订单信息
     * @return GenericResult
     */
    @RequestMapping(value = "/order", method = RequestMethod.POST)
    public GenericResult OrderGoods(@RequestBody ViewBusinessOrder viewBusinessOrder) {
        logger.info("视图层传来订单数据：" + viewBusinessOrder);
        BusinessOrder businessOrder = null;
        try {
            businessOrder = this.businessOrderService.generateOrder(viewBusinessOrder);
        } catch (Exception e) {
            e.printStackTrace();
            GenericResult result = new GenericResult();
            result.setMessage("生成订单失败！");
            result.setStatus(500);
            result.setData(null);
            return result;
        }
        return GenericResult.ok(businessOrder);
    }
    @RequestMapping(value = "/order/page", method = RequestMethod.GET)
    public GenericResult getAllOrderByPage(String businessId, int pageIndex, int pageSize) {
        logger.info("查找商家：" + businessId + " 所有订单第" + pageIndex + "页," + "每页大小为：" + pageSize);
        return GenericResult.ok(this.businessOrderService.findAllByPage(businessId, pageIndex, pageSize));
    }

    @RequestMapping(value = "/order/{businessId}")
    public GenericResult getAllOrderByBusinessId(@PathVariable String businessId) {
        logger.info("通过商家id " + businessId + "查找所有订单 ");
        return GenericResult.ok(this.businessOrderService.findAllByBusinessId(businessId));
    }
}
