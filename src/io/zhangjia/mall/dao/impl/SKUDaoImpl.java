package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.utils.CommonDao;

public class SKUDaoImpl extends CommonDao implements SKUDao {
    @Override
    public int querySKUInventory(Integer SKUId) {
        String sql = "SELECT SKU_INVENTORY FROM SKU  WHERE  SKU_ID = ?";
        return query4IntData(sql,SKUId);
    }
}
