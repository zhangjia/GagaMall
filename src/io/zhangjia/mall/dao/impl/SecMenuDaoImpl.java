package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.FirstMenuDao;
import io.zhangjia.mall.dao.SecMenuDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class SecMenuDaoImpl extends CommonDao implements SecMenuDao {


	@Override
	public List<Map<String, Object>> querySecMenu() {
		String sql = "SELECT * FROM  sec_menu WHERE sec_menu_id IN (SELECT sec_menu_id FROM commodities WHERE commodity_is_del=1 GROUP BY sec_menu_id )";
		return query4MapList(sql);
	}
}


