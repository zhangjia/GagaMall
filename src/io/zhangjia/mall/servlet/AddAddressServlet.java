package io.zhangjia.mall.servlet;

import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.AddressService;
import io.zhangjia.mall.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//看清楚了张大嘎，这个是是Add  Address ,不是address
@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
        User user = (User) session.getAttribute("user");
        Map<String, Object> maps = new HashMap<>();
        maps.put("userId", user.getUserId());
        maps.put("name", req.getParameter("name"));
        maps.put("postcode", req.getParameter("postcode"));
        maps.put("tel", req.getParameter("tel"));
        maps.put("province", req.getParameter("province"));
        maps.put("city", req.getParameter("city"));
        maps.put("district", req.getParameter("district"));
        maps.put("country", req.getParameter("country"));
        maps.put("detailedAddress", req.getParameter("detailedAddress"));
        maps.put("addressId", req.getParameter("addressId"));
        boolean result = addressService.addUserAddress(maps);
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        writer.println("{\"success\":" + result + "}");
        writer.close();
    }
}
