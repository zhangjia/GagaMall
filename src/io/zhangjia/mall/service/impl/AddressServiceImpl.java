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
        if (!"".equals(userId) && userId != null) {

            int uid = Integer.parseInt(userId);
            List<Map<String, Object>> maps = addressDao.queryByUserId(uid);
            return maps;

        } else {

            return null;
        }

    }

    @Override
    public boolean addUserAddress(Map<String, Object> maps) {
        System.out.println("\"\".equals((maps.get(\"addressId\")+\"\")) = " + "".equals((maps.get("addressId") + "")));
        System.out.println("\"\".equals((maps.get(\"null\")+\"\")) = " + (maps.get("addressId") + "") == null);
        if ((maps.get("addressId") + "") != null || !"".equals((maps.get("addressId") + "")) || !"".equals((maps.get("userId") + ""))) {
            String addressId = maps.get("addressId") + "";
            String userId = (maps.get("userId") + "");
            addressDao.updateAddressStatus("0", Integer.parseInt(addressId), Integer.parseInt(userId));
        }
        return addressDao.doInsert(maps) == 1;

    }

    @Override
    public Map<String, Object> getAddress4Edit(String addressId) {
        if (addressId != null && !"".equals(addressId)) {
            return addressDao.queryById(Integer.parseInt(addressId));
        }
        return null;
    }

    @Override
    public int setDefaultAddress(String addressId, String userId) {
        if (addressId != null && !"".equals(addressId) && userId != null && !"".equals(userId)) {
//            (String status,Integer addressId,Integer userId)
            int i = addressDao.deleteDefaultAddress(Integer.parseInt(userId));
            System.out.println("iwer = " + i);
            i *= addressDao.updateAddressStatus("2", Integer.parseInt(addressId), Integer.parseInt(userId));
            System.out.println("iwww = " + i);
            return i;

        }
        return 0;
    }

    @Override
    public int deleteAddress(String addressId, String userId) {
        if (addressId != null && !"".equals(addressId) && userId != null && !"".equals(userId)) {

            return addressDao.updateAddressStatus("0", Integer.parseInt(addressId), Integer.parseInt(userId));

        }
        return 0;
    }
}
