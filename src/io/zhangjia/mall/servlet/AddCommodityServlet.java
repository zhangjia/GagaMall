package io.zhangjia.mall.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/addCommodity")
public class AddCommodityServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String,Object> map = new HashMap<>();
        //商品规格
        String[] spggs = req.getParameterValues("spgg");

        //商品属性
        String[] spsxes = req.getParameterValues("spsx");
        String[] spts = req.getParameterValues("spt");
        String[] spxqts = req.getParameterValues("spxqt");
//{"颜色":"白色","容量":"256G"}
//        {"颜色":["黑色","白色"],"容量":["128G","256G"]}

        List<String> skuValue = new ArrayList<>();
        List<String> skuInventory = new ArrayList<>();
        List<String> skuPresentPrice = new ArrayList<>();
//        List<Map<String,Object>> attr = new ArrayList<>();

        //存商品的attribute
        Map<String,List<String>> attr = new HashMap<>();
        for (int i = 0; i < spsxes.length; i++) {
            List<String> attr1 = new ArrayList<>();
//            attr

//            temp.put(req.getParameter("spgg"+i+""),req.getParameter("spss"+i+""));
            skuValue.add(req.getParameter("sku-value"+i+""));
            skuInventory.add(req.getParameter("sku-inventory"+i+""));
            skuPresentPrice.add(req.getParameter("sku-present-price"+i+""));
        }
        System.out.println(skuValue);
        System.out.println(skuInventory);
        System.out.println(skuPresentPrice);
        List<String> spsx= new ArrayList<String>(Arrays.asList(spsxes));
        List<String> spgg= new ArrayList<String>(Arrays.asList(spggs));
        List<String> spt= new ArrayList<String>(Arrays.asList(spts));
        List<String> spxqt= new ArrayList<String>(Arrays.asList(spxqts));
        System.out.println(Arrays.toString(spsxes));
        System.out.println(Arrays.toString(spggs));
        System.out.println(Arrays.toString(spts));
        System.out.println(Arrays.toString(spxqts));
        System.out.println("--------------------");
        map.put("skuValue",skuValue);
        map.put("skuInventory",skuInventory);
        map.put("skuValue",skuValue);
        map.put("skuPresentPrice",skuPresentPrice);
        map.put("spgg",spgg);
        map.put("spt",spt);
        map.put("spxqt",spxqt);
        map.put("spsx",spsx);
        map.put("commodityName",req.getParameter("commodityName"));
        map.put("firstMenuId",req.getParameter("firstMenuId"));
        map.put("secMenuId",req.getParameter("secMenuId"));
        System.out.println(map);
        /*
        * 商品表: 商品名，商品属性值，一级菜单，二级菜单
        * key表
        * value表
        * sku表：sku的值，价格，库存，商品ID，
        *
        * ***********************************
        * {"颜色":"白色","容量":"256G"}
        *{"颜色":["黑色","白色"],"容量":["128G","256G"]}
        * 颜色：黄色
        ************************************
        *
        *
        *
        *
        *
        * */

    }

}
