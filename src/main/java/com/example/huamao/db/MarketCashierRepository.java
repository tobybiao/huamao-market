package com.example.huamao.db;

import com.example.huamao.po.MarketCashier;
import org.springframework.data.mongodb.repository.MongoRepository;

/** 集合t_marketCashier 商场收银员
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:27
 */
public interface MarketCashierRepository extends MongoRepository<MarketCashier, String>, IUserLoginRepository<MarketCashier> {
}
