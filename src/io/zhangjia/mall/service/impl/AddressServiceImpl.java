package io.zhangjia.mall.service.impl;

import io.zhangjia.mall.dao.AddressDao;
import io.zhangjia.mall.dao.impl.AddressDaoImpl;
import io.zhangjia.mall.service.AddressService;

import java.util.List;
import java.util.Map;

public class AddressServiceImpl implements AddressService {
    private AddressDao addressDao = new AddressDaoImpl();

    @Override
    public List<Map<String, Object>> getUserAddress(String userId) {
        if(!"".equals(userId) && userId !=null) {

            int uid = Integer.parseInt(userId);
            List<Map<String, Object>> maps = addressDao.queryByUserId(uid);
            return maps;

        } else {

            return  null;
        }

    }

    @Override
    public boolean addUserAddress(Map<String, Object> maps) {
        return addressDao.doInsert(maps) == 1;
    }
}
