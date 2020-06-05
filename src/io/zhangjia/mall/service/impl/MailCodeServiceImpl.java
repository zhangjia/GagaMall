package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.api.mailcode.MailCode;
import io.zhangjia.mall.api.verificationCode.PhoneCode;
import io.zhangjia.mall.service.MailCodeService;
import io.zhangjia.mall.service.PhoneCodeService;

import javax.servlet.http.HttpSession;

public class MailCodeServiceImpl implements MailCodeService {
    private MailCode codes = new MailCode();

    @Override
    public String getMailCode(String mail) {
        System.out.println("mailcode = " + mail);
        return codes.sample(mail);
    }

    @Override
    public int isMailCodeRight(String code, HttpSession session) {

        if (!code.equals(session.getAttribute("mailCode"))) {
            System.out.println("code00000 = " + code);
            return 0;
        } else {
            return 1;
        }

    }


}
