package org.dongkoer.com.entity;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.Date;

public class Predict {

    @JSONField(name = "date", format = "yyyy/MM/dd")
    private Date date;
    @JSONField(name = "city")
    private String city;
    @JSONField(name = "province")
    private String province;

    public Predict(String province, Date date, String city) {
        this.province = province;
        this.date = date;
        this.city = city;
    }
    public Predict() {
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
