package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.dao.impl.SKUDaoImpl;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.CommodityService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CarService {
    private CartDao cartDao = new CartDaoImpl();
    private SKUDao skuDao = new SKUDaoImpl();
    private CommodityDao commodityDao = new CommodityDaoImpl();
    private CommodityService commodityService= new CommodityServiceImpl();

    @Override
    public List<Map<String, Object>> getCarCommodities(String userId) {
        Integer usersId = -1;
        if (userId != null || "".equals(userId)) {
            usersId = Integer.parseInt(userId);
        }
        return cartDao.queryByUserId(usersId);
    }

    @Override
    public Map<String, Object> addCart(String userId, String SKUId, String commodityCount) {
        Map<String, Object> param = new HashMap<>();
        int uid = 0;
        int sid = 0;
        int count = 0;
        Map<String, Object> map = new HashMap<>();
        if (userId != null && !"".equals(userId) &&
                SKUId != null && !"".equals(SKUId) &&
                commodityCount != null && !"".equals(commodityCount)) {
            uid = Integer.parseInt(userId);
            sid = Integer.parseInt(SKUId);
            count = Integer.parseInt(commodityCount);
            param.put("userId", uid);
            param.put("SKUId", sid);
            param.put("commodityCount", count);
        }

        Map<String, Object> cart = cartDao.queryByUserIdAndSKUId(uid, sid);
        //仔细考虑一下，这里的判断为啥要加在Service里而不是DaoImpl里
        int i = 0;


        Map<String, Object> add = commodityService.updateCount2CommodityDetail("add", userId, SKUId, commodityCount);

        if(add.get("error") == null ){
            i = 1;

            if (cart == null) {
                i = cartDao.doInsert(param);
            } else {
                i = cartDao.doUpdateCommodityCount(param);
            }

        } else {
            i = 0;
            map.put("error",add.get("error"));
        }


        map.put("success",i == 1);
        System.out.println("put"+map);

        return map;
    }

    @Override
    public boolean deleteCart(String userId, List<String> SKUId) {
        if (userId != null && !"".equals(userId)) {
            System.out.println("SKUID" + SKUId);
            boolean b = cartDao.doDelete(Integer.parseInt(userId), SKUId) == 1;
            System.out.println("b = " + b);
            return b;
        }
        return false;
    }

    @Override
    public Map<String, Object> updateCount(String action, String userId, String SKUId) {
        int uid = -1;
        int sid = -1;
        System.out.println("userId = " + userId);
        System.out.println("SKUId = " + SKUId);
        Map<String, Object> map = new HashMap<>();
        if (userId != null && !"".equals(userId) &&
                SKUId != null && !"".equals(SKUId)) {
            uid = Integer.parseInt(userId);
            sid = Integer.parseInt(SKUId);
            System.out.println("uid = " + uid);
            System.out.println("sid = " + sid);


            /*思路整理：
             * 先获取当前商品的库存
             * 再获取当前商品在当前用户的购物车中数量
             * 如果购物车中的数量+1 大于 库存，则失败
             * 如果购物车中的数量-1 = 0 。则失败
             * */

//        获取当前商品的库存
            int skuInventory = skuDao.querySKUInventory(sid);

//        再获取当前商品在当前用户的购物车中数量

            Map<String, Object> cartSKU = cartDao.queryByUserIdAndSKUId(uid, sid);
            System.out.println("cartSKU = " + cartSKU);
            System.out.println("cartSKU.get(\"COMMODITY_COUNT\")class = " + cartSKU.get("COMMODITY_COUNT").getClass());
            int skuCount = ((BigDecimal) cartSKU.get("COMMODITY_COUNT")).intValue();

            int i = 0;
            if (action.equals("add")) {
                if (skuCount + 1 > skuInventory) {
                    map.put("error", "超出库存");
                } else {
                    i = cartDao.addCount(uid, sid);
                }

            } else {
                if (skuCount - 1 == 0) {
                    map.put("error", "不能再少啦");
                } else {
                    i = cartDao.subCount(uid, sid);
                }

            }
            if (i == 1) {
                map.put("success", true);
            }

            return map;
        } else {
            return null;
        }

    }
}
