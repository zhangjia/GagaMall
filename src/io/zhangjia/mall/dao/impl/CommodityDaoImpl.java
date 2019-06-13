package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.utils.CommonDao;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommodityDaoImpl extends CommonDao implements CommodityDao {

	public List<Commodity> queryCommodityImgs(List<Commodity> commodities){
		for (Commodity commodity:commodities) {
			String sql1 = "SELECT img_url,img_order,img_type FROM img WHERE img_belong = ? AND img_type='商品图' AND img_is_del = 1";
			List<Map<String, Object>> maps = query4MapList(sql1,commodity.getCommodityId());
//			System.out.println(maps);
			commodity.setCommodityImg(maps);
			String sql2 = "SELECT img_url,img_order,img_type FROM img WHERE img_belong = ? AND img_type='商品详情图' AND img_is_del = 1";
			List<Map<String, Object>> maps2 = query4MapList(sql2,commodity.getCommodityId());
//			System.out.println(maps);
			commodity.setCommodityDetails(maps2);
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
	public List<Commodity> queryAll(Integer firstMenuId,Integer secMenuId,int page,int pageSize,int order) {
		String sql = "";
		List<Commodity> commodities = null;
		//查询全部
		if(firstMenuId == null && secMenuId == null) {
			/*
			* 0:默认排序
			* 1：按照销量从低到高
			* 2：按照销量从高到低排序DESC
			* 3：按照价格排序
			* 4：按照价格从高到底排序DESC
			* 5：按照时间排序
			*
			*
			* */
			switch (order){
				case 0:
//					sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1) c) WHERE rn > ? AND rn <= ?";
					break;
				case 1:
//					sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES) COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES) c) WHERE rn > ? AND rn <= ?";
					break;
				case 2:
//					sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES DESC) COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES DESC) c) WHERE rn > ? AND rn <= ?";
					break;
				case 3:
//					sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_PRESENT_PRICE) COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_PRESENT_PRICE) c) WHERE rn > ? AND rn <= ?";
					break;
				case 4:
//					sql = "SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_PRESENT_PRICE DESC) COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_PRESENT_PRICE DESC) c) WHERE rn > ? AND rn <= ?";
					break;
				case 5:
//					sql="SELECT * FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_CREATE_TIME DESC) COMMODITIES) WHERE rn > ? AND rn <= ?";
					sql = "SELECT * FROM (SELECT ROWNUM rn,c.* FROM(SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_CREATE_TIME DESC) c) WHERE rn > ? AND rn <= ?";
					break;

			}
			commodities = query4BeanList(sql, Commodity.class,page,pageSize);
			queryCommodityImgs(commodities);
			return commodities;
		}
		if(firstMenuId == null && secMenuId != null) {
			/*
			 * 0:默认排序
			 * 1：按照销量从低到高
			 * 2：按照销量从高到低排序DESC
			 * 3：按照价格排序
			 * 4：按照价格从高到底排序DESC
			 * 5：按照时间排序
			 *
			 *
			 * */
			switch (order){
				case 0:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES WHERE SEC_MENU_ID = ? ) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 1:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ? ORDER BY COMMODITY_SALES) COMMODITIES) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID ORDER BY COMMODITY_SALES) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 2:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ? ORDER BY COMMODITY_SALES DESC) COMMODITIES) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID ORDER BY COMMODITY_SALES DESC) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 3:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ? ORDER BY COMMODITY_PRESENT_PRICE) COMMODITIES) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 4:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ? ORDER BY COMMODITY_PRESENT_PRICE DESC) COMMODITIES) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE DESC) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 5:
//					sql="SELECT r.*,f.FIRST_MENU_CHINESE_NAME,s.SEC_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ? ORDER BY COMMODITY_CREATE_TIME DESC) COMMODITIES) r,FIRST_MENU f,SEC_MENU s WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID AND r.SEC_MENU_ID = s.SEC_MENU_ID";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,s.SEC_MENU_CHINESE_NAME FROM COMMODITIES c,SEC_MENU s WHERE c.SEC_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.SEC_MENU_ID = s.SEC_MENU_ID ORDER BY COMMODITY_CREATE_TIME DESC) c2) WHERE rn > ? AND rn <= ?";
					break;

			}
			commodities = query4BeanList(sql, Commodity.class,secMenuId,page,pageSize);
			queryCommodityImgs(commodities);
			return commodities;

		}

		if(firstMenuId != null && secMenuId == null) {

			/*
			 * 0:默认排序
			 * 1：按照销量从低到高
			 * 2：按照销量从高到低排序DESC
			 * 3：按照价格排序
			 * 4：按照价格从高到底排序DESC
			 * 5：按照时间排序
			 *
			 *
			 * */
			switch (order){
				case 0:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM COMMODITIES WHERE FIRST_MENU_ID =? ) r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//					sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 1:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ? ORDER BY COMMODITY_SALES) COMMODITIES ) r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//                    sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_SALES ) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_SALES) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 2:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ? ORDER BY COMMODITY_SALES DESC) COMMODITIES ) r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//                    sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_SALES DESC) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_SALES DESC) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 3:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ? ORDER BY COMMODITY_PRESENT_PRICE) COMMODITIES )r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//                    sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 4:
//					sql = "SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ? ORDER BY COMMODITY_PRESENT_PRICE DESC) COMMODITIES )r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//                    sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE DESC) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_PRESENT_PRICE DESC) c2) WHERE rn > ? AND rn <= ?";
					break;
				case 5:
//					sql="SELECT r.*,f.FIRST_MENU_CHINESE_NAME FROM (SELECT ROWNUM rn,COMMODITIES.* FROM (SELECT * FROM COMMODITIES WHERE COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ? ORDER BY COMMODITY_CREATE_TIME DESC) COMMODITIES )r,FIRST_MENU f WHERE COMMODITY_IS_DEL = 1 AND rn > ? AND rn <= ? AND r.FIRST_MENU_ID = f.FIRST_MENU_ID";
//                    sql = "SELECT * FROM (SELECT ROWNUM rn,c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_CREATE_TIME DESC) WHERE rn > ? AND rn <= ?";
                    sql = "SELECT * FROM (SELECT ROWNUM rn,c2.* FROM (SELECT c.*,f.FIRST_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f WHERE c.FIRST_MENU_ID =? AND COMMODITY_IS_DEL = 1 AND c.FIRST_MENU_ID = f.FIRST_MENU_ID ORDER BY COMMODITY_CREATE_TIME DESC) c2) WHERE rn > ? AND rn <= ?";
					break;

			}
			commodities = query4BeanList(sql, Commodity.class,firstMenuId,page,pageSize);
			queryCommodityImgs(commodities);
			return commodities;

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

	@Override
	public Commodity queryCommodity(String commodityId) {
		String sql = "SELECT c.*,FIRST_MENU_CHINESE_NAME,SEC_MENU_CHINESE_NAME FROM COMMODITIES c,FIRST_MENU f,SEC_MENU s WHERE  c.SEC_MENU_ID = s.SEC_MENU_ID AND c.FIRST_MENU_ID = f.FIRST_MENU_ID AND COMMODITY_ID = ? AND COMMODITY_IS_DEL = 1";
		List<Commodity> c = new ArrayList<>();
		c.add(query4Bean(sql, Commodity.class, commodityId));
		List<Commodity> commodities = queryCommodityImgs(c);
		return  commodities.get(0);
	}

	@Override
	public List<Commodity> queryAllByOrder() {
		return null;
	}
}
