package com.example.huamao.db;

import com.example.huamao.po.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/** 集合t_admin 超级管理员
 * @author toby tobytb@163.com
 * @date 2018/5/2 17:54
 */
public interface AdminRepository extends MongoRepository<Admin, String> , IUserLoginRepository<Admin> {
}
