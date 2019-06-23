package io.zhangjia.mall.service;

import java.util.Map;

public interface WalletService {
    int addWallet(String userId);
    Map<String,Object> getUserWallet(String userId);
    int recharge(String userId,String money);
}
