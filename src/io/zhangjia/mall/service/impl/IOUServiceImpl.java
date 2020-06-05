package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.IOUDao;
import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.dao.impl.IOUDaoImpl;
import io.zhangjia.mall.dao.impl.OrderDaoImpl;
import io.zhangjia.mall.service.IOUService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class IOUServiceImpl implements IOUService {
    private IOUDao iouDao = new IOUDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();

    @Override
    public Map<String, Object> queryUserIOU(String userId) {
        if (userId != null && !"".equals(userId)) {
            return iouDao.queryByUserId(Integer.parseInt(userId));
        }
        return null;
    }

    @Override
    public Map<String, Object> orderPayByIOU(String userId, String money, String orderId) {
        System.out.println(" orderPayByIOU");
        Map<String, Object> result = new HashMap<>();
        if (userId != null && !"".equals(userId) && money != null && !"".equals(money) && orderId != null && !"".equals(orderId)) {
            Integer uid = Integer.parseInt(userId);
            Map<String, Object> stringObjectMap = iouDao.queryByUserId(uid);
            System.out.println("gaggagastringObjectMap = " + stringObjectMap);
            Double iouBalance = ((BigDecimal) stringObjectMap.get("IOU_USABLE_LIMIT")).doubleValue();
            System.out.println("iouBalance = " + iouBalance);
            System.out.println("iouBalance = " + iouBalance);
            double m = Double.parseDouble(money);
            if (iouBalance < m) {
                result.put("error", "额度不足");
            } else {
                int i = iouDao.doUpdate(uid, m * -1);
                if (i == 1) {
                    result.put("success", "支付成功");
                    orderDao.doUpdateByPay("白条支付", uid, Integer.parseInt(orderId));
                } else {
                    result.put("error", "支付失败");
                }
            }

        } else {
            result.put("error", "支付失败");

        }
        return result;
    }
}
