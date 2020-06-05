package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.IOUDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.Map;

public class IOUDaoImpl extends CommonDao implements IOUDao {
    @Override
    public Map<String, Object> queryByUserId(Integer userId) {
        String sql = "SELECT  * FROM IOU WHERE USER_ID = ?";
        return query4Map(sql, userId);
    }

    @Override
    public int doUpdate(Integer userId, Double money) {
        String sql = "UPDATE IOU SET IOU_USABLE_LIMIT = IOU_USABLE_LIMIT +  ? WHERE USER_ID = ?";
        return executeUpdate(sql, money, userId);
    }

    @Override
    public int doInsert(Integer userId) {
        String sql = "INSERT INTO iou VALUES(seq_iou.nextval,5000.0,5000,?)";
        return executeUpdate(sql, userId);
    }
}
