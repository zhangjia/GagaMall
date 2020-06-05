package io.zhangjia.mall.service;

import javax.servlet.http.HttpSession;

public interface MailCodeService {
    String getMailCode(String mail);

    int isMailCodeRight(String code, HttpSession session);
}
