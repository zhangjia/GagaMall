package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.WalletDao;
import io.zhangjia.mall.dao.impl.WalletDaoImpl;
import io.zhangjia.mall.service.WalletService;

import java.util.Map;

public class WalletServiceImpl implements WalletService {
    private WalletDao walletDao = new WalletDaoImpl();

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
}
