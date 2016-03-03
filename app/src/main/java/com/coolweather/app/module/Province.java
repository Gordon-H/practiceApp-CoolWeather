package com.coolweather.app.module;

/**
 * Created by lbf on 2016/3/2.
 */
public class Province {
    private String provinceName;
    private String provinceCode;
    private int id;

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvinceName() {

        return provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public int getId() {
        return id;
    }
}
