package io.zhangjia.mall.service;

import java.sql.Date;
import java.util.Map;

/**
 * 用户的业务层
 *
 */
public interface UserService {
	/**
	 * 用户登录
	 * @param userName 用户名
	 * @param userPassword 密码
	 * @return 登录结果
	 */
	Map<String, Object> login(String userName, String userPassword);

	/**
	 * 用户注册
	 * @param userName 用户名
	 * @param userPassword 用户密码
	 * @param userPayPassword 用户支付密码
	 * @param userTel 用户手机号
	 * @param userMail 用户邮箱
	 * @param userGender 用户性别
	 * @param userBirthday 用户生日
	 * @param userAvatar 用户头像
	 * @return 注册结果
	 */
	int register(String userName, String userPassword, String userPayPassword,
					 String userTel, String userMail, String userGender, Date userBirthday, String userAvatar);
}
