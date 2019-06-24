package io.zhangjia.mall.service;

import java.util.Map;

public interface IOUService  {
    Map<String,Object> queryUserIOU(String userId);
    Map<String,Object> orderPayByIOU(String userId,String money,String orderId);
}
