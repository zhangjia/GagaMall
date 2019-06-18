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
        return query4MapList(sql, userId);
    }

    @Override
    public int doInsert(Map<String, Object> param) {


        String sql = "INSERT INTO cart VALUES(seq_cart.nextval,?,?,?,1)";
        int i = executeUpdate(sql, param.get("userId"), param.get("SPUId"), param.get("commodityCount"));

        return i;
    }

    @Override
    public Map<String, Object> queryByUserIdAndSPUId(Integer userId, Integer SPUId) {
        String sql = "SELECT  * FROM CART WHERE USER_ID = ? AND SPU_ID = ?";
        return query4Map(sql, userId,SPUId);
    }

    @Override
    public int doUpdateCommodityCount(Map<String, Object> param) {
        String sql = "UPDATE CART SET COMMODITY_COUNT = COMMODITY_COUNT + ? WHERE USER_ID = ? AND SPU_ID = ?";
        int i = executeUpdate(sql,param.get("commodityCount"),param.get("userId"),param.get("SPUId"));
        return i;
    }

    @Override
    public int doDelete(Integer userId, List<String> SPUId) {
        int i = 1;
        for (String sid: SPUId) {
            String sql = "DELETE FROM CART WHERE USER_ID = ? AND SPU_ID = ?";
            System.out.println("sid" + sid);
            i *= executeUpdate(sql,userId,sid);
        }
        return i;
    }


}
