package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.SiteSettingsDao;
import io.zhangjia.mall.utils.CommonDao;

public class SiteSettingsDaoImpl extends CommonDao implements SiteSettingsDao {

    @Override
    public String queryByKey(String key) {
        String sql = "SELECT SITE_SITE_SETTINGS_VALUE VALUE FROM SITE_SETTINGS WHERE SITE_SETTINGS_OPTION=?";
        return query4StringData(sql, key);
    }

    @Override
    public int doUpdate(String key, String value) {
        String sql = "UPDATE SITE_SETTINGS\n" +
                "SET SITE_SITE_SETTINGS_VALUE = ?\n" +
                "\n" +
                "WHERE SITE_SETTINGS_OPTION = ?";
        return executeUpdate(sql, value, key);
    }
}
