package io.zhangjia.mall.service;

import javax.servlet.http.HttpSession;

public interface PhoneCodeService {
    String getPhoneCode(String tel);
    int isPhoneCodeRight(String code, HttpSession session);
}
