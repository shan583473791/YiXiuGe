package com.zykj.yixiu.utils;

/**
 * Created by zykj on 2017/5/2.
 */

public class User_TianJia {
    @Override
    public String toString() {
        return "User_TianJia{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", user_id='" + user_id + '\'' +
                ", region='" + region + '\'' +
                ", lat='" + lat + '\'' +
                ", lon='" + lon + '\'' +
                ", city_name='" + city_name + '\'' +
                ", city_code='" + city_code + '\'' +
                ", isdefault='" + isdefault + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    String    name;//: 姓名
    String address;//: 地址
    String phone;//: 电话号码
    String  user_id;//:用户ID
    String region;//:区
    String  lat;//:纬度
    String   lon;//:经度
    String city_name;//:城市名
    String  city_code;//:城市编码
    String  isdefault;// 是否是默认 0 不默认  1 默认

}
