package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<Map<String,Object>> queryByUserId(Integer userId);
    int doInsert(Map<String,Object> param);
    Map<String,Object> queryByUserIdAndSKUId(Integer userId, Integer SKUId);
    int doUpdateCommodityCount(Map<String,Object> param);
    int doDelete(Integer userId,List<String> SKUId);

    int addCount(Integer userId, Integer SKUId);
    int subCount(Integer userId, Integer SKUId);
}
