package io.zhangjia.mall.dao;

import java.util.List;
import java.util.Map;

public interface FirstMenuDao {
    List<Map<String, Object>> queryFirstMenu();

    String getFirstMenuChineseNameById(Integer id);
}
