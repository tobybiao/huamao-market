package com.example.huamao.db;

import com.example.huamao.po.BusinessOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:57
 */
public interface BusinessOrderRepository extends MongoRepository<BusinessOrder, String> {
    /**
     * 通过商家id 查找商家订单信息
     * @param businessId 通过商家id mongoDB ObjectId
     * @return List<BusinessOrder>
     */
    List<BusinessOrder> findByBusinessId(String businessId);

    Page<BusinessOrder> findByBusinessId(String businessId, Pageable pageable);
}
