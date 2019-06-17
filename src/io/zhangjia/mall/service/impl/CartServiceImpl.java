package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.service.CarService;

import java.util.List;
import java.util.Map;

public class CartServiceImpl implements CarService {
    private CartDao cartDao  = new CartDaoImpl();
    @Override
    public List<Map<String, Object>> getCarCommodities(String userId) {
        Integer usersId = -1;
        if(userId != null || "".equals(userId)) {
            usersId = Integer.parseInt(userId);
        }
        return cartDao.queryByUserId(usersId);
    }
}
