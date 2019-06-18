package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface CartDao {
    List<Map<String,Object>> queryByUserId(Integer userId);
    int doInsert(Map<String,Object> param);
    Map<String,Object> queryByUserIdAndSPUId(Integer userId, Integer SPUId);
    int doUpdateCommodityCount(Map<String,Object> param);
    int doDelete(Integer userId,List<String> SPUId);
}
