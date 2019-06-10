package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.FirstMenuDao;
import io.zhangjia.mall.dao.SecMenuDao;
import io.zhangjia.mall.dao.impl.FirstMenuDaoImpl;
import io.zhangjia.mall.dao.impl.SecMenuDaoImpl;
import io.zhangjia.mall.service.NavService;

import java.util.List;
import java.util.Map;

public class NavServiceImpl  implements NavService {
    private FirstMenuDao firstMenuDao = new FirstMenuDaoImpl();
    private SecMenuDao secMenuDao = new SecMenuDaoImpl();
    @Override
    public List<Map<String, Object>> getFirstNav() {
        return firstMenuDao.queryFirstMenu();
    }

    @Override
    public List<Map<String, Object>> getSecNav() {
        return secMenuDao.querySecMenu();
    }
}
