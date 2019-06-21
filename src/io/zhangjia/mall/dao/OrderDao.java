package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface OrderDao {
    List<Map<String,Object>> queryByUserId(Integer userId);
    List<Map<String,Object>> queryCommodityByOrderId(Integer orderId);
    Double queryOrderPrice(Integer orderId);
    Double queryOrderOriginalPrice(Integer orderId);
    Double queryOrderDiscountPrice(Integer orderId);

    int doInsert(Map<String, Object> param);

    int doInsert4Detail(Map<String, Object> param);

}
