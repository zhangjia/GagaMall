package io.zhangjia.mall.entity;

import java.util.Date;
import java.util.Objects;

public class User {
    private Integer userId;             //用户ID
    private String userName;            //用户名
    private String userPassword;        //用户密码
    private String userPayPassword;     //用户支付密码
    private Date userRegisterTime;      //用户注册时间
    private String userTel;             //用户手机号
    private String userMail;            //用户邮箱
    private String userNick;            //用户昵称
    private String userGender;          //用户性别
    private Date userBirthday;          //用户生日
    private String userAvatar;          //用户头像
    private Integer userGrade;          //用户等级
    private Integer userStatus;         //用户状态

//    -------------------------------------
    private String ImgUrl;
    private String date;

    /**
     * 无参构造函数
     */
    public User() {
    }

    /**
     * 不带ID的构造函数
     *
     * @param userName         用户名
     * @param userPassword     用户密码
     * @param userPayPassword  用户支付密码
     * @param userRegisterTime 用户注册时间
     * @param userTel          用户手机号
     * @param userMail         用户邮箱
     * @param userNick         用户昵称
     * @param userGender       用户性别
     * @param userBirthday     用户生日
     * @param userAvatar       用户头像
     * @param userGrade        用户等级
     * @param userStatus       用户状态
     */
    public User(String userName, String userPassword, String userPayPassword, Date userRegisterTime, String userTel, String userMail, String userNick, String userGender, Date userBirthday, String userAvatar, Integer userGrade, Integer userStatus) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPayPassword = userPayPassword;
        this.userRegisterTime = userRegisterTime;
        this.userTel = userTel;
        this.userMail = userMail;
        this.userNick = userNick;
        this.userGender = userGender;
        this.userBirthday = userBirthday;
        this.userAvatar = userAvatar;
        this.userGrade = userGrade;
        this.userStatus = userStatus;
    }

    /**
     * 构造函数
     *
     * @param userId           用户ID
     * @param userName         用户名
     * @param userPassword     用户密码
     * @param userPayPassword  用户支付密码
     * @param userRegisterTime 用户注册时间
     * @param userTel          用户手机号
     * @param userMail         用户邮箱
     * @param userNick         用户昵称
     * @param userGender       用户性别
     * @param userBirthday     用户生日
     * @param userAvatar       用户头像
     * @param userGrade        用户等级
     * @param userStatus       用户状态
     */
    public User(Integer userId, String userName, String userPassword, String userPayPassword, Date userRegisterTime, String userTel, String userMail, String userNick, String userGender, Date userBirthday, String userAvatar, Integer userGrade, Integer userStatus) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPayPassword = userPayPassword;
        this.userRegisterTime = userRegisterTime;
        this.userTel = userTel;
        this.userMail = userMail;
        this.userNick = userNick;
        this.userGender = userGender;
        this.userBirthday = userBirthday;
        this.userAvatar = userAvatar;
        this.userGrade = userGrade;
        this.userStatus = userStatus;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public void setUserPayPassword(String userPayPassword) {
        this.userPayPassword = userPayPassword;
    }

    public void setUserRegisterTime(Date userRegisterTime) {
        this.userRegisterTime = userRegisterTime;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public void setUserNick(String userNick) {
        this.userNick = userNick;
    }

    public void setUserGender(String userGender) {
        this.userGender = userGender;
    }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public void setUserGrade(Integer userGrade) {
        this.userGrade = userGrade;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public String getUserPayPassword() {
        return userPayPassword;
    }

    public Date getUserRegisterTime() {
        return userRegisterTime;
    }

    public String getUserTel() {
        return userTel;
    }

    public String getUserMail() {
        return userMail;
    }

    public String getUserNick() {
        return userNick;
    }

    public String getUserGender() {
        return userGender;
    }

    public Date getUserBirthday() {
        return userBirthday;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public Integer getUserGrade() {
        return userGrade;
    }

    public Integer getUserStatus() {
        return userStatus;
    }
//    ---------------------


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User users = (User) o;
        return Objects.equals(userId, users.userId) &&
                Objects.equals(userName, users.userName) &&
                Objects.equals(userPassword, users.userPassword) &&
                Objects.equals(userPayPassword, users.userPayPassword) &&
                Objects.equals(userRegisterTime, users.userRegisterTime) &&
                Objects.equals(userTel, users.userTel) &&
                Objects.equals(userMail, users.userMail) &&
                Objects.equals(userNick, users.userNick) &&
                Objects.equals(userGender, users.userGender) &&
                Objects.equals(userBirthday, users.userBirthday) &&
                Objects.equals(userAvatar, users.userAvatar) &&
                Objects.equals(userGrade, users.userGrade) &&
                Objects.equals(userStatus, users.userStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, userName, userPassword, userPayPassword, userRegisterTime, userTel, userMail, userNick, userGender, userBirthday, userAvatar, userGrade, userStatus);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userPayPassword='" + userPayPassword + '\'' +
                ", userRegisterTime=" + userRegisterTime +
                ", userTel='" + userTel + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userNick='" + userNick + '\'' +
                ", userGender='" + userGender + '\'' +
                ", userBirthday=" + userBirthday +
                ", userAvatar='" + userAvatar + '\'' +
                ", userGrade=" + userGrade +
                ", userStatus=" + userStatus +
                ", ImgUrl='" + ImgUrl + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
