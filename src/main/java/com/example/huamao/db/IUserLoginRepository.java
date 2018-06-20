package com.example.huamao.db;

import java.util.List;

/** 用户登录数据访问接口
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:19
 */
public interface IUserLoginRepository <T> {
    /**
     * 通过用户名去查找用户信息
     * @param username 用户名
     * @return 返回找到的用户列表
     */
    List<T> findByUsername(String username);
}
