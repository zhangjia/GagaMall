package io.zhangjia.mall.service;

import java.util.List;
import java.util.Map;

public interface OrderService {
    List<Map<String, Object>> getOrders(String userId);

    Map<String, Object> getOrder(String userId, String orderId);

    boolean submit(String userId, String addressId, String logistics,
                   String orderPayType, String orderNote, String[] SKUIds);
}
