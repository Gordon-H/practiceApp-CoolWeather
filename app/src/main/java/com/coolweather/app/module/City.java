package com.coolweather.app.module;

/**
 * Created by lbf on 2016/3/2.
 */
public class City {
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {

        return id;
    }

    private String cityName;
    private String cityCode;
    private int provinceId;

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCityName() {

        return cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getProvinceId() {

        return provinceId;
    }
}
