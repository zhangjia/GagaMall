package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.WalletDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.Map;

public class WalletDaoImpl extends CommonDao implements WalletDao {
    @Override
    public int doInsert(Integer userId) {
        String sql = "INSERT INTO wallet VALUES(seq_wallet.nextval,0.0,0,null,0,?)";
        return executeUpdate(sql, userId);
    }

    @Override
    public Map<String, Object> queryByUserId(Integer userId) {
        String sql = "SELECT  * FROM WALLET WHERE USER_ID = ?";
        return query4Map(sql, userId);
    }

    @Override
    public int doUpdate(Integer userId, Double money) {
        String sql = "UPDATE WALLET SET WALLET_BALANCE = WALLET_BALANCE +  ? WHERE USER_ID = ?";
        return executeUpdate(sql, money, userId);
    }


}
