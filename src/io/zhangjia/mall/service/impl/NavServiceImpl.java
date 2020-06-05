package io.zhangjia.mall.service.impl;

import com.alibaba.fastjson.JSON;
import io.zhangjia.mall.dao.FirstMenuDao;
import io.zhangjia.mall.dao.SecMenuDao;
import io.zhangjia.mall.dao.impl.FirstMenuDaoImpl;
import io.zhangjia.mall.dao.impl.SecMenuDaoImpl;
import io.zhangjia.mall.entity.FirstMenu;
import io.zhangjia.mall.service.NavService;

import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NavServiceImpl implements NavService {
    private FirstMenuDao firstMenuDao = new FirstMenuDaoImpl();
    private SecMenuDao secMenuDao = new SecMenuDaoImpl();

//    @Override
//    public List<Map<String, Object>> getFirstNav() {
//        return firstMenuDao.queryFirstMenu();
//    }
//
//    @Override
//    public List<Map<String, Object>> getSecNav() {
//        return secMenuDao.querySecMenu();
//    }

    @Override
    public List<FirstMenu> getNav() {
//        拿到所有的值
        List<Map<String, Object>> maps = secMenuDao.querySecMenu();
        System.out.println("chongxie = " + JSON.toJSONString(maps));
//        List中的每个FirstMenu
        List<FirstMenu> firstMenus = new ArrayList<>();


        for (int i = 0; i < maps.size(); i++) {

            Map<String, Object> map = maps.get(i);
            FirstMenu firstMenu = new FirstMenu();
            //设置FirstMenu的ID和值
            firstMenu.setFirstMenuId((BigDecimal) map.get("FIRST_MENU_ID"));
            firstMenu.setFirstMenuChineseName((String) map.get("FIRST_MENU_CHINESE_NAME"));


            //找出有几个SecondMenu
            List<Map<String, Object>> sec = new ArrayList<>();
            int x = 0;

            Map<String, Object> b1 = new HashMap<>();

            b1.put("SEC_MENU_ID", map.get("SEC_MENU_ID"));
            b1.put("SEC_MENU_CHINESE_NAME", map.get("SEC_MENU_CHINESE_NAME"));
            sec.add(b1);

            for (int j = i + 1; j < maps.size(); j++) {

                Map<String, Object> map2 = maps.get(j);
                Map<String, Object> b2 = new HashMap<>();
                if (map.get("FIRST_MENU_CHINESE_NAME").equals(map2.get("FIRST_MENU_CHINESE_NAME"))) {
                    b2.put("SEC_MENU_ID", map2.get("SEC_MENU_ID"));
                    b2.put("SEC_MENU_CHINESE_NAME", map2.get("SEC_MENU_CHINESE_NAME"));
                    sec.add(b2);
                    maps.remove(map2);
                    j--;
                    x++;
                }

            }

            firstMenu.setSecMenu(sec);

            firstMenus.add(firstMenu);


        }


        System.out.println(JSON.toJSONString(firstMenus));
        return firstMenus;

    }

    @Override
    public String getFirstMenuChineseName(String sid) {
        if (sid != null && !"".equals(sid)) {
            return firstMenuDao.getFirstMenuChineseNameById(Integer.parseInt(sid));
        } else {
            return null;
        }
    }
}
