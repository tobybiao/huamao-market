package com.example.huamao.db;

import com.example.huamao.po.MarketManager;
import org.springframework.data.mongodb.repository.MongoRepository;

/**t_marketManager  商场管理员
 * @author toby tobytb@163.com
 * @date 2018/5/8 21:17
 */
public interface MarketManagerRepository extends MongoRepository<MarketManager, String>, IUserLoginRepository<MarketManager> {
}
