package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.FirstMenuDao;
import io.zhangjia.mall.dao.SecMenuDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class SecMenuDaoImpl extends CommonDao implements SecMenuDao {


    @Override
    public List<Map<String, Object>> querySecMenu() {
//		String sql = "SELECT * FROM  sec_menu WHERE sec_menu_id IN (SELECT sec_menu_id FROM commodities WHERE commodity_is_del=1 GROUP BY sec_menu_id )";
        String sql = "SELECT  DISTINCT s.SEC_MENU_ID,s.SEC_MENU_CHINESE_NAME,f.FIRST_MENU_ID, f.FIRST_MENU_CHINESE_NAME\n" +
                "FROM FIRST_MENU f,\n" +
                "     SEC_MENU s,\n" +
                "     commodity c\n" +
                "WHERE f.FIRST_MENU_ID = c.FIRST_MENU_ID\n" +
                "  AND s.SEC_MENU_ID = c.SEC_MENU_ID AND c.COMMODITY_IS_DEL = 1\n" +
                "ORDER BY f.FIRST_MENU_ID";
        return query4MapList(sql);
    }
}


