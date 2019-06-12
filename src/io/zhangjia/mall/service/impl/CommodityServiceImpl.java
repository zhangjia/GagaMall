package io.zhangjia.mall.service.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.List;

public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao = new CommodityDaoImpl();
	private int pageSize = 1;
	@Override
	public List<Commodity> queryCommodities(String name,String page,
											String firstMenuId,String secMenuId) {
		int pages= 0;

		if(page != null) {
			pages = Integer.parseInt(page);
		}


		/*if(name == null && firstMenuId == null && secMenuId == null) {
			System.out.println("进入1");
			return commodityDao.queryAll((pages-1) * pageSize,pageSize * pages);
		} else 	if (name !=null){
			System.out.println("进入2");
			return commodityDao.queryLike(name);
		} else {
			System.out.println("进入3");
			if(firstMenuId != null && (!"".equals(firstMenuId))) {

				return commodityDao.queryMenuId(Integer.parseInt(firstMenuId),null);

			}
			if(secMenuId != null && (!"".equals(secMenuId))) {

				return commodityDao.queryMenuId(null,Integer.parseInt(secMenuId));

			}
			return null;
		}*/

		if(name != null) {
			return commodityDao.queryLike(name);
		} else {
			if(firstMenuId == null && secMenuId == null) {
				return commodityDao.queryAll(null,null,(pages-1) * pageSize,pageSize * pages);
			}
			if(firstMenuId != null && secMenuId == null) {
				if(!"".equals(firstMenuId)) {
					return commodityDao.queryAll(Integer.parseInt(firstMenuId), null, (pages - 1) * pageSize, pageSize * pages);
				}
			}
			if(firstMenuId == null && secMenuId != null) {
				if(!"".equals(secMenuId)) {
					return commodityDao.queryAll(null,Integer.parseInt(secMenuId),(pages-1) * pageSize,pageSize * pages);
				}

			}
		}

		return  null;

	}

	@Override
	public Integer queryPagesCount(String firstMenuId, String secMenuId) {
		if(firstMenuId != null && (!"".equals(firstMenuId))) {

			return commodityDao.queryCommodityCount(Integer.parseInt(firstMenuId),null)/ pageSize;

		}
		if(secMenuId != null && (!"".equals(secMenuId))) {

			return commodityDao.queryCommodityCount(null,Integer.parseInt(secMenuId))/ pageSize;

		}

		if(secMenuId == null && firstMenuId==null) {
			return commodityDao.queryCommodityCount(null, null) / pageSize;
		}

		return -1;
	}


}
