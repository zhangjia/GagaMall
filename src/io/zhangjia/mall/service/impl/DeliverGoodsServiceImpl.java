package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.dao.impl.OrderDaoImpl;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.DeliverGoodsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeliverGoodsServiceImpl implements DeliverGoodsService {
    private OrderServiceImpl  orderService = new OrderServiceImpl();
    private UserServiceImpl userService = new UserServiceImpl();
    private OrderDao orderDao = new OrderDaoImpl();
    @Override
    public List<Map<String, Object>> GetUnshippedOrders() {
        List<User> users = userService.queryUserAll();
        List<Map<String, Object>> unshippedOrders = new ArrayList<>();
        for (User user: users  ) {
            List<Map<String, Object>> orders = orderService.getOrders(user.getUserId() + "");
            for (Map<String,Object> order: orders ) {
                int orderStatus = ((BigDecimal)order.get("ORDER_STATUS")).intValue();
                if((orderStatus) == 1){
                    unshippedOrders.add(order);
                }
            }

        }
        return unshippedOrders;
    }

    @Override
    public int deliverGoods(String orderId, String logistic) {
        System.out.println("123orderId = " + orderId);
        System.out.println("123orderId = " + logistic);
        int i = orderDao.doUpdateByDeliverGoods(Integer.parseInt(orderId), logistic);
        System.out.println("i = " + i);
        return  i;
    }
}
