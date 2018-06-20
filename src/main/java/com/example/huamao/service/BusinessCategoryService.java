package com.example.huamao.service;

import com.example.huamao.po.BusinessCategory;
import com.example.huamao.pojo.VBusinessCategory;

import java.util.List;
import java.util.Map;

/**
 * @author toby tobytb@163.com
 * @date 2018/5/29 20:37
 */
public interface BusinessCategoryService {
    /**
     * 添加入住商家分类
     * @param vBusinessCategory 视图层传来的新分类信息
     * @return BusinessCategory 保存到数据库的新分类信息
     */
    BusinessCategory addNewCategory(VBusinessCategory vBusinessCategory);

    /**
     * 获取所有入住商家分类信息
     * @return 入住商家分类信息<pre>
    [{
        value: '5a5e07d772cb620e64ef485f|服装店',
        label: '服装店',
        children: [
            {
            value: '5a5e07d772cb620e64ef482e|男装专卖',
            label: '男装专卖'
            },
            {
            value: '5a5e07d772cb620e64ef482f|女装专卖',
            label: '女装专卖'
            }
        ]
    }]</pre>
    备注：“|”为英文状态下"|"
     */
    List<Map<String,Object>> getAllCategory();
}
