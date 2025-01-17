package org.dongkoer.com.entity;

import com.alibaba.fastjson.annotation.JSONField;

public class PredictReceiver {
    @JSONField(name = "date", format = "yyyyMM")
    private String Date;
    @JSONField(name = "city")
    private String City;
    @JSONField(name = "province")
    private String Province;
    @JSONField(name = "price")
    private Double Price;

    public PredictReceiver()
    {

    }

    public PredictReceiver(String date, Double price, String province, String city) {
        Date = date;
        Price = price;
        Province = province;
        City = city;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    @Override
    public String toString() {
        return "PredictReceiver{" +
                "Date='" + Date + '\'' +
                ", City='" + City + '\'' +
                ", Province='" + Province + '\'' +
                ", Price=" + Price +
                '}';
    }
}
