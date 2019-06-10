package io.zhangjia.mall.dao;

import io.zhangjia.mall.entity.Commodity;

import java.util.List;
import java.util.Map;

public interface CommodityDao {
    List<Commodity> queryCommodityImgs(List<Commodity> commodities);
    List<Commodity> queryAll();
    List<Commodity> queryLike(String name);
    List<Commodity> queryMenuId(Integer firstMenuId, Integer secMenuId);
}
