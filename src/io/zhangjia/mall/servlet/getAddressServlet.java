package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.service.AddressService;
import io.zhangjia.mall.service.impl.AddressServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/getAddress")
public class getAddressServlet extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addressId = req.getParameter("addressId");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        Map<String, Object> address4Edit = addressService.getAddress4Edit(addressId);
        writer.println(JSON.toJSONString(address4Edit));
        System.out.println("JSON.toJSONString(address4Edit) = " + JSON.toJSONString(address4Edit));
        writer.close();
    }
}
