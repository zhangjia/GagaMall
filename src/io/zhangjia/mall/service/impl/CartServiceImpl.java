package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.dao.impl.SKUDaoImpl;
import io.zhangjia.mall.service.CarService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CarService {
    private CartDao cartDao = new CartDaoImpl();
    private SKUDao skuDao = new SKUDaoImpl();
    private CommodityDao commodityDao = new CommodityDaoImpl();

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
        if (cart == null) {
            i = cartDao.doInsert(param);
        } else {
            i = cartDao.doUpdateCommodityCount(param);
        }



            map.put("success",i == 1);

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
    public Map<String, Object> updateCount(String action, String userId, String SKUId,String count) {
        int uid = -1;
        int sid = -1;
        int ct = -1;
        System.out.println("userId = " + userId);
        System.out.println("SKUId = " + SKUId);
        System.out.println("ct = " + count);
        Map<String, Object> map = new HashMap<>();
        if (userId != null && !"".equals(userId) &&
                SKUId != null && !"".equals(SKUId) && count != null && !"".equals(count)) {
            uid = Integer.parseInt(userId);
            sid = Integer.parseInt(SKUId);
            ct = Integer.parseInt(count);
            System.out.println("uid = " + uid);
            System.out.println("sid = " + sid);
            System.out.println("csid = " + sid);


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

            } else if (action.equals("input")) {
                if (ct > skuInventory) {
                    map.put("error", "超出库存");
                    Map<String,Object> m = new HashMap<>();
                    m.put("commodityCount",skuInventory);
                    m.put("userId",uid);
                    m.put("SKUId",sid);
                    cartDao.doUpdateCartCount(m);
                    map.put("skuInventory",skuInventory);
                    System.out.println(skuInventory);
                } else {
                    Map<String,Object> m = new HashMap<>();
                    m.put("commodityCount",ct);
                    m.put("userId",uid);
                    m.put("SKUId",sid);
                    i = cartDao.doUpdateCartCount(m);
                    System.out.println("m" + m);
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
