package io.zhangjia.mall.utils;

import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.OrderService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;
import io.zhangjia.mall.service.impl.OrderServiceImpl;

public class Test {
    public static void main(String[] args) {

        OrderService c = new OrderServiceImpl();
        System.out.println(c.getOrder("1" , "3"));

    }
}
