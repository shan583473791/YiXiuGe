package com.zykj.yixiu.utils;

/**
 * Created by Administrator on 2017/4/21.
 */
public class User {

    /**
     * icon :
     * phone : 13936550052
     * sex :
     * idcard_image1 : idcard/bac82340-05e2-4307-a932-ab2382b46ea4.jsp
     * idcard_image2 : idcard/3f925c3c-9c3c-4dec-ac4c-8ae4b19347ed.txt
     * headimg :
     * ali_account : 123123123
     * score : 0
     * type : 0
     * password : ab123123
     * city :
     * username :
     * regtime : 2016-11-28 00:24:00
     * token : 3fa405cb-2264-4a9d-be04-51effb4ee330
     * order_num : 0
     * province :
     * ali_name : wakak
     * user_id : 2
     */

    private String icon; //头像地址
    private String phone;//电话号
    private String sex;//性别
    private String idcard_image1; //身份证正面 "idcard/bac82340-05e2-4307-a932-ab2382b46ea4.jsp",
    private String idcard_image2;//身份证反面 "idcard/3f925c3c-9c3c-4dec-ac4c-8ae4b19347ed.txt",
    private String headimg;
    private String ali_account; //支付宝账号
    private int score;//评分 买家没用
    private int type; //用户类型
    private String password;//密码
    private String city; //城市
    private String username; //用户名
    private String regtime;//注册时间
    private String token;//令牌
    private int order_num;//订单数量
    private String province;//省
    private String ali_name; //支付宝名
    private int user_id;  //用户ID

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getIdcard_image1() {
        return idcard_image1;
    }

    public void setIdcard_image1(String idcard_image1) {
        this.idcard_image1 = idcard_image1;
    }

    public String getIdcard_image2() {
        return idcard_image2;
    }

    public void setIdcard_image2(String idcard_image2) {
        this.idcard_image2 = idcard_image2;
    }

    public String getHeadimg() {
        return headimg;
    }

    public void setHeadimg(String headimg) {
        this.headimg = headimg;
    }

    public String getAli_account() {
        return ali_account;
    }

    public void setAli_account(String ali_account) {
        this.ali_account = ali_account;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRegtime() {
        return regtime;
    }

    public void setRegtime(String regtime) {
        this.regtime = regtime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getOrder_num() {
        return order_num;
    }

    public void setOrder_num(int order_num) {
        this.order_num = order_num;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getAli_name() {
        return ali_name;
    }

    public void setAli_name(String ali_name) {
        this.ali_name = ali_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
