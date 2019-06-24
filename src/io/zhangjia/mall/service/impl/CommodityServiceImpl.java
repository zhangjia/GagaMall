package io.zhangjia.mall.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.dao.impl.SKUDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.CommodityService;
import org.omg.PortableInterceptor.INACTIVE;

import java.math.BigDecimal;
import java.util.*;

public class CommodityServiceImpl implements CommodityService {

    private CommodityDao commodityDao = new CommodityDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private SKUDao skuDao = new SKUDaoImpl();

    private int pageSize = 2;

    @Override
    public List<Commodity> getCommodities(String name, String page,
                                          String firstMenuId, String secMenuId, String orders) {
        int pages = 0;
        int order = 0;

        if (orders == null || "".equals(orders)) {
            order = 0;
        } else {
            order = Integer.parseInt(orders);
        }
        if (page != null) {
            pages = Integer.parseInt(page);
        }

        if (name != null) {
            return commodityDao.queryLike(name, (pages - 1) * pageSize, pageSize * pages, order);
        } else {
            if (firstMenuId == null && secMenuId == null) {
                return commodityDao.queryAll(null, null, (pages - 1) * pageSize, pageSize * pages, order);
            }
            if (firstMenuId != null && secMenuId == null) {
                if (!"".equals(firstMenuId)) {
                    return commodityDao.queryAll(Integer.parseInt(firstMenuId), null, (pages - 1) * pageSize, pageSize * pages, order);
                }
            }
            if (firstMenuId == null && secMenuId != null) {
                if (!"".equals(secMenuId)) {
                    return commodityDao.queryAll(null, Integer.parseInt(secMenuId), (pages - 1) * pageSize, pageSize * pages, order);
                }

            }
        }

        return null;

    }

    @Override
    public Integer getPagesCount(String firstMenuId, String secMenuId, String name) {
        if (firstMenuId != null && (!"".equals(firstMenuId))) {
            double ceil = Math.ceil(commodityDao.queryCommodityCount(Integer.parseInt(firstMenuId), null, name) / (pageSize * 1.0));
            return (int) ceil;

        }
        if (secMenuId != null && (!"".equals(secMenuId))) {
            double ceil = Math.ceil(commodityDao.queryCommodityCount(null, Integer.parseInt(secMenuId), name) / (pageSize * 1.0));
            return (int) ceil;

        }

        if (secMenuId == null && firstMenuId == null) {
            double ceil = Math.ceil(commodityDao.queryCommodityCount(null, null, name) / (pageSize * 1.0));
            return (int) ceil;
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
Map<String,List<String>> sku = null;
		List<Map<String,List<String>>> skus = null;
		Set<String> key = new HashSet<>();
		Set<String> value = new HashSet<>();
        Map<String,String> z = new HashMap<>();
 cid = Integer.parseInt(commodityId);
    List<Map<String, Object>> maps = commodityDao.querySPEC(cid);
    //遍历所有的记录，每次遍历生成一个：
			for (Map<String,Object> map: maps) {
        //获取SKU表中的JSON字符串
        String skuValue = (String)map.get("SKU_VALUE");
        //将其转换为Java对象
        JSONObject jsonObject = JSON.parseObject(skuValue);
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
 * @param skuValue
 * @return
 */

    public String getCommoditySPEC(String commodityId) {
        /*
         *{"颜色":["蓝色","白色"]}
         * 蓝色，白色使用List<String>，最外层使用Map，多条记录再包一层List
         * 即：List<Map<String,List<String>>>
         * */
        Integer cid = null;
        System.out.println((String) commodityDao.querySPEC(Integer.parseInt(commodityId)).get(0).get("COMMODITY_ATTRIBUTES"));
        if (commodityId != null || !"".equals(commodityId)) {
            return (String) commodityDao.querySPEC(Integer.parseInt(commodityId)).get(0).get("COMMODITY_ATTRIBUTES");
        }

        return null;

    }

    /**
     * 根据sku表的sku——value获取该商品的其他sku属性
     *
     * @param skuValue
     * @return
     */
    @Override
    public List<Map<String, Object>> getCommoditySKU(String skuValue) {
        return commodityDao.querySKU(skuValue);
    }


    /**
     * 在商品详情页，点击《》按钮判断是否可以
     * @param action
     * @param userId
     * @param SKUId
     * @param count
     * @return
     */
    @Override
    public Map<String, Object> updateCount2CommodityDetail(String action, String userId, String SKUId, String count,String vals) {
        int uid = -1;
        int sid = -1;
        int ct = -1;
        int val = -1;
        System.out.println("userId = " + userId);
        System.out.println("SKUId = " + SKUId);
        System.out.println("val = " + vals);
        Map<String, Object> map = new HashMap<>();
        if (userId != null && !"".equals(userId) &&
                SKUId != null && !"".equals(SKUId) && count != null && !"".equals(count)
                && vals != null && !"".equals(vals)) {
            uid = Integer.parseInt(userId);
            sid = Integer.parseInt(SKUId);
            ct = Integer.parseInt(count);

            val = Integer.parseInt(vals);
            System.out.println("进入了嘎嘎");

            /*思路整理：
             * 先获取当前商品的库存
             * 再获取当前商品在当前用户的购物车中数量，
             * 如果是0，则添加，如果不是0，则修改
             * 如果购物车中的数量加当前选中的数量 > 库存，则失败
                     * */

//        获取当前商品的库存
            int skuInventory = skuDao.querySKUInventory(sid);

//        再获取当前商品在当前用户的购物车中数量
            int skuCount;
            Map<String, Object> cartSKU = cartDao.queryByUserIdAndSKUId(uid, sid);
            if (cartSKU == null) {
                map.put("nowInventory",skuInventory);
                skuCount = 0;
            } else {
                System.out.println(cartSKU);
                map.put("nowInventory",skuInventory);
                skuCount= ((BigDecimal) cartSKU.get("COMMODITY_COUNT")).intValue();

            }
            if (action.equals("add") || action.equals("input")) {
                System.out.println("Sku" + skuCount + "val" + val + "ct" + ct + "skuInventory" +skuInventory);
                if ((val + ct) > skuInventory) {
                    System.out.println("库存吵了");
                    map.put("error", "超出库存");

                }

            }


            //如果是null，说明购物车里没有

            System.out.println("action = " + action + "--" + "userId = " + userId + "--" +"count = " + count + "--" + skuCount + "--" + SKUId);
            System.out.println();


            return map;
        } else {
            return null;
        }
    }

    @Override
    public List<Commodity> getCommodities4Index() {
        return commodityDao.queryCommodity4Index();
    }


}
