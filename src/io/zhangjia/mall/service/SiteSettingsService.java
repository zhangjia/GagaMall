package io.zhangjia.mall.service;

public interface SiteSettingsService {
    String getValue(String key);

    int editValue(String key, String value);
}
