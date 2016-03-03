package com.coolweather.app.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import com.coolweather.app.module.City;
import com.coolweather.app.module.CoolWeatherDB;
import com.coolweather.app.module.County;
import com.coolweather.app.module.Province;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

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
    public static void handleWeattherResponse(Context context, String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherInfo = jsonObject.getJSONObject("weatherinfo");
            String cityName = weatherInfo.getString("city");
            String weatherCode = weatherInfo.getString("cityid");
            String temp1 = weatherInfo.getString("temp1");
            String temp2 = weatherInfo.getString("temp2");
            String weatherDescription = weatherInfo.getString("weather");
            String publishTime = weatherInfo.getString("ptime");
            saveWeatherInfo(context, cityName, weatherCode, temp1, temp2, weatherDescription, publishTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private static void saveWeatherInfo(Context context, String cityName, String weatherCode,
                                        String temp1, String temp2, String weatherDescription, String publishTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy年M月d日",Locale.CHINA);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean("city_selected",true);
        editor.putString("city_name", cityName);
        editor.putString("weather_code",weatherCode);
        editor.putString("temp1",temp1);
        editor.putString("temp2",temp2);
        editor.putString("weather_description",weatherDescription);
        editor.putString("publish_time",publishTime);
        editor.putString("date",dateFormat.format(new java.util.Date()));
        editor.commit();
    }
}
