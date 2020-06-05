package io.zhangjia.mall.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.zhangjia.mall.dao.CommodityDao;
import io.zhangjia.mall.dao.ImgDao;
import io.zhangjia.mall.dao.SKUDao;
import io.zhangjia.mall.dao.impl.CommodityDaoImpl;
import io.zhangjia.mall.dao.impl.ImgDaoImpl;
import io.zhangjia.mall.dao.impl.SKUDaoImpl;
import io.zhangjia.mall.entity.Commodity;
import io.zhangjia.mall.entity.SKU;
import io.zhangjia.mall.service.AddCommodityService;

import java.util.*;

public class AddCommodityServiceImpl implements AddCommodityService {
    private CommodityDao commodityDao = new CommodityDaoImpl();
    private SKUDao skuDao = new SKUDaoImpl();
    private ImgDao imgDao = new ImgDaoImpl();

    @Override
    public Map<String, Object> addCommodity(Map<String, Object> maps) {
        /*思路整理
         * 先判断key是否存在，存在不动，不存在插入
         * 再判断false是否存在，存在不动，不存在插入
         * 先在商品表插入，返回商品ID
         * 然后根据商品Id，插入图片和详情图
         * 再插入sku表
         * */
        /*---------------------------------插入商品开始------------------------------------*/
        Commodity commodity = new Commodity();
        commodity.setCommodityName(maps.get("commodityName") + "");
        commodity.setCommodityAttributes(maps.get("jsonAttr") + "");
        commodity.setFirstMenuId(Integer.parseInt(maps.get("firstMenuId") + ""));
        commodity.setSecMenuId(Integer.parseInt(maps.get("sectMenuId") + ""));
        int i1 = commodityDao.doCommodityInsert(commodity);
        System.out.println(i1);
        /*---------------------------------插入商品结束-----------------------------------*/

        /*---------------------------------插入SKU开始------------------------------------*/
        JSONArray skuRecords = JSON.parseArray(maps.get("skuRecords") + "");

        System.out.println("skuRecords = " + skuRecords);
        for (Object skrs : skuRecords) {
            System.out.println("srrs" + skrs.getClass());
//            Map<String,String> m = (Map<String,String>)skrs;
            JSONObject m = (JSONObject) skrs;
            SKU sku = new SKU();
            sku.setCommodityId(i1);
            sku.setSkuValue(m.getString("skuvalue"));
//            sku.setPresentPrice(Double.parseDouble(m.get("price")+""));
            sku.setPresentPrice(m.getDouble("price"));
//            sku.setSkuInventory(Integer.parseInt(m.get("inventory")+""));
            sku.setSkuInventory(m.getInteger("inventory"));
            System.out.println(sku);
            skuDao.doInsert(sku);
        }



        /*---------------------------------插入SKU结束------------------------------------*/

        /*---------------------------------获取所有图片开始------------------------------------*/
        //将其转换为Java对象
        JSONObject jsonObject = JSON.parseObject(maps.get("spt") + "");
        JSONArray jy = (JSONArray) jsonObject.get("spt");
        List<String> spt = new ArrayList<>();
        for (int i = 0; i < jy.size(); i++) {
            spt.add(jy.get(i) + "");
        }
        int order = 0;
        for (String j : spt) {
            imgDao.doInsert(j, i1, ++order, "商品图");

        }



        /*---------------------------------获取所有图片结束------------------------------------*/

        /*---------------------------------获取所有详情图开始------------------------------------*/
        //将其转换为Java对象
        System.out.println(maps + "maps");
        JSONObject jsonObject2 = JSON.parseObject(maps.get("spxqt") + "");
        JSONArray jy2 = (JSONArray) jsonObject2.get("spxqt");
        List<String> spxqt = new ArrayList<>();
        for (int i = 0; i < jy2.size(); i++) {
            spxqt.add(jy2.get(i) + "");
        }
        System.out.println(spxqt.size());
        for (String s : spxqt
        ) {
            System.out.print(s + "--");

        }
        int order2 = 0;
        for (String j : spxqt) {
            imgDao.doInsert(j, i1, ++order2, "商品详情图");

        }
        /*---------------------------------获取所有详情图结束------------------------------------*/


        return null;
    }
}
