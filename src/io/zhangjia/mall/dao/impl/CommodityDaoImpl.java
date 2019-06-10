package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.utils.CommonDao;
import org.omg.CORBA.INTERNAL;

import java.util.List;
import java.util.Map;

public class CommodityDaoImpl extends CommonDao implements CommodityDao {

	public List<Commodity> queryCommodityImgs(List<Commodity> commodities){
		for (Commodity commodity:commodities) {
			String sql2 = "SELECT img_url,img_order,img_type FROM img WHERE img_belong = ? AND img_type='商品图' AND img_is_del = 1";
			List<Map<String, Object>> maps = query4MapList(sql2,commodity.getCommodityId());
			System.out.println(maps);
			commodity.setCommodityImg(maps);
		}
		return commodities;

	}

	@Override
	public List<Commodity> queryAll() {
		//查出来所有的商品
		String sql = "SELECT * FROM commodities WHERE commodity_is_del = 1";
		List<Commodity> commodities = query4BeanList(sql, Commodity.class);
		System.out.println("--" +commodities);
		queryCommodityImgs(commodities);
		System.out.println("--" +commodities);
		return commodities;
	}

	@Override
	public List<Commodity> queryLike(String name) {
//		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER('%?%')";
		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER(?)";

		return queryCommodityImgs(query4BeanList(sql, Commodity.class,"%" + name +"%"));
	}

	@Override
	public List<Commodity> queryMenuId(Integer firstMenuId, Integer secMenuId) {
		String sql = "";
		if (firstMenuId != null) {
			sql = "SELECT * FROM COMMODITIES WHERE FIRST_MENU_ID = ? AND COMMODITY_IS_DEL = 1";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,firstMenuId));
		} else {
			sql = "SELECT * FROM COMMODITIES WHERE SEC_MENU_ID = ? AND COMMODITY_IS_DEL = 1";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,secMenuId));
		}

	}
}
