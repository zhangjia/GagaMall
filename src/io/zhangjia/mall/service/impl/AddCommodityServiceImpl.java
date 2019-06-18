package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.service.AddCommodityService;

import java.util.Map;

public class AddCommodityServiceImpl implements AddCommodityService {

    @Override
    public Map<String, Object> addCommodity(Map<String, Object> maps) {
        //1.添加商品
        Commodity commodity = new Commodity();
        commodity.setCommodityName((String)maps.get("commodityName"));
        commodity.setFirstMenuId(Integer.parseInt((String)maps.get("firstMenuId")));
        commodity.setSecMenuId(Integer.parseInt((String)maps.get("secMenuId")));




//        for (String key:maps.keySet()) {
//
//        }
        return null;
    }
}
