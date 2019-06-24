package io.zhangjia.mall.utils;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.MailCodeService;
import io.zhangjia.mall.service.OrderService;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;
import io.zhangjia.mall.service.impl.MailCodeServiceImpl;
import io.zhangjia.mall.service.impl.OrderServiceImpl;
import io.zhangjia.mall.servlet.SettlementServlet;

public class Test {
    public static void main(String[] args) {
//
//        MailCodeService mailCodeService = new MailCodeServiceImpl();
//        mailCodeService.getMailCode("zhangjia@188.com");
        CartDao cartDao = new CartDaoImpl();
        String[]a = new String[2];
        a[1] = "20";
        a[0] = "8";
//        cartDao.querylogistics(1,a);
        cartDao.queryTotal(1,a);
    }
}
