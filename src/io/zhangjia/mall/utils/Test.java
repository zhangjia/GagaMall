package io.zhangjia.mall.utils;

import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

public class Test {
    public static void main(String[] args) {

        CommodityService c = new CommodityServiceImpl();
        c.getCommoditySPEC("1");

    }
}
