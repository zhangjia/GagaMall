package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.FirstMenuDao;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class FirstMenuDaoImpl extends CommonDao implements FirstMenuDao {

	@Override
	public List<Map<String, Object>> queryFirstMenu() {
//		String sql = "SELECT DISTINCT first_menu.first_menu_id,first_menu_chinese_name FROM commodities,first_menu WHERE commodities.first_menu_id = first_menu.first_menu_id";
		String sql = "SELECT * FROM  first_menu WHERE first_menu_id IN (SELECT first_menu_id FROM commodities WHERE commodity_is_del=1 GROUP BY first_menu_id )";
		return query4MapList(sql);
	}
}


