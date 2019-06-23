package io.zhangjia.mall.dao;

import java.util.Map;

public interface IOUDao {
    Map<String,Object> queryByUserId(Integer userId);
}
