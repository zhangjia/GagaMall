package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.dao.WalletDao;
import io.zhangjia.mall.dao.impl.OrderDaoImpl;
import io.zhangjia.mall.dao.impl.WalletDaoImpl;
import io.zhangjia.mall.service.WalletService;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class WalletServiceImpl implements WalletService {
    private WalletDao walletDao = new WalletDaoImpl();
    private OrderDao orderDao = new OrderDaoImpl();


    @Override
    public int addWallet(String userId) {
        if (userId != null && !"".equals(userId)) {
            return walletDao.doInsert(Integer.parseInt(userId));
        } else {
            return 0;
        }
    }

    @Override
    public Map<String, Object> getUserWallet(String userId) {
        if (userId != null && !"".equals(userId)) {
            return walletDao.queryByUserId(Integer.parseInt(userId));
        } else {
            return null;
        }

    }

    @Override
    public int recharge(String userId, String money) {
        System.out.println(" 进入service的recharge");
        if (userId != null && !"".equals(userId) && money != null && !"".equals(money)) {
            Integer uid = Integer.parseInt(userId);
            double m = Double.parseDouble(money);
            if (m <= 0) {
                System.out.println(" 充值金额小于0");
                return 0;
            }
            return walletDao.doUpdate(uid, m);
        } else {
            System.out.println(" 充值金额null0");

            return 0;
        }
    }

    @Override
    public Map<String, Object> orderPayByBalance(String userId, String money, String orderId) {
        System.out.println(" 进入了orderPayByBalance");
        Map<String, Object> result = new HashMap<>();
        System.out.println("userId = " + userId);
        System.out.println("money = " + money);
        System.out.println("orderId = " + orderId);
        if (userId != null && !"".equals(userId) && money != null && !"".equals(money) && orderId != null && !"".equals(orderId)) {
            Integer uid = Integer.parseInt(userId);
            Map<String, Object> stringObjectMap = walletDao.queryByUserId(uid);
            System.out.println("gaggagastringObjectMap = " + stringObjectMap);
            Double walletBalance = ((BigDecimal) stringObjectMap.get("WALLET_BALANCE")).doubleValue();
            System.out.println("walletBalance = " + walletBalance);
            System.out.println("wallet_balance = " + walletBalance);
            double m = Double.parseDouble(money);
            if (walletBalance < m) {
                result.put("error", "余额不足");
            } else {
                int i = walletDao.doUpdate(uid, m * -1);
                if (i == 1) {
                    result.put("success", "支付成功");
                    orderDao.doUpdateByPay("余额支付", uid, Integer.parseInt(orderId));
                } else {
                    System.out.println(" 路飞= ");
                    result.put("error", "支付失败");
                }
            }

        } else {
            System.out.println(" 索隆= ");
            result.put("error", "支付失败");

        }
        return result;
    }
}
