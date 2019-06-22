package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.api.verificationCode.PhoneCode;
import io.zhangjia.mall.service.PhoneCodeService;

public class PhoneCodeServiceImpl implements PhoneCodeService {
    private PhoneCode codes = new PhoneCode();
    @Override
    public String getPhoneCode(String tel) {
        return codes.sendCode(tel);
    }
}
