package io.zhangjia.mall.service;

import io.zhangjia.mall.entity.FirstMenu;

import java.util.List;
import java.util.Map;

public interface NavService {
   /* List<Map<String, Object>> getFirstNav();
    List<Map<String, Object>> getSecNav();*/
    List<FirstMenu> getNav();


    String getFirstMenuChineseName(String fid);
}
