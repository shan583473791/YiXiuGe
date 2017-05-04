package com.zykj.yixiu.utils;

import java.io.Serializable;

/**
 * Created by zykj on 2017/5/2.
 */

public class ShouJi  implements Serializable {
    @Override
    public String toString() {
        return "ShouJi{" +
                "PINPAI='" + PINPAI + '\'' +
                ", XINGHAO='" + XINGHAO + '\'' +
                ", GUZHANG='" + GUZHANG + '\'' +
                ", MIAOSHU='" + MIAOSHU + '\'' +
                ", TUPIAN='" + TUPIAN + '\'' +
                ", LEIXING='" + LEIXING + '\'' +
                '}';
    }

    String PINPAI;//品牌
    String XINGHAO;//型号
    String GUZHANG;//故障
    String MIAOSHU; //故障描述
    String TUPIAN;//图片
    String LEIXING;//类型
    public String getPINPAI() {
        return PINPAI;
    }

    public void setPINPAI(String PINPAI) {
        this.PINPAI = PINPAI;
    }

    public String getXINGHAO() {
        return XINGHAO;
    }

    public void setXINGHAO(String XINGHAO) {
        this.XINGHAO = XINGHAO;
    }

    public String getGUZHANG() {
        return GUZHANG;
    }

    public void setGUZHANG(String GUZHANG) {
        this.GUZHANG = GUZHANG;
    }

    public String getMIAOSHU() {
        return MIAOSHU;
    }

    public void setMIAOSHU(String MIAOSHU) {
        this.MIAOSHU = MIAOSHU;
    }

    public String getTUPIAN() {
        return TUPIAN;
    }

    public void setTUPIAN(String TUPIAN) {
        this.TUPIAN = TUPIAN;
    }

    public String getLEIXING() {
        return LEIXING;
    }

    public void setLEIXING(String LEIXING) {
        this.LEIXING = LEIXING;
    }

}
