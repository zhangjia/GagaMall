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
//			System.out.println(maps);
			commodity.setCommodityImg(maps);
		}
		return commodities;

	}

//	@Override
//	public List<Commodity> queryAll(Integer firstMenuId,Integer secMenuId,int page,int pageSize) {
//		//查出来所有的商品
//		String sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES) WHERE rn > ? AND rn <= ?";
////		List<Commodity> commodities = query4BeanList(sql, Commodity.class,"0","2");
//		List<Commodity> commodities = query4BeanList(sql, Commodity.class,page,pageSize);
//		System.out.println(page + " -- " + pageSize);
//		queryCommodityImgs(commodities);
//		return commodities;
//	}
	@Override
	public List<Commodity> queryAll(Integer firstMenuId,Integer secMenuId,int page,int pageSize) {
		String sql = "";
		//查询全部
		if(firstMenuId == null && secMenuId == null) {
			 sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES) WHERE rn > ? AND rn <= ?";
			List<Commodity> commodities = query4BeanList(sql, Commodity.class,page,pageSize);
			queryCommodityImgs(commodities);
			return commodities;
		}
		if(firstMenuId != null && secMenuId == null) {
			sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES WHERE FIRST_MENU_ID = ? ) WHERE COMMODITY_IS_DEL = 1" +
					"AND rn > ? AND rn <= ?";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,firstMenuId,page,pageSize));
		}
		if(firstMenuId == null && secMenuId != null) {
			sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES WHERE SEC_MENU_ID = ?) WHERE COMMODITY_IS_DEL = 1" +
					"AND rn > ? AND rn <= ?";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,secMenuId,page,pageSize));
		}
		return null;
	}


	@Override
	public List<Commodity> queryLike(String name) {
//		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER('%?%')";
		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER(?)";

		return queryCommodityImgs(query4BeanList(sql, Commodity.class,"%" + name +"%"));
	}

/*	@Override
	public List<Commodity> queryMenuId(Integer firstMenuId, Integer secMenuId) {
		String sql = "";
		if (firstMenuId != null) {
			sql = "SELECT * FROM COMMODITIES WHERE FIRST_MENU_ID = ? AND COMMODITY_IS_DEL = 1";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,firstMenuId));
		} else {
			sql = "SELECT * FROM COMMODITIES WHERE SEC_MENU_ID = ? AND COMMODITY_IS_DEL = 1";
			return queryCommodityImgs(query4BeanList(sql, Commodity.class,secMenuId));
		}

	}*/

	@Override
	public Integer queryCommodityCount(Integer firstMenuId,Integer secMenuId) {

		String sql = "";
		if (firstMenuId == null && secMenuId == null) {
			sql = "SELECT count(*) FROM COMMODITIES WHERE  COMMODITY_IS_DEL = 1";
			return query4IntData(sql);
		} else if(firstMenuId != null){
			sql = "SELECT count(*) FROM COMMODITIES WHERE  COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ?";
			return query4IntData(sql,firstMenuId);
		} else {
			sql = "SELECT count(*) FROM COMMODITIES WHERE  COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ?";
			return query4IntData(sql,secMenuId);
		}



	}
}
