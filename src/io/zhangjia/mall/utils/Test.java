package io.zhangjia.mall.utils;

import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.UserDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.dao.impl.UserDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.UserService;
import io.zhangjia.mall.service.impl.UserServiceImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {


       CommodityDao co = new CommodityDaoImpl();
        co.queryAll();

    }
}
