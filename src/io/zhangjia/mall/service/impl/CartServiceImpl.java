package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.dao.impl.CartDaoImpl;
import io.zhangjia.mall.service.CarService;

import java.util.HashMap;
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

    @Override
    public boolean addCart(String userId, String SPUId, String commodityCount) {
        Map<String,Object> param = new HashMap<>();
        int uid = 0;
        int sid = 0;
        int count = 0;
        if(userId != null && !"".equals(userId) &&
        SPUId != null && !"".equals(SPUId)&&
        commodityCount != null && !"".equals(commodityCount)) {
            uid = Integer.parseInt(userId);
            sid = Integer.parseInt(SPUId);
            count = Integer.parseInt(commodityCount);
            param.put("userId",uid);
            param.put("SPUId",sid);
            param.put("commodityCount",count);
        }

        Map<String,Object> cart = cartDao.queryByUserIdAndSPUId(uid,sid);
        //仔细考虑一下，这里的判断为啥要加在Service里而不是DaoImpl里
        int i = 0;
        if(cart == null) {
           i =  cartDao.doInsert(param);
        } else{
           i =  cartDao.doUpdateCommodityCount(param);
        }
        return i == 1;
    }

    @Override
    public boolean deleteCart(String userId, List<String> SPUId) {
       if(userId != null && !"".equals(userId)){
           System.out.println("SPUID" + SPUId);
           boolean b = cartDao.doDelete(Integer.parseInt(userId),SPUId) == 1;
           System.out.println("b = " + b);
           return b;
       }
       return false;
    }
}
