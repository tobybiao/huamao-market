package com.example.huamao.pojo.business;

import com.example.huamao.pojo.ViewOrderGoodItem;

import java.util.List;

/** 视图层 商家商家订单
 * @author toby tobytb@163.com
 * @date 2018/6/4 7:13
 */
public class ViewBusinessOrder {
    /**
     * 商家id mongoDB ObjectId
     */
    private String businessId;

    /**
     * 商家名称
     */
    private String businessName;

    /**
     * 支付方式：现金、微信、支付宝
     */
    private String payment;

    /**
     * 订单包含的商品项
     */
    private List<ViewOrderGoodItem> items;

    /**
     * 订单总额
     */
    private long totalAmount;

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public List<ViewOrderGoodItem> getItems() {
        return items;
    }

    public void setItems(List<ViewOrderGoodItem> items) {
        this.items = items;
    }

    public long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "ViewBusinessOrder{" +
                "businessId='" + businessId + '\'' +
                ", businessName='" + businessName + '\'' +
                ", payment='" + payment + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                '}';
    }
}
