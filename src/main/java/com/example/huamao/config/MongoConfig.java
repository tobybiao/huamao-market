package com.example.huamao.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/** Mongodb 配置
 * @author toby tobytb@163.com
 * @date 2018/5/2 16:55
 */
@Configuration
@EnableMongoRepositories("com.example.huamao.db")
public class MongoConfig extends AbstractMongoConfiguration  {
    @Override
    protected String getDatabaseName() {
        return "db_hua_mao_market"; // 指定数据库名称
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient(); // 创建Mongo 客户端
    }
}
