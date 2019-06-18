package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.CommodityService;
import io.zhangjia.mall.service.impl.CartServiceImpl;
import io.zhangjia.mall.service.impl.CommodityServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

@WebServlet("/addCart")
public class AddCartServlet extends HttpServlet {
    private CarService carService = new CartServiceImpl();
    private CommodityService commodityService = new CommodityServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        //先根据json字符串判断是哪个spu表中的商品，并获取该商品的ID
        List<Map<String, Object>> spu = commodityService.getCommoditySPU(req.getParameter("SPU"));
        System.out.println("spu " + spu + "---" + spu.getClass());
        /*resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        boolean b = false;
        if(spu.size() > 0) {
            String spuId = spu.get(0).get("SPU_ID") + "";
            System.out.println("spuId = " + spuId);
             b = carService.addCart(user.getUserId() + "", spuId, req.getParameter("commodityCount"));
            System.out.println("addcaft" + user.getUserId() + "--" + req.getParameter("SPUId") + "--" + req.getParameter("commodityCount"));
            writer.println("{\"success\":"+b+"}");
//            writer.println(JSON.toJSONString(spu));
        }*/
//        writer.println("{\"success\":"+b+"}");
        /*思路整理
        * 1.当登录的时候，直接访问http://localhost:8888/ga/addCart?SPUId=3&commodityCount=10
        * 直接访问，被filter拦截，不是ajax，所以直接被放行，进入该Servlet，输出false
        *
        * 当未登录的时候，直接访问http://localhost:8888/ga/addCart?SPUId=3&commodityCount=10
        *被filter重定向到http://localhost:8888/ga/login?uri=/ga/addCart?SPUId=3&commodityCount=10
        * 登录后，回到1
        * */
//        writer.println("{\"error\":"+b+"}");


        boolean b = false;
        String spuId = spu.get(0).get("SPU_ID") + "";
        b = carService.addCart(user.getUserId() + "", spuId, req.getParameter("commodityCount"));
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":"+b+"}");
        writer.close();



//        writer.close();
    }
}
