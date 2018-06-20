package com.example.huamao.common.pojo;

/** 通用相应结果
 * @author toby tobytb@163.com
 * @date 2018/5/8 10:36
 */
public class GenericResult {
    /**
     * 响应业务状态
     */
    private Integer status;

    /**
     * 响应消息
     */
    private String message;

    /**
     * 响应中的数据
     */
    private Object data;

    public GenericResult() {
    }
    private GenericResult(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功响应，并返回可用数据
     * @param data 返回的响应数据
     * @return 返回成功数据
     */
    public static GenericResult ok(Object data) {
        return new GenericResult(200, "OK", data);
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
