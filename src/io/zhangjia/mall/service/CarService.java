package io.zhangjia.mall.service;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<Map<String, Object>> getCarCommodities(String userId);
    Map<String, Object> addCart(String userId,String SKUId,String CommodityCount);
    boolean deleteCart(String userId,List<String> SKUId);
    Map<String, Object> updateCount(String action,String userId,String SKUId);
}
