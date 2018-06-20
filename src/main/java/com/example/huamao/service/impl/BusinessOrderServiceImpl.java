package com.example.huamao.service.impl;

import com.example.huamao.db.BusinessOrderRepository;
import com.example.huamao.db.BusinessProductRepository;
import com.example.huamao.po.BusinessOrder;
import com.example.huamao.po.BusinessProduct;
import com.example.huamao.pojo.ViewOrderGoodItem;
import com.example.huamao.pojo.business.ViewBusinessOrder;
import com.example.huamao.pojo.business.ViewBusinessOrderListItem;
import com.example.huamao.service.BusinessOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/** 商家订单服务
 * @author toby tobytb@163.com
 * @date 2018/6/4 8:00
 */
@Service
public class BusinessOrderServiceImpl implements BusinessOrderService {
    private static final Logger logger = LoggerFactory.getLogger(BusinessOrderServiceImpl.class);
    private BusinessOrderRepository businessOrderRepository;
    private BusinessProductRepository businessProductRepository;
    @Value("${ORDER_GENERATE_FORMAT}")
    private String ORDER_GENERATE_FORMAT;
    @Autowired
    public BusinessOrderServiceImpl(BusinessOrderRepository businessOrderRepository, BusinessProductRepository businessProductRepository) {
        this.businessOrderRepository = businessOrderRepository;
        this.businessProductRepository = businessProductRepository;
    }

    /**
     * 生成商家订单
     * @param viewBusinessOrder 视图层 商家商家订单
     * @return BusinessOrder
     */
    @Override
    public BusinessOrder generateOrder(ViewBusinessOrder viewBusinessOrder) throws Exception{
        BusinessOrder businessOrder = new BusinessOrder();
        // 减少相应商品的库存
        List<ViewOrderGoodItem> orderGoodItems = viewBusinessOrder.getItems();
        for(ViewOrderGoodItem orderGoodItem: orderGoodItems) {
            // 遍历订单所有商品项，通过商品Id 更新库存
            String goodsId = orderGoodItem.getId(); // 商品id
            int goodsNumber = orderGoodItem.getQuantity();// 库存减少量
            BusinessProduct businessProduct = this.businessProductRepository.findOne(goodsId);// 更新商品库存
            int newStock = businessProduct.getStock() - goodsNumber; // 更新后的库存
            if(newStock < 0) {
                logger.error("商家商品 id = " + goodsId + " 库存不足，无法生成订单");
                throw new RuntimeException("商家商品 id = " + goodsId + " 库存不足，无法生成订单");
            }
            businessProduct.setStock(newStock);
            this.businessProductRepository.save(businessProduct); // 更新商品库存
        }
        businessOrder.setBusinessId(viewBusinessOrder.getBusinessId());
        businessOrder.setBusinessName(viewBusinessOrder.getBusinessName());
        businessOrder.setPayment(viewBusinessOrder.getPayment());
        businessOrder.setTotalAmount(viewBusinessOrder.getTotalAmount());
        businessOrder.setItems(viewBusinessOrder.getItems());
        businessOrder.setCreateTime(new Date());
        this.businessOrderRepository.save(businessOrder);
        logger.info("保存到数据库的商家订单信息如下：" + businessOrder);
        return businessOrder;
    }

    /**
     * 通过分页查找所有订单
     *
     * @param businessId 商家id mongoDB ObjectId
     * @param pageIndex  页码
     * @param pageSize   每页数据量
     * @return Map<String, Object> {"totalElements": 100,"totalPages": 20,"content":List<element>}
     */
    @Override
    public Map<String, Object> findAllByPage(String businessId, int pageIndex, int pageSize) {
        Map<String,Object> result = new HashMap<>();
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<BusinessOrder> businessOrderPage = this.businessOrderRepository.findByBusinessId(businessId, pageable);
        result.put("totalElements", businessOrderPage.getTotalElements());
        result.put("totalPages", businessOrderPage.getTotalPages());
        result.put("content", this.PoBusinessOrderListToViewBusinessOrderListItemList(businessOrderPage.getContent()));
        logger.info("在数据库中分页显示入住商家所有订单信息返回给视图数据：" + result);
        return result;
    }

    /**
     * 通过商家id 查找该商家所有订单
     *
     * @param businessId mongoDB ObjectId
     * @return List<BusinessOrder>
     */
    @Override
    public List<BusinessOrder> findAllByBusinessId(String businessId) {
        return this.businessOrderRepository.findByBusinessId(businessId);
    }

    /**
     * 数据库中BusinessOrder 转换为展示订单列表的订单列表
     * @param businessOrderList 数据库中BusinessOrder 列表
     * @return List<ViewBusinessOrderListItem>
     */
    private List<ViewBusinessOrderListItem> PoBusinessOrderListToViewBusinessOrderListItemList(List<BusinessOrder> businessOrderList) {
        List<ViewBusinessOrderListItem> viewBusinessOrderListItemList = new ArrayList<>();
        for(BusinessOrder businessOrder: businessOrderList) {
            ViewBusinessOrderListItem orderListItem = new ViewBusinessOrderListItem();
            orderListItem.setId(businessOrder.getId());
            orderListItem.setPayment(businessOrder.getPayment());
            orderListItem.setTotalAmount(businessOrder.getTotalAmount());
            orderListItem.setItems(businessOrder.getItems());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(this.ORDER_GENERATE_FORMAT);
            orderListItem.setCreateTime(simpleDateFormat.format(businessOrder.getCreateTime()));
            viewBusinessOrderListItemList.add(orderListItem);
        }
        return viewBusinessOrderListItemList;
    }
}
