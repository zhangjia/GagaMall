package io.zhangjia.mall.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import org.omg.PortableInterceptor.INACTIVE;

import java.util.*;

public class CommodityServiceImpl implements CommodityService {

	private CommodityDao commodityDao = new CommodityDaoImpl();
	private int pageSize = 2;
	@Override
	public List<Commodity> getCommodities(String name, String page,
										  String firstMenuId, String secMenuId, String orders) {
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
	public Integer getPagesCount(String firstMenuId, String secMenuId, String name) {
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
	public Commodity getCommodity(String firstMenuId) {
		return commodityDao.queryCommodity(firstMenuId);
	}

	@Override
//	public List<Map<String, Object>> getCommoditySPEC(String commodityId) {
/*
Map<String,List<String>> spu = null;
		List<Map<String,List<String>>> spus = null;
		Set<String> key = new HashSet<>();
		Set<String> value = new HashSet<>();
        Map<String,String> z = new HashMap<>();
 cid = Integer.parseInt(commodityId);
    List<Map<String, Object>> maps = commodityDao.querySPEC(cid);
    //遍历所有的记录，每次遍历生成一个：
			for (Map<String,Object> map: maps) {
        //获取SPU表中的JSON字符串
        String spuValue = (String)map.get("SPU_VALUE");
        //将其转换为Java对象
        JSONObject jsonObject = JSON.parseObject(spuValue);
        //获取所有的key
        Set<String> strings = jsonObject.keySet();
        //遍历所有的key，将其存入set，防止重复
        for (String str : strings) {
            //取出当前记录下的单个规格的值
            z.put(str,(String)jsonObject.get(str));
        }

        System.out.println(key);
    }*/
/**
 * 根据商品的ID，查询商品的属性
 * @param spuValue
 * @return
 */

    public String getCommoditySPEC(String commodityId) {
	    /*
	    *{"颜色":["蓝色","白色"]}
	    * 蓝色，白色使用List<String>，最外层使用Map，多条记录再包一层List
	    * 即：List<Map<String,List<String>>>
	    * */
		Integer cid = null;
        System.out.println((String)commodityDao.querySPEC(Integer.parseInt(commodityId)).get(0).get("COMMODITY_ATTRIBUTES"));
		if(commodityId != null || !"".equals(commodityId)) {
           return  (String)commodityDao.querySPEC(Integer.parseInt(commodityId)).get(0).get("COMMODITY_ATTRIBUTES");
		}

		return null;

	}

	/**
	 * 根据spu表的spu——value获取该商品的其他spu属性
	 * @param spuValue
	 * @return
	 */
	@Override
	public List<Map<String,Object>> getCommoditySPU(String spuValue) {
		return  commodityDao.querySPU(spuValue);
	}


}
