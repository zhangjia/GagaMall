package io.zhangjia.mall.service.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao = new CommodityDaoImpl();
	private int pageSize = 2;
	@Override
	public List<Commodity> queryCommodities(String name,String page,
											String firstMenuId,String secMenuId,String orders) {
		int pages= 0;
		int order = 0;

		if(orders == null || "".equals(orders)){
			order = 0;
		} else {
			order = Integer.parseInt(orders);
		}
		if(page != null) {
			pages = Integer.parseInt(page);
		}

		if(name != null) {
			return commodityDao.queryLike(name,(pages-1) * pageSize,pageSize * pages,order);
		} else {
			if(firstMenuId == null && secMenuId == null) {
				return commodityDao.queryAll(null,null,(pages-1) * pageSize,pageSize * pages,order);
			}
			if(firstMenuId != null && secMenuId == null) {
				if(!"".equals(firstMenuId)) {
					return commodityDao.queryAll(Integer.parseInt(firstMenuId), null, (pages - 1) * pageSize, pageSize * pages,order);
				}
			}
			if(firstMenuId == null && secMenuId != null) {
				if(!"".equals(secMenuId)) {
					return commodityDao.queryAll(null,Integer.parseInt(secMenuId),(pages-1) * pageSize,pageSize * pages,order);
				}

			}
		}

		return  null;

	}

	@Override
	public Integer queryPagesCount(String firstMenuId, String secMenuId,String name) {
		if(firstMenuId != null && (!"".equals(firstMenuId))) {
			double ceil = Math.ceil(commodityDao.queryCommodityCount(Integer.parseInt(firstMenuId), null,name) / (pageSize * 1.0));
			return (int)ceil;

		}
		if(secMenuId != null && (!"".equals(secMenuId))) {
			double ceil = Math.ceil(commodityDao.queryCommodityCount(null,Integer.parseInt(secMenuId),name)/ (pageSize * 1.0));
			return (int)ceil;

		}

		if(secMenuId == null && firstMenuId==null) {
			double ceil = Math.ceil(commodityDao.queryCommodityCount(null, null,name) / (pageSize * 1.0));
			return (int)ceil;
		}

		return -1;
	}

	@Override
	public Commodity queryCommodity(String firstMenuId) {
		return commodityDao.queryCommodity(firstMenuId);
	}


}
