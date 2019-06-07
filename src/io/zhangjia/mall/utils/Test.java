package io.zhangjia.mall.utils;

import io.zhangjia.mall.dao.UserDao;
import io.zhangjia.mall.dao.impl.UserDaoImpl;
import io.zhangjia.mall.service.UserService;
import io.zhangjia.mall.service.impl.UserServiceImpl;

import java.sql.Date;

public class Test {
    public static void main(String[] args) {
       /* UserDao usreDap = new UserDaoImpl();
        System.out.println(usreDap.queryByUsername("luffy"));
        System.out.println(usreDap.queryByUsernameAndPassword("luffy","1"));

        System.out.println(usreDap.doInsert("zhangjia","1","2"
        ,"15622222221","zhang@zhangjia.com","男",new Date(System.currentTimeMillis())
                ,"baidu.com"));*/

        UserService us = new UserServiceImpl();
        System.out.println(us.login("luffy","2"));
    }
}
