package com.zykj.yixiu.utils;

/**
 * 地址工具类
 * Created by Administrator on 2017/4/15.
 */

public class YURL {
    // 服务器地址
    public static final String BASE_HOST = "http://221.207.184.124:7071/";
    //项目名称
    public static final String HOST = BASE_HOST + "yxg/";
    //查找手机品牌
    public static final String FIND_PHONE_BRAND = HOST + "findPhoneBrand";
    //根据品牌查找型号
    public static final String FIND_PHONE_MODEL = HOST + "findPhoneModel";
    //查询手机故障
    public static final String FIND_PHONE_FAULT = HOST + "findPhoneFault";
    //查找电脑型号
    public static final String FIND_BY_COMPUTER_MODEL = HOST + "findByComputerModel";
    //查找电脑品牌
    public static final String FIND_COMPUTER_BRAND = HOST + "findComputerBrand";
    //电脑类型
    public static final String FIND_COMPUTER_CATEGORY = HOST + "findComputerCategory";
    //家电品牌
    public static final String FIND_BY_APPLIANCE_BRAND = HOST + "findByApplianceBrand";
    //家电类型
    public static final String FIND_APPLIANCE_CATEGORY = HOST + "findApplianceCategory";
    //家电型号
    public static final String FIND_BY_APPLIANCE_MODEL = HOST + "findByApplianceModel";


    //注册+++++++
    public static final String REGISTER = HOST + "register";

    //密码
    public static final String SETPASSWORD = HOST + "setpassword";

    //登录
    public static final String LOGIN = HOST + "login";

    //    上传头像
    public static final String UPLOADICON = HOST + "uploadIcon";
    //查询地址
    public static final String SELECT_ADDRESS = HOST + "selectAddress";
    //添加地址
    public static final String ADDADDRESS = HOST + "addaddress";
    //发布订单
    public static final String ADD_ORDER = HOST + "addOrder";
    //设置默认收货地址
    public static final String DEF_ADDRESS = HOST + "defAddress";
    //删除地址
    public static final String DEL_ADDRESS = HOST + "delAddress";
    //'根据状态查找订单
    public static final String FIND_ORDER_BYSTATE = HOST + "findOrderByState";
    //取消订单
    public static final String CAN_CELORDER = HOST + "cancelOrder";
    //删除订单
    public static final String DEL_ORDER = HOST + "delOrder";
}
