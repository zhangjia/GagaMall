package io.zhangjia.mall.service.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao = new CommodityDaoImpl();
	@Override
	public List<Commodity> queryCommodities(String name,Integer firstMenuId,Integer secMenuId) {
		if(name == null && firstMenuId == null && secMenuId == null) {
			System.out.println("进入1");
			return commodityDao.queryAll();
		} else 	if (name !=null){
			System.out.println("进入2");
			return commodityDao.queryLike(name);
		} else {
			System.out.println("进入3");
			return commodityDao.queryMenuId(firstMenuId,secMenuId);
		}


	}


}
