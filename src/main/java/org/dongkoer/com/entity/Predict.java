package org.dongkoer.com.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Predict {

    @JSONField(name = "date", format = "yyyyMM")
    private String date;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "province")
    private String province;

    public Predict(String province, String date, String city) {
        this.province = province;
        this.date = date;
        this.city = city;
    }

    public Predict() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }
}
