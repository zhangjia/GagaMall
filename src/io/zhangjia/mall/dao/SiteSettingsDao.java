package io.zhangjia.mall.dao;

public interface SiteSettingsDao {
    String queryByKey(String key);
    int doUpdate(String key,String value);
}
