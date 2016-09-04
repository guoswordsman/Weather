package com.moliying.dayadayweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.moliying.dayadayweather.model.City;
import com.moliying.dayadayweather.model.County;
import com.moliying.dayadayweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thinkpad on 2016/9/4.
 */
public class WeatherDB  {
    /**
     * 数据库名
     */
    public static  final String DB_NAME = "dayday_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static WeatherDB weatherDB;
    private SQLiteDatabase db;

    /**
     * 构造方法私有化
     * @param context
     */
    private WeatherDB(Context context){
        WeatherOpenHelper dbHelper = new WeatherOpenHelper(context,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取weather实例
     * @param context
     * @return
     */
    public synchronized static  WeatherDB getInstance(Context context){
        if(weatherDB == null ){
            weatherDB = new WeatherDB(context);
        }
        return weatherDB;
    }

    /**
     * 将Province实例存储到数据库中
     * @param province
     */
    public void saveProvince(Province province){
        if(province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
           db.insert("Province",null,values);
        }
    }

    /**
     * 从数据库中读取全国所有的省份信息
     * @return
     */
    public List<Province> LoadProvince(){
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Province",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());
           if (cursor!=null){
               cursor.close();
           }
        }
        return list;
    }

    /**
     * 将City实例储存到数据库中
     * @param city
     */
    public void saveCity(City city ){
        if (city != null){
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getProvinceId());
            db.insert("City",null,values);
        }
    }

    /**
     * 从数据库中获取某省份下所有城市信息
     * @param provinceId
     * @return
     */
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query("City",null,"province_id = ?",new String[]{String.valueOf(provinceId)},
                null,null,null);
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);

            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return  list;
    }
    /**
     * 将County实例储存到数据库中
     * @param county
     */
    public void saveCounty(County county){
        if (county != null){
            ContentValues values = new ContentValues();
            values.put("county_name",county.getCountyName());
            values.put("county_code",county.getCountyCode());
            values.put("city_id",county.getCityId());
            db.insert("County",null,values);
        }
    }
    /**
     * 从数据库中读取某城市下所有的县信息
     * @param cityId
     * @return
     */
    public List<County> loadCounties(int cityId){
        List<County> list = new ArrayList<>();
        Cursor cursor = db.query("County",null,"city_id = ?",new String[]{String.valueOf(cityId)},
                null,null,null);
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setCityId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);

            }while (cursor.moveToNext());
        }
        if (cursor!=null){
            cursor.close();
        }
        return  list;
    }


}
