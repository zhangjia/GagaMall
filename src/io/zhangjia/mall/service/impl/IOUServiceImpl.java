package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.IOUDao;
import io.zhangjia.mall.dao.impl.IOUDaoImpl;
import io.zhangjia.mall.service.IOUService;

import java.util.Map;

public class IOUServiceImpl implements IOUService {
    private IOUDao iouDao = new IOUDaoImpl();
    @Override
    public Map<String, Object> queryUserIOU(String userId) {
        if(userId != null && !"".equals(userId)){
            return iouDao.queryByUserId(Integer.parseInt(userId));
        }
        return  null;
    }
}
