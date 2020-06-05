package io.zhangjia.mall.utils;


import com.alibaba.druid.pool.DruidDataSource;

public class JDBCDataSource {
    private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
    private static final String USERNAME = "gagamall";
    private static final String PASSWORD = "zhangjia";
    private static DruidDataSource dataSource;

    //setDriverClassName可以不设置，当不设置驱动类名时，会根据url自动设置
    static {
        dataSource = new DruidDataSource();
        dataSource.setUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static DruidDataSource getDataSource() {
        return dataSource;
    }
}

