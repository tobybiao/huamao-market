package com.example.huamao.service;

import com.example.huamao.po.BusinessOrder;
import com.example.huamao.pojo.business.ViewBusinessOrder;

import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:58
 */
public interface BusinessOrderService {
    /**
     * 生成商家订单
     * @param viewBusinessOrder 视图层 商家商家订单
     * @return BusinessOrder
     */
    BusinessOrder generateOrder(ViewBusinessOrder viewBusinessOrder) throws Exception;

    /**
     * 通过分页查找所有订单
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByPage(String businessId, int pageIndex, int pageSize);

    /**
     * 通过商家id 查找该商家所有订单
     * @param businessId mongoDB ObjectId
     * @return List<BusinessOrder>
     */
    List<BusinessOrder> findAllByBusinessId(String businessId);
}
