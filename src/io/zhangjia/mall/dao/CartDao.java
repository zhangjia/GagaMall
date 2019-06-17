package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<Map<String,Object>> queryByUserId(Integer userId);
}
