package com.moliying.dayadayweather.model;

/**
 * Created by thinkpad on 2016/9/4.
 */
public class Province {
    private int id;
    private String provinceName;
    private String provinceCode;

    public Province() {
    }

    public Province(String provinceName, String provinceCode) {
        this.provinceName = provinceName;
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Province{" +
                "id=" + id +
                ", provinceName='" + provinceName + '\'' +
                ", provinceCode='" + provinceCode + '\'' +
                '}';
    }
}
