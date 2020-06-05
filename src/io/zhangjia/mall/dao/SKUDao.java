package io.zhangjia.mall.dao;

import io.zhangjia.mall.entity.SKU;

public interface SKUDao {
    int querySKUInventory(Integer SKUId);

    int doInsert(SKU sku);

    int updateInventoryAndSales(Integer SKUId, Integer count);
}
