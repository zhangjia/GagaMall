package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.api.verificationCode.PhoneCode;
import io.zhangjia.mall.service.PhoneCodeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PhoneCodeServiceImpl implements PhoneCodeService {
    private PhoneCode codes = new PhoneCode();
    @Override
    public String getPhoneCode(String tel) {
        System.out.println("teljia = " + tel);
        return codes.sendCode(tel);
    }

    @Override
    public int isPhoneCodeRight(String code,HttpSession session) {
        //        判断是不是手机注册


            if(!code.equals(session.getAttribute("phoneCode"))){
                System.out.println("code00000 = " + code);
                return 0;
            } else {
                return 1;
            }

    }
}
