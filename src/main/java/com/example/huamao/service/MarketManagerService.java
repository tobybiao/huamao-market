package com.example.huamao.service;

import com.example.huamao.po.MarketManager;
import com.example.huamao.pojo.VMarketManager;

import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/29 9:03
 */
public interface MarketManagerService {
    /**
     * 添加商场管理员信息
     * @param vMarketManager 视图层传来的数据
     * @return MarketManager 保存到数据库后的商场管理员信息
     */
    MarketManager saveMarketManager(VMarketManager vMarketManager);
    /**
     * 通过分页查找所有商场管理员信息
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
