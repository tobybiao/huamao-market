package com.example.huamao.common.pojo;

/** 用户类型
 * @author toby tobytb@163.com
 * @date 2018/5/8 11:13
 */
public interface IUserType {
    /**
     * 超级管理员
     */
    String ADMIN = "admin";

    /**
     * 商场管理员
     */
    String MARKET_MANAGER = "marketManager";

    /**
     * 商场收银员
     */
    String MARKET_CASHIER = "marketCashier";

    /**
     * 商家
     */
    String BUSINESS = "business";
}
