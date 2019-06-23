package io.zhangjia.mall.dao;

import java.util.Map;

public interface WalletDao {
    int doInsert(Integer userId);
    Map<String,Object> queryByUserId(Integer userId);
    int doUpdate(Integer userId, Double money);
}
