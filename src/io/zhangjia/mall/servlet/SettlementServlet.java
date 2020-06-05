package io.zhangjia.mall.servlet;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.AddressService;
import io.zhangjia.mall.service.CarService;
import io.zhangjia.mall.service.impl.AddressServiceImpl;
import io.zhangjia.mall.service.impl.CartServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@WebServlet("/settlement")
public class SettlementServlet extends HttpServlet {
    private AddressService addresservice = new AddressServiceImpl();
    private CarService carService = new CartServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        String[] commoditySKUIds = req.getParameterValues("SKUIds");
        System.out.println("commoditySKUIds = " + commoditySKUIds);
        System.out.println("Arrays.toString(commoditySKUIds) = " + Arrays.toString(commoditySKUIds));
        List<Map<String, Object>> userAddress = addresservice.getUserAddress(user.getUserId() + "");
        System.out.println("userAddress = " + userAddress);
        List<Map<String, Object>> commoditySKUS = carService.getCarCommodities4Settlement(user.getUserId() + "", commoditySKUIds);
        System.out.println("commoditySKUs = " + JSON.toJSONString(commoditySKUS));

        Map<String, Object> total = carService.getTotal(user.getUserId() + "", commoditySKUIds);
        System.out.println("total = " + total);

        System.out.println("commoditySKUSsss = " + JSON.toJSONString(commoditySKUS));
        req.setAttribute("userAddress", userAddress);
        req.setAttribute("commoditySKUS", commoditySKUS);
        req.setAttribute("total", total);


        req.getRequestDispatcher("/WEB-INF/views/settlement.jsp").forward(req, resp);
    }
}
