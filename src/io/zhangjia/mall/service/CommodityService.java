package io.zhangjia.mall.service;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.entity.User;

import java.util.List;
import java.util.Map;

/**
 * 用户的业务层
 *
 */
public interface CommodityService {
	List<Commodity> queryCommodities(String name,Integer firstMenuId,Integer secMenuId);
//	List<Commodity> queryCommodities(String name);
}
