package com.example.huamao.db;

import com.example.huamao.po.Business;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/24 23:43
 */
public interface BusinessRepository extends MongoRepository<Business, String>, IUserLoginRepository<Business>{
}
