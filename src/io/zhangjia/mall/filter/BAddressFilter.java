package io.zhangjia.mall.filter;


import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.service.AddressService;
import io.zhangjia.mall.service.impl.AddressServiceImpl;
import io.zhangjia.mall.utils.HttpFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebFilter(urlPatterns = {"/address", "/settlement"}, filterName = "b")
public class BAddressFilter extends HttpFilter {
    private AddressService addressService = new AddressServiceImpl();

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        System.out.println("user.getUserId()+\"\" = " + user.getUserId() + "");
        List<Map<String, Object>> userAddress = addressService.getUserAddress(user.getUserId() + "");
        System.out.println("userAddress2345 = " + JSON.toJSONString(userAddress));
        request.setAttribute("userAddress", userAddress);
        filterChain.doFilter(request, response);
    }


}
