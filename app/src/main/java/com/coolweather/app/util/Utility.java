package com.coolweather.app.util;

import android.text.TextUtils;

import com.coolweather.app.module.City;
import com.coolweather.app.module.CoolWeatherDB;
import com.coolweather.app.module.County;
import com.coolweather.app.module.Province;

/**
 * Created by lbf on 2016/3/3.
 */
public class Utility {
    public static synchronized Boolean handleProvincesResponse
            (CoolWeatherDB coolWeatherDB, String response){
        if(!TextUtils.isEmpty(response)){
            String[] responseArray = response.split(",");
            if(responseArray != null && responseArray.length > 0){
                for(String responseItem : responseArray){
                    String [] info = responseItem.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(info[0]);
                    province.setProvinceName(info[1]);
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }
    public static synchronized Boolean handleCitiesResponse
            (CoolWeatherDB coolWeatherDB, String response,int provinceId){
        if(!TextUtils.isEmpty(response)){
            String[] responseArray = response.split(",");
            if(responseArray != null && responseArray.length > 0){
                for(String responseItem : responseArray){
                    String [] info = responseItem.split("\\|");
                    City city = new City();
                    city.setCityCode(info[0]);
                    city.setCityName(info[1]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return true;
            }
        }
        return false;
    }
    public static synchronized Boolean handleCountiesResponse
            (CoolWeatherDB coolWeatherDB, String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            String[] responseArray = response.split(",");
            if(responseArray != null && responseArray.length > 0){
                for(String responseItem : responseArray){
                    String [] info = responseItem.split("\\|");
                    County county = new County();
                    county.setCountyCode(info[0]);
                    county.setCountyName(info[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
