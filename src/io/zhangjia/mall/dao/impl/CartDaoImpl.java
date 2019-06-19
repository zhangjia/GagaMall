package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.CartDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class CartDaoImpl extends CommonDao implements CartDao {

    /**
     * 查询单个用户的购物车所有商品
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT *\n" +
                "FROM SKU t1,\n" +
                "     COMMODITY t2,\n" +
                "     cart t3,\n" +
                "     img t4\n" +
                "WHERE t1.COMMODITY_ID = t2.COMMODITY_ID\n" +
                "  AND t3.SKU_ID = t1.SKU_ID\n" +
                "  AND t3.USER_ID = ?\n" +
                "AND t4.IMG_BELONG = t1.COMMODITY_ID AND t4.IMG_ORDER = 1 AND t4.IMG_TYPE='商品图'";
        return query4MapList(sql, userId);
    }

    /**
     * 向单个用户的购物车添加商品
     * @param param
     * @return
     */
    @Override
    public int doInsert(Map<String, Object> param) {


        String sql = "INSERT INTO cart VALUES(seq_cart.nextval,?,?,?,1)";
        int i = executeUpdate(sql, param.get("userId"), param.get("SKUId"), param.get("commodityCount"));

        return i;
    }

    /**
     * 根据用户的id和商品sku的id，判断购物车里是否有这个商品，有的话，就更新，没有的话，就添加
     * @param userId
     * @param SKUId
     * @return
     */
    @Override
    public Map<String, Object> queryByUserIdAndSKUId(Integer userId, Integer SKUId) {
        String sql = "SELECT  * FROM CART WHERE USER_ID = ? AND SKU_ID = ?";
        return query4Map(sql, userId,SKUId);
    }

    /**
     *  加入购物车的时候，如果该商品已经存在，调用这个方法更新用户的购物车商品数量
     * @param param
     * @return
     */
    @Override
    public int doUpdateCommodityCount(Map<String, Object> param) {
        String sql = "UPDATE CART SET COMMODITY_COUNT = COMMODITY_COUNT + ? WHERE USER_ID = ? AND SKU_ID = ?";
        int i = executeUpdate(sql,param.get("commodityCount"),param.get("userId"),param.get("SKUId"));
        return i;
    }

    /**
     * 删除购物车中的商品，可以删除一个或者多个
     * @param userId
     * @param SKUId
     * @return
     */
    @Override
    public int doDelete(Integer userId, List<String> SKUId) {
        int i = 1;
        for (String sid: SKUId) {
            String sql = "DELETE FROM CART WHERE USER_ID = ? AND SKU_ID = ?";
            System.out.println("sid" + sid);
            i *= executeUpdate(sql,userId,sid);
        }
        return i;
    }

    /**
     * 点击+号按钮修改购物车的商品数量
     * @param userId
     * @param SKUId
     * @return
     */
    @Override
    public int addCount(Integer userId, Integer SKUId) {
        String sql = "UPDATE CART SET COMMODITY_COUNT = COMMODITY_COUNT + 1 WHERE USER_ID = ? AND SKU_ID = ?";
        return executeUpdate(sql,userId,SKUId);
    }

    /**
     * 点击-号按钮修改购物车的商品数量
     * @param userId
     * @param SKUId
     * @return
     */

    @Override
    public int subCount(Integer userId, Integer SKUId) {
        String sql = "UPDATE CART SET COMMODITY_COUNT = COMMODITY_COUNT - 1 WHERE USER_ID = ? AND SKU_ID = ?";
        return executeUpdate(sql,userId,SKUId);
    }


}
