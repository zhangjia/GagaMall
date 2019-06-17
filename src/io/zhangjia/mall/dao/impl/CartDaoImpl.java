package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class CartDaoImpl extends CommonDao implements CartDao {

    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT *\n" +
                "FROM SPU t1,\n" +
                "     COMMODITY t2,\n" +
                "     cart t3,\n" +
                "     img t4\n" +
                "WHERE t1.COMMODITY_ID = t2.COMMODITY_ID\n" +
                "  AND t3.SPU_ID = t1.SPU_ID\n" +
                "  AND t3.USER_ID = ?\n" +
                "AND t4.IMG_BELONG = t1.COMMODITY_ID AND t4.IMG_ORDER = 1 AND t4.IMG_TYPE='商品图'";
        return query4MapList(sql,userId);
    }
}
