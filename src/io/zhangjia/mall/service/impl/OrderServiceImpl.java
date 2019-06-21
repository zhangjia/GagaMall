package io.zhangjia.mall.service.impl;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.dao.AddressDao;
import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.dao.impl.AddressDaoImpl;
import io.zhangjia.mall.dao.impl.OrderDaoImpl;
import io.zhangjia.mall.service.OrderService;

import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {
        private AddressDao addressDao = new AddressDaoImpl();
        private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Map<String, Object>> getOrders(String userId) {
       if(userId !=null && !"".equals(userId)){
           List<Map<String, Object>> maps = orderDao.queryByUserId(Integer.parseInt(userId));



           for (Map<String, Object> map : maps) {
//               获取地址编号
               int addressId = Integer.parseInt(map.get("ADDRESS_ID")+"");
               Map<String, Object> stringObjectMap = addressDao.queryById(addressId);
               map.put("ADDRESS", stringObjectMap);
               int orderId = Integer.parseInt(map.get("ORDER_ID")+"");
               Double aDouble = orderDao.queryOrderPrice(orderId);
               map.put("ORDERPRICE",aDouble);
               List<Map<String, Object>> maps1 = orderDao.queryCommodityByOrderId(orderId);
               map.put("COMMODITIES",maps1);
               Double aDouble1 = orderDao.queryOrderDiscountPrice(orderId);
               map.put("DISCOUNTPRICE",aDouble1);
               Double aDouble2 = orderDao.queryOrderOriginalPrice(orderId);
               map.put("ORIGINALPRICE",aDouble2);
           }
           System.out.println("jsonmaps" + JSON.toJSONString(maps));
           return  maps;
       } else {
           return  null;
       }
    }

    @Override
    public Map<String, Object> getOrder(String userId,String orderId) {
        List<Map<String, Object>> orders = getOrders(userId);
        for (Map<String, Object> m: orders) {
            System.out.println(("m" + JSON.toJSONString(m)));
            System.out.println((m.get("ORDER_ID")+"").equals(orderId));
            //必须加""，否则是false
            if((m.get("ORDER_ID")+"").equals(orderId)) {
                System.out.println("进入orderId");
                return m;
            }
        }
        return null;

    }
}
