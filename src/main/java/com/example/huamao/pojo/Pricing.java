package com.example.huamao.pojo;

/** 价格
 * @author toby tobytb@163.com
 * @date 2018/5/21 8:57
 */
public class Pricing {
    /**
     * 零售价 单位是 分
     */
    private long retail;

    /**
     * 批发价 单位是 分
     */
    private long sale;

    public long getRetail() {
        return retail;
    }

    public void setRetail(long retail) {
        this.retail = retail;
    }

    public long getSale() {
        return sale;
    }

    public void setSale(long sale) {
        this.sale = sale;
    }

    @Override
    public String toString() {
        return "Pricing{" +
                "retail=" + retail +
                ", sale=" + sale +
                '}';
    }
}
