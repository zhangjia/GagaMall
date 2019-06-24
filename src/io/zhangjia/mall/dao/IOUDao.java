package io.zhangjia.mall.dao;

import java.util.Map;

public interface IOUDao {
    Map<String,Object> queryByUserId(Integer userId);
    int doUpdate(Integer userId, Double money);
    int doInsert(Integer userId);
}
