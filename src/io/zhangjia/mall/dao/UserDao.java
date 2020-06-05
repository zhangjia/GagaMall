package io.zhangjia.mall.dao;

import io.zhangjia.mall.entity.User;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface UserDao {

    /**
     * 根据用户名和密码查询
     *
     * @param userName     用户名
     * @param userPassword 密码
     * @return 查询的用户
     */
    User queryByUsernameAndPassword(String userName, String userPassword);

    /**
     * 根据用户名查询
     *
     * @param userName 用户名
     * @return 查询的用户
     */
    User queryByUsername(String userName);

    User queryByUserId(String userId);

    /**
     * 根据用户名和密码添加用户
     *
     * @param user 要添加的用户
     * @return 添加是否成功，成功返回1，不成功返回0
     */
    int doInsert(User user);

    int updateUserInformation(User user);

    Map<String, Object> queryByPayPassword(Integer userId, String paypassword);

    List<User> queryUserAll();
}
