package io.zhangjia.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.sun.xml.internal.bind.v2.TODO;
import io.zhangjia.mall.dao.AddressDao;
import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.dao.impl.AddressDaoImpl;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.dao.impl.OrderDaoImpl;
import io.zhangjia.mall.dao.impl.SKUDaoImpl;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.OrderService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
    private AddressDao addressDao = new AddressDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    private CartDao cartDao = new CartDaoImpl();
    private SKUDao skuDao = new SKUDaoImpl();

    private CarService carService = new CartServiceImpl();

    @Override
    public List<Map<String, Object>> getOrders(String userId) {
        if (userId != null && !"".equals(userId)) {
            List<Map<String, Object>> maps = orderDao.queryByUserId(Integer.parseInt(userId));


            for (Map<String, Object> map : maps) {
//               获取地址编号
                int addressId = Integer.parseInt(map.get("ADDRESS_ID") + "");
                Map<String, Object> stringObjectMap = addressDao.queryById(addressId);
                map.put("ADDRESS", stringObjectMap);
                int orderId = Integer.parseInt(map.get("ORDER_ID") + "");
                System.out.println("orderIdzz = " + orderId);
                Double aDouble = orderDao.queryOrderPrice(orderId);
                map.put("ORDERPRICE", aDouble);
                List<Map<String, Object>> maps1 = orderDao.queryCommodityByOrderId(orderId);
                map.put("COMMODITIES", maps1);
                Double aDouble1 = orderDao.queryOrderDiscountPrice(orderId);
                map.put("DISCOUNTPRICE", aDouble1);
                Double aDouble2 = orderDao.queryOrderOriginalPrice(orderId);
                map.put("ORIGINALPRICE", aDouble2);
            }
            System.out.println("jsonmaps" + JSON.toJSONString(maps));
            return maps;
        } else {
            return null;
        }
    }

    @Override
    public Map<String, Object> getOrder(String userId, String orderId) {
        List<Map<String, Object>> orders = getOrders(userId);
        for (Map<String, Object> m : orders) {
            System.out.println(("m" + JSON.toJSONString(m)));
            System.out.println((m.get("ORDER_ID") + "").equals(orderId));
            //必须加""，否则是false
            if ((m.get("ORDER_ID") + "").equals(orderId)) {
                System.out.println("进入orderId");
                return m;
            }
        }
        return null;

    }

    @Override
    public int submit(String userId, String addressId, String logistics,
                      String orderPayType, String orderNote, String[] SKUIds) {
        int result = 1;
        if (userId != null && !"".equals(userId) &&
                addressId != null && !"".equals(addressId)) {
//            判断运费
            Map<String, Object> total = carService.getTotal(userId, SKUIds);
            System.out.println("判断运费 = " + total);
            int logistic = (((BigDecimal) total.get("SUM_COMMODITY_PRESENT_PRICE")).doubleValue() >= 1000) ? 0 : 10;

            //        1.向订单表插入数据

            Map<String, Object> param = new HashMap<>();
            param.put("userId", userId);
            param.put("addressId", addressId);
//            param.put("orderLogistics", 10);
//            TODO：订单物流
            param.put("orderLogistics", "未支付");
            param.put("orderFreightPrice", logistic);
            param.put("orderPayType", "未支付");
            param.put("orderNote", orderNote);
            int orderId = orderDao.doInsert(param);

            result *= orderId;
            System.out.println("result0 = " + result);
//        2.  向订单明细表插入数据
            List<Map<String, Object>> maps = cartDao.queryCommodities4Settlement(Integer.parseInt(userId), SKUIds);
            List<String> SKUIDs = new ArrayList<>();
            for (Map<String, Object> map : maps) {
                map.put("ORDER_ID", orderId);
//            TODO：订单优惠金额，留着做优惠券和京豆使用
                map.put("ORdER_DETAILS_DISCOUNT_PRICE", 10);
                result *= orderDao.doInsert4Detail(map);
                System.out.println("result1 = " + result);
                SKUIDs.add(((BigDecimal) map.get("SKU_ID")).intValue() + "");


//                4. 更改库存和销量
                result *= skuDao.updateInventoryAndSales(Integer.parseInt("" + map.get("SKU_ID")),
                        Integer.parseInt("" + map.get("COMMODITY_COUNT")));
                System.out.println("result3 = " + result);
            }
//        3. 删除购物车中的商品
            System.out.println("orderserviceSKUIDs = " + SKUIDs);
            result *= cartDao.doDelete(Integer.parseInt(userId), SKUIDs);
//       如果插入成功，将id返回
            if (result != 0) {
                return orderId;
            } else {
                return 0;
            }

        } else {

            return 0;
        }
    }
}
