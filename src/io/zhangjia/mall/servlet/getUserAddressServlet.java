package io.zhangjia.mall.servlet;

import io.zhangjia.mall.dao.AddressDao;
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
import java.util.List;
import java.util.Map;

@WebServlet("/address2")
public class getUserAddressServlet extends HttpServlet {
    private AddressService addressService = new AddressServiceImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
//        Map<String,Object> user = (Map<String,Object>)session.getAttribute("user");
        User user = (User)session.getAttribute("user");
        List<Map<String, Object>> userAddress = addressService.getUserAddress(user.getUserId()+"");
        System.out.println("userAddress = " + userAddress);
        req.setAttribute("userAddress",userAddress);
        req.getRequestDispatcher("/WEB-INF/views/address.jsp").forward(req,resp);

    }
}
