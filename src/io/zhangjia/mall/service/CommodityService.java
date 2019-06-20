package io.zhangjia.mall.service;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.entity.User;
import org.omg.CORBA.INTERNAL;

import java.util.List;
import java.util.Map;

/**
 * 用户的业务层
 */
public interface CommodityService {
    List<Commodity> getCommodities(String name, String page, String firstMenuId, String secMenuId, String order);

    //	List<Commodity> getCommodities(String name);
    Integer getPagesCount(String firstMenuId, String secMenuId, String name);

    Commodity getCommodity(String firstMenuId);

    //	List<Map<String,Object>> getCommoditySPEC(String commodityId);
    String getCommoditySPEC(String commodityId);
    List<Map<String,Object>> getCommoditySKU(String skuValue);

    public Map<String, Object> updateCount2CommodityDetail(String action, String userId, String SKUId,String count,String val);

}
