package com.example.sampleweather.Utils;

import android.text.TextUtils;

import com.example.sampleweather.db.City;
import com.example.sampleweather.db.County;
import com.example.sampleweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by edword on 2017/4/16.
 */
public class Parse {
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {

            try {
                JSONArray provinces = new JSONArray(response);
                for (int i = 0; i < provinces.length(); i++) {
                    JSONObject provinceObject = provinces.getJSONObject(i);
                    Province province = new Province();
                    province.setId(provinceObject.getInt("id"));
                    province.setProvinceName(provinceObject.getString("name"));
                    province.save();
                }
                return true;
`            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    Public static boolean handleCityResponce (String response,int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray Cities = new JSONArray(response);
                for (int i=0 ; i < Cities.length(); i++){
                    JSONObject cityObject = Cities.getJSONObject(i);
                    City city = new City();
                    //city.setId(cityObject.getInt("id"));
                    city.setProvinceCode(provinceId);
                    city.setCityCode(cityObject.getInt("id"));
                    city.setCityName(cityObject.getString("name"));
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    public static boolean handleCountyResponce (String response, int cityId) {
        if (TextUtils.isEmpty(response)) {
            try {
                JSONArray counties = new JSONArray(response);
                for (int i = 0; i < counties.length();i++) {
                    JSONObject countyObject = counties.getJSONObject(i);
                    County county = new County();
                    county.setCityId(cityId);
                    county.setCountyName(countyObject.getString("name"));
                    county.setWeatherId(countyObject.getString("weather_id"));
                    county.save();

                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        return false;

    }
}