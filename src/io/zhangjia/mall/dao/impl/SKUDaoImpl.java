package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.entity.SKU;
import io.zhangjia.mall.utils.CommonDao;

public class SKUDaoImpl extends CommonDao implements SKUDao {
    @Override
    public int querySKUInventory(Integer SKUId) {
        String sql = "SELECT SKU_INVENTORY FROM SKU  WHERE  SKU_ID = ?";
        return query4IntData(sql,SKUId);
    }

    @Override
    public int doInsert(SKU sku) {
        String sql ="INSERT INTO sku VALUES(seq_sku.nextval,?,?,0,0,?,?,0,sysdate,sysdate,1)";
        return executeUpdate(sql,sku.getCommodityId(),sku.getSkuValue(),
                sku.getPresentPrice(),sku.getSkuInventory());
    }
}
