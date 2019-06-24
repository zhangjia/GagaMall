package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.utils.CommonDao;
import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CommodityDaoImpl extends CommonDao implements CommodityDao {

    public List<Commodity> queryCommodityImgs(List<Commodity> commodities) {
        for (Commodity commodity : commodities) {
            String sql1 = "SELECT img_url,img_order,img_type FROM img WHERE img_belong = ? AND img_type='商品图' AND img_is_del = 1";
            List<Map<String, Object>> maps = query4MapList(sql1, commodity.getCommodityId());
//			System.out.println(maps);
            commodity.setCommodityImg(maps);
            String sql2 = "SELECT img_url,img_order,img_type FROM img WHERE img_belong = ? AND img_type='商品详情图' AND img_is_del = 1";
            List<Map<String, Object>> maps2 = query4MapList(sql2, commodity.getCommodityId());
//			System.out.println(maps);
            commodity.setCommodityDetails(maps2);
        }
        return commodities;

    }


    @Override
    public List<Commodity> queryAll(Integer firstMenuId, Integer secMenuId, int page, int pageSize, int order) {
        String sql = "";
        List<Commodity> commodities = null;
        //查询全部
        if (firstMenuId == null && secMenuId == null) {
            /*
             * 0:默认排序
             * 1：按照销量从低到高
             * 2：按照销量从高到低排序DESC
             * 3：按照价格排序
             * 4：按照价格从高到底排序DESC
             * 5：按照时间排序
             *
             *
             * */
            switch (order) {
                case 0:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ?\n" +
                            "  AND rn <= ?";
                    break;
                case 1:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 2:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_SALES DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 3:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_MAX_PRESENT_PRICE\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 4:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_MAX_PRESENT_PRICE DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 5:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT *\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1 ORDER BY COMMODITY_CREATE_TIME DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;

            }
            commodities = query4BeanList(sql, Commodity.class, page, pageSize);
            queryCommodityImgs(commodities);
            return commodities;
        }
        if (firstMenuId == null && secMenuId != null) {
            /*
             * 0:默认排序
             * 1：按照销量从低到高
             * 2：按照销量从高到低排序DESC
             * 3：按照价格排序
             * 4：按照价格从高到底排序DESC
             * 5：按照时间排序
             *
             *
             * */
            switch (order) {
                case 0:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.SEC_MENU_ID = ?\n" +
                            "\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 1:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*, t2.*, t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                    AND t1.SEC_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_SALES \n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 2:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*, t2.*, t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                    AND t1.SEC_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_SALES DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 3:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*, t2.*, t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                    AND t1.SEC_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_MAX_PRESENT_PRICE\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 4:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*, t2.*, t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                    AND t1.SEC_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_MAX_PRESENT_PRICE DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 5:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*, t2.*, t3.SEC_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       SEC_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.SEC_MENU_ID = t1.SEC_MENU_ID\n" +
                            "                    AND t3.SEC_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                    AND t1.SEC_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_CREATE_TIME DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;

            }
            commodities = query4BeanList(sql, Commodity.class, secMenuId, page, pageSize);
            queryCommodityImgs(commodities);
            return commodities;

        }

        if (firstMenuId != null && secMenuId == null) {

            /*
             * 0:默认排序
             * 1：按照销量从低到高
             * 2：按照销量从高到低排序DESC
             * 3：按照价格排序
             * 4：按照价格从高到底排序DESC
             * 5：按照时间排序
             *
             *
             * */
            switch (order) {
                case 0:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 1:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_SALES\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ?\n" +
                            "  AND rn <= ?";
                    break;
                case 2:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_SALES DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 3:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_MAX_PRESENT_PRICE\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 4:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_MAX_PRESENT_PRICE DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;
                case 5:
                    sql = "SELECT *\n" +
                            "FROM (\n" +
                            "         SELECT ROWNUM rn, c.*\n" +
                            "         FROM (\n" +
                            "                  SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME\n" +
                            "                  FROM COMMODITY t1,\n" +
                            "                       (SELECT COMMODITY_ID                            cid,\n" +
                            "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                            "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                            "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                            "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                            "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                            "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                            "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                            "                        FROM SKU\n" +
                            "                        WHERE SKU_IS_DEL = 1\n" +
                            "                        GROUP BY COMMODITY_ID) t2,\n" +
                            "                       FIRST_MENU t3\n" +
                            "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                            "                    AND t3.FIRST_MENU_ID = t1.FIRST_MENU_ID\n" +
                            "                    AND t3.FIRST_MENU_IS_DEL = 1\n" +
                            "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                            "                  AND t1.FIRST_MENU_ID = ?\n" +
                            "                  ORDER BY COMMODITY_CREATE_TIME DESC\n" +
                            "              ) c\n" +
                            "     )\n" +
                            "WHERE rn > ? AND rn <= ?";
                    break;

            }
            commodities = query4BeanList(sql, Commodity.class, firstMenuId, page, pageSize);
            queryCommodityImgs(commodities);
            return commodities;

        }


        return null;
    }


    @Override
    public List<Commodity> queryLike(String name, int page, int pageSize, int order) {
//		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER('%?%')";
//		String sql = "SELECT * FROM commodities WHERE UPPER(commodity_name) LIKE UPPER(?)";

        System.out.println(page + "////" + pageSize);

        String sql = "";
        switch (order) {
            case 0:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?)) c) WHERE rn > ? AND rn <= ?";
                break;
            case 1:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?) ORDER BY COMMODITY_SALES) c) WHERE rn > ? AND rn <= ? ";
                break;
            case 2:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?) ORDER BY COMMODITY_SALES DESC) c) WHERE rn > ? AND rn <= ? ";
                break;
            case 3:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?) ORDER BY COMMODITY_MAX_PRESENT_PRICE) c) WHERE rn > ? AND rn <= ? ";
                break;
            case 4:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?) ORDER BY COMMODITY_MAX_PRESENT_PRICE DESC) c) WHERE rn > ? AND rn <= ? ";
                break;
            case 5:
                sql = "SELECT *\n" +
                        "FROM (\n" +
                        "         SELECT ROWNUM rn, c.*\n" +
                        "         FROM (\n" +
                        "                  SELECT *\n" +
                        "                  FROM COMMODITY t1,\n" +
                        "                       (SELECT COMMODITY_ID                            cid,\n" +
                        "                               SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                        "                               SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                        "                               MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                        "                               MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                        "                               MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                        "                               MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                        "                               MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                        "                        FROM SKU\n" +
                        "                        WHERE SKU_IS_DEL = 1\n" +
                        "                        GROUP BY COMMODITY_ID) t2\n" +
                        "                  WHERE t1.COMMODITY_ID = t2.cid\n" +
                        "                    AND t1.COMMODITY_IS_DEL = 1\n" +
                        "                    AND UPPER(t1.COMMODITY_NAME) LIKE UPPER(?) ORDER BY COMMODITY_CREATE_TIME) c) WHERE rn > ? AND rn <= ? ";

                break;

        }

        return queryCommodityImgs(query4BeanList(sql, Commodity.class, "%" + name + "%", page, pageSize));


    }


    @Override
    public Integer queryCommodityCount(Integer firstMenuId, Integer secMenuId, String name) {

        String sql = "";
        if (firstMenuId == null && secMenuId == null && name == null) {
            sql = "SELECT count(*) FROM COMMODITY WHERE  COMMODITY_IS_DEL = 1";
            return query4IntData(sql);
        } else if (firstMenuId != null) {
            sql = "SELECT count(*) FROM COMMODITY WHERE  COMMODITY_IS_DEL = 1 AND FIRST_MENU_ID = ?";
            return query4IntData(sql, firstMenuId);
        } else if (secMenuId != null) {
            sql = "SELECT count(*) FROM COMMODITY WHERE  COMMODITY_IS_DEL = 1 AND SEC_MENU_ID = ?";
            return query4IntData(sql, secMenuId);
        } else {
            sql = "SELECT count(*) FROM COMMODITY WHERE  COMMODITY_IS_DEL = 1 AND UPPER(commodity_name) LIKE UPPER(?) ";
            return query4IntData(sql, "%" + name + "%");
        }


    }

    @Override
    public Commodity queryCommodity(String commodityId) {
        String sql = "SELECT *\n" +
                "FROM (\n" +
                "         SELECT t1.*,t2.*,t3.FIRST_MENU_CHINESE_NAME,t4.SEC_MENU_CHINESE_NAME\n" +
                "         FROM COMMODITY t1,\n" +
                "              (SELECT COMMODITY_ID                            cid,\n" +
                "                      SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                "                      SUM(SKU_INVENTORY)                       COMMODITY_INVENTORY,\n" +
                "                      MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                "                      MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                "                      MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                "                      MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                "                      MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                "               FROM SKU\n" +
                "               WHERE SKU_IS_DEL = 1\n" +
                "               GROUP BY COMMODITY_ID) t2,\n" +
                "               FIRST_MENU t3,\n" +
                "            SEC_MENU t4\n" +
                "         WHERE t1.COMMODITY_ID = t2.cid\n" +
                "           AND t1.COMMODITY_IS_DEL = 1\n" +
                "           AND t3.FIRST_MENU_IS_DEL = 1\n" +
                "           AND t4.SEC_MENU_IS_DEL = 1\n" +
                "           AND t1.FIRST_MENU_ID = t3.FIRST_MENU_ID\n" +
                "           AND t1.SEC_MENU_ID = t4.SEC_MENU_ID\n" +
                "           AND t1.COMMODITY_ID = ?\n" +
                "     )";
        List<Commodity> c = new ArrayList<>();
        c.add(query4Bean(sql, Commodity.class, commodityId));
        List<Commodity> commodities = queryCommodityImgs(c);
        return commodities.get(0);
    }

    @Override
    public List<Map<String, Object>> querySPEC(Integer commodityId) {
//		String sql = "SELECT SKU_VALUE,COMMODITY_ID   FROM SKU  WHERE COMMODITY_ID = 1 AND SKU_IS_DEL = ?";
        String sql = "SELECT COMMODITY_ATTRIBUTES,COMMODITY_ID FROM COMMODITY WHERE COMMODITY_ID = ?";
        return query4MapList(sql, commodityId);
    }

    @Override
    public List<Map<String, Object>> querySKU(String skuValue) {
        String sql = "SELECT * FROM SKU WHERE SKU_VALUE = ? AND SKU_IS_DEL = 1";
        return query4MapList(sql, skuValue);
    }

    @Override
    public int doCommodityInsert(Commodity commodity) {
        String sqlid = "SELECT seq_commodity.nextval id FROM dual";
        int id =  query4IntData(sqlid);
        System.out.println("id = " + id);
        String sql = "INSERT INTO commodity VALUES(?,?,?,sysdate,sysdate,100,?,?,1)";
        int i = executeUpdate(sql,id, commodity.getCommodityName(), commodity.getCommodityAttributes(),
                commodity.getFirstMenuId(), commodity.getSecMenuId());
        if(i == 1) {
            return id;
        } else {
            return 0;
        }

    }

    @Override
    public int doSKUInsert(List<Map<String, Object>> lists) {
        int i = 1;
        for (Map<String, Object> list : lists) {
			String sql = "INSERT INTO sku VALUES(seq_sku.nextval,1,?,0,0,?,?,0,sysdate,sysdate,1)";
			i *= executeUpdate(sql);
        }

        return -1;
    }

    @Override
    public List<Commodity> queryCommodity4Index() {
//        String sql = "SELECT  * FROM  COMMODITY WHERE  COMMODITY.COMMODITY_IS_DEL != 0";
        String sql = "SELECT *\n" +
                "FROM COMMODITY t1,\n" +
                "     (SELECT COMMODITY_ID                            cid,\n" +
                "             SUM(SKU_SALES)                          COMMODITY_SALES,\n" +
                "             SUM(SKU_INVENTORY)                      COMMODITY_INVENTORY,\n" +
                "             MAX(SKU_PRESENT_PRICE)                  COMMODITY_MAX_PRESENT_PRICE,\n" +
                "             MIN(SKU_PRESENT_PRICE)                  COMMODITY_MIN_PRESENT_PRICE,\n" +
                "             MAX(SKU_ORIGINAL_PRICE)                 COMMODITY_MAX_ORIGINAL_PRICE,\n" +
                "             MIN(SKU_ORIGINAL_PRICE)                 COMMODITY_MIN_ORIGINAL_PRICE,\n" +
                "             MAX(SKU_LAST_PRICE - SKU_PRESENT_PRICE) COMMODITY_MAX_MARK_DOWN\n" +
                "      FROM SKU\n" +
                "      WHERE SKU_IS_DEL = 1\n" +
                "      GROUP BY COMMODITY_ID) t2\n" +
                "WHERE t1.COMMODITY_ID = t2.cid\n" +
                "  AND t1.COMMODITY_IS_DEL = 1 ORDER BY  t1.COMMODITY_CREATE_TIME DESC\n";
        List<Commodity> commodities = query4BeanList(sql, Commodity.class);
        queryCommodityImgs(commodities);
        return commodities;
    }


}
