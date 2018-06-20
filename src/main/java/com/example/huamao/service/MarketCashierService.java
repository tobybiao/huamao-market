package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.VMarketCashier;

import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/23 23:14
 */
public interface MarketCashierService {
    /**
     * 添加商场收银员
     * @param marketCashier　视图层传来的商场收银员信息
     * @return 　GenericResult　包含添加信息的响应数据
     */
    GenericResult saveMarketCashier(VMarketCashier marketCashier);

    /**
     * 通过分页查找所有商场收银员信息
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByPage(int pageIndex, int pageSize);

    /**
     *  通过用户名查找用户信息，结果分页展示
     * @param name 用户名
     * @param pageIndex 页码 0开始
     * @param pageSize 每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    Map<String, Object> findAllByNameAndPage(String name, int pageIndex, int pageSize);
}
