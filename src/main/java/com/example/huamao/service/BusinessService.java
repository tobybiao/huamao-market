package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.po.Business;
import com.example.huamao.pojo.VBusiness;
import com.example.huamao.pojo.VBusinessListNode;

import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/30 17:39
 */
public interface BusinessService {
    /**
     * 添加入住商家
     * @param vBusiness　视图层传来的入住商家信息
     * @return 　Business　保存到数据库的入住商家信息
     */
    Business saveBusiness(VBusiness vBusiness);

    /**
     * 通过分页查找所有入住商家信息
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

    /**
     * 通过id 修改入住商家信息
     * @param vBusinessListNode 视图层 商家列表中单个商家
     * @return Business 保存到数据库中的商家信息
     */
    Business updateBusinessById(VBusinessListNode vBusinessListNode);
}
