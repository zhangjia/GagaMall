package io.zhangjia.mall.service;

import java.util.List;
import java.util.Map;

public interface CarService {
    List<Map<String, Object>> getCarCommodities(String userId);
    boolean addCart(String userId,String SPUId,String CommodityCount);
    boolean deleteCart(String userId,List<String> SPUId);
}
