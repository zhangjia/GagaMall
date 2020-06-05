package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.SiteSettingsDao;
import io.zhangjia.mall.dao.impl.SiteSettingsDaoImpl;
import io.zhangjia.mall.service.SiteSettingsService;

public class SiteSettingsServiceImpl implements SiteSettingsService {
    private SiteSettingsDao siteSettingsDao = new SiteSettingsDaoImpl();

    @Override
    public String getValue(String key) {
        return siteSettingsDao.queryByKey(key);
    }

    @Override
    public int editValue(String key, String value) {
        System.out.println("key + \"---\"+ value = " + key + "---" + value);
        return siteSettingsDao.doUpdate(key, value);
    }
}
