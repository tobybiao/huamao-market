package com.example.huamao.service;

import com.example.huamao.common.pojo.GenericResult;
import com.example.huamao.pojo.MarketGoodsNewCategory;

/** 商场商品分类服务
 * @author toby tobytb@163.com
 * @date 2018/5/17 11:13
 */
public interface MarketProductCategoryService {
    /**
     * 添加商场商品新分类
     * @param newCategory 封装新分类的信息
     * @return 包含添加新分类的响应信息
     */
    GenericResult addNewCategory(MarketGoodsNewCategory newCategory);

    /**
     * 获取所有商场商品分类数据
     * @return 响应包含分类数据
     */
    GenericResult getAllCategory();
}
