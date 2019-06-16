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
    List<Commodity> queryCommodities(String name, String page, String firstMenuId, String secMenuId, String order);

    //	List<Commodity> queryCommodities(String name);
    Integer queryPagesCount(String firstMenuId, String secMenuId, String name);

    Commodity queryCommodity(String firstMenuId);

    //	List<Map<String,Object>> queryCommoditySPEC(String commodityId);
    String queryCommoditySPEC(String commodityId);
    List<Map<String,Object>> queryCommoditySPU(String spuValue);

}
