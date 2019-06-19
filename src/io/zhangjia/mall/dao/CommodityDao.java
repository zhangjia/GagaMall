package io.zhangjia.mall.dao;

import io.zhangjia.mall.entity.Commodity;

import java.util.List;
import java.util.Map;

public interface CommodityDao {
    List<Commodity> queryCommodityImgs(List<Commodity> commodities);
    List<Commodity> queryAll(Integer firstMenuId,Integer secMenuId,int page,int pageSize,int order);
    List<Commodity> queryLike(String name,int page,int pageSize,int order);
    Integer queryCommodityCount(Integer firstMenuId,Integer secMenuId,String name);
    Commodity queryCommodity(String commodityId);

    List<Map<String,Object>> querySPEC(Integer commodityId);

    List<Map<String,Object>> querySKU(String skuValue);

    int doCommodityInsert(Commodity commodity);
    int doSKUInsert(List<Map<String,Object>> lists) ;


}
