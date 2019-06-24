package io.zhangjia.mall.dao.impl;


import io.zhangjia.mall.dao.UserDao;
import io.zhangjia.mall.entity.User;
import io.zhangjia.mall.utils.CommonDao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl extends CommonDao implements UserDao {

	@Override
	public User queryByUsernameAndPassword(String userName, String userPassword) {
		String sql = "SELECT * FROM users WHERE user_name = ? AND user_password = ?";

		return query4Bean(sql,User.class,userName,userPassword);
	}

	@Override
	public int doInsert(User user) {
		String sqlid = "SELECT seq_users.nextval id FROM dual";
		int id =  query4IntData(sqlid);
		String sql = "INSERT INTO users VALUES(?,?,?,?,sysdate,?,?,?,?,?,'https://thirdqq.qlogo.cn/g?b=oidb&k=Wl7068SxFJxKNpfO943LXA&s=100',0,1)";
		int i = executeUpdate(sql, id,user.getUserName(), user.getUserPassword(),user.getUserPayPassword(),
				user.getUserTel(),user.getUserMail(),user.getUserName(),user.getUserGender(),
				user.getUserBirthday()/*,user.getUserAvatar()*/);
		if(i == 1) {
			return  id;
		} else {
			return 0;
		}

	}

	@Override
	public int updateUserInformation(User user) {
		String sql = "update USERS\n" +
				"set (USER_NICK, USER_GENDER, USER_BIRTHDAY, USER_TEL,USER_MAIL,USER_PASSWORD,USER_PAY_PASSWORD) =\n" +
				"        (SELECT ?,?,TO_DATE(?,'yyyy-mm-dd'),?,?,?,? FROM dual)\n" +
				"where USER_ID = ?";
		System.out.println("user1111 = " + user);
		return executeUpdate(sql,user.getUserNick(),user.getUserGender(),user.getDate(),user.getUserTel()
		,user.getUserMail(),user.getUserPassword(),user.getUserPayPassword(),user.getUserId());
	}

	@Override
	public Map<String, Object> queryByPayPassword(Integer userId, String paypassword) {
		Map<String, Object> result = new HashMap<>();
		String sql = "SELECT USER_PAY_PASSWORD FROM USERS WHERE  USER_ID = ?";
		String s = query4StringData(sql, userId);
		if(s == null || "".equals(paypassword)) {
			result.put("error","未设置密码");
		} else {
			String sql2 = "SELECT * FROM USERS WHERE  USER_ID = ? AND USER_PAY_PASSWORD = ?";
			User user = query4Bean(sql2, User.class, userId, paypassword);
			if(user == null){
				result.put("error","支付密码不正确");
			} else {
				result.put("success","支付密码成功");
			}
		}
		return result;
	}

	@Override
	public List<User> queryUserAll() {
		String sql = "SELECT * FROM USERS WHERE USER_STATUS =1";
		return query4BeanList(sql,User.class);
	}

	/**
	 * 不能作为用户的个人信息使用，因为这个方法再用户刚登录的时候，作为用户是否存在的判断了，用户都默认没有头像
	 * @param userName 用户名
	 * @return
	 */
	@Override
	public User queryByUsername(String userName) {
		String sql = "SELECT * FROM users WHERE user_name = ?";

		User user = query4Bean(sql, User.class, userName);

		String sql2 = "SELECT * FROM USERS,IMG WHERE USER_ID = ? AND IMG_TYPE ='用户头像' AND IMG_BELONG = USER_ID AND IMG_IS_DEL != 0";
		User user2 = null;
		if(user != null) {
			 user2 = query4Bean(sql2, User.class, user.getUserId());
		}
		System.out.println("2user2 = " + user2);
		if(user2 != null) {
			System.out.println("有头像");
			user.setImgUrl(user2.getImgUrl());
		}
		return user;
	}

	/**
	 * 作为查询用户的个人信息使用
	 * @param userId
	 * @return
	 */
	@Override
	public User queryByUserId(String userId) {
		String sql1 = "SELECT * FROM users WHERE USER_ID = ?";
		User user = query4Bean(sql1, User.class, userId);

		String sql2 = "SELECT * FROM USERS,IMG WHERE USER_ID = ? AND IMG_TYPE ='用户头像' AND IMG_BELONG = USER_ID AND IMG_IS_DEL != 0";
		User user2 = query4Bean(sql2, User.class, userId);
		if(user2 != null) {
			user.setImgUrl(user2.getImgUrl());
		}
		return user;
	}


}
