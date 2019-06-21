package io.zhangjia.mall.dao.impl;

import io.zhangjia.mall.dao.OrderDao;
import io.zhangjia.mall.utils.CommonDao;

import java.util.List;
import java.util.Map;

public class OrderDaoImpl extends CommonDao implements OrderDao {

    /**
     * 根据用户ID查熏她的所有订单
     * @param userId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryByUserId(Integer userId) {
        String sql = "SELECT * FROM ORDERS WHERE USER_ID = ? AND ORDER_STATUS != 0";
        return query4MapList(sql,userId);
    }

    /**
     * 根据订单ID，查询订单详情
     * @param orderId
     * @return
     */
    @Override
    public List<Map<String, Object>> queryCommodityByOrderId(Integer orderId) {
        String sql = "SELECT t2.* FROM ORDERS t1,ORDER_DETAILS t2\n" +
                "WHERE t1.ORDER_ID = t2.ORDER_ID AND t1.ORDER_ID = ? \n" +
                "AND t1.ORDER_STATUS != 0 AND t2.ORDER_DETAILS_STATUS !=0";
        return query4MapList(sql,orderId);
    }

    /**
     * 订单实际支付金额 = 运费 + 每个商品原价 * 商品数量 - 每个商品的优惠金额总和
     * @param orderId
     * @return
     */
    @Override
    public Double queryOrderPrice(Integer orderId) {
        String sql = "SELECT t2.ORDER_FREIGHT_PRICE + t1.price  sumPrice\n" +
                "FROM\n" +
                "    ( SELECT ORDER_ID,SUM(ORDER_DETAILS_COMMODITY_PRICE * ORDER_DETAILS_COMMODITY_COUNT - ORDER_DETAILS_DISCOUNTS_PRICE) price\n" +
                "      FROM ORDER_DETAILS\n" +
                "      WHERE ORDER_DETAILS_STATUS != 0\n" +
                "      GROUP BY ORDER_ID  HAVING  ORDER_ID = ?) t1,\n" +
                "    ORDERS t2\n" +
                "WHERE t1.ORDER_ID = t2.ORDER_ID\n" +
                "  AND t2.ORDER_STATUS != 0";
        return Double.parseDouble(query4Map(sql,orderId).get("SUMPRICE") + "");
    }

    /**
     * 订单中所有的商品原价总和 = 商品数量 * 商品原价
     * @param orderId
     * @return
     */
    @Override
    public Double queryOrderOriginalPrice(Integer orderId) {
        String sql ="SELECT ORDER_ID,SUM(ORDER_DETAILS_COMMODITY_PRICE * ORDER_DETAILS_COMMODITY_COUNT) original_price\n" +
                "FROM ORDER_DETAILS\n" +
                "WHERE ORDER_DETAILS_STATUS != 0\n" +
                "GROUP BY ORDER_ID  HAVING  ORDER_ID = ?";
        return Double.parseDouble(query4Map(sql,orderId).get("ORIGINAL_PRICE") + "");
    }

    /**
     * 订单优惠金额 = 每个商品的优惠金额总和
     * @param orderId
     * @return
     */
    @Override
    public Double queryOrderDiscountPrice(Integer orderId) {
        String sql = "SELECT ORDER_ID,SUM(ORDER_DETAILS_DISCOUNTS_PRICE) discount_price\n" +
                "FROM ORDER_DETAILS\n" +
                "WHERE ORDER_DETAILS_STATUS != 0\n" +
                "GROUP BY ORDER_ID  HAVING  ORDER_ID = ?";
        return Double.parseDouble(query4Map(sql,orderId).get("DISCOUNT_PRICE") + "");
    }

    /**
     * 插入订单记录
     * @param param
     * @return
     */
    @Override
    public int doInsert(Map<String, Object> param) {
        String sqlid = "SELECT seq_commodity.nextval id FROM dual";
        int id =  query4IntData(sqlid);
        System.out.println("id = " + id);
        String sql = "INSERT INTO orders VALUES(?,?,?,sysdate,?,?,?,?,1)";
        int i = executeUpdate(sql, id, param.get("userId"), param.get("address_id"), param.get("rderLogistics")
                , param.get("orderFreightPrice"), param.get("orderPayType"), param.get("orderNote"));
        if(i == 1) {
            return id;
        } else {
            return 0;
        }
    }
    /**
     * 插入订单明细
     * @param param
     * @return
     */
    @Override
    public int doInsert4Detail(Map<String, Object> param) {
        String sql = "INSERT INTO order_details VALUES(seq_order_details.nextval,?,?,?,?,?,?,?,?,1)";
        return executeUpdate(sql,
                param.get("orderId "),param.get("skuId"),
                param.get("orderDetailsCommodityName"),param.get("orderDetailsSKUValue"),
                param.get("orderDetailsCommodityImg"),param.get("orderDetailsCommodityPrice"),
                param.get("orderDetailsDiscountsPrice"),param.get("orderDetailsCommodityCount"));
    }
}
