package com.example.huamao.common.util;

import java.util.Random;

/** id 生成工具
 * @author toby tobytb@163.com
 * @date 2018/5/21 16:41
 */
public class IDUtils {
    /**
     * 商场商品 sku 生成
     * @return 商场商品sku
     */
    public static String genMarketGoodsSku() {
        // 取当前时间的长整形值包含纳秒
        long nanoTime = System.nanoTime();
        //加上三位随机数
        Random random = new Random();
        int end3 = random.nextInt(999);
        //如果不足三位前面补0
        return nanoTime + String.format("%03d", end3);
    }
    public static String genBusinessGoodsSku() {
        // 取当前时间的长整形值包含纳秒
        long nanoTime = System.nanoTime();
        Random random = new Random();
        //加上四位随机数
        int end4 = random.nextInt(9999);
        //如果不足四位前面补0
        return  nanoTime + String.format("%04d", end4);
    }
}
