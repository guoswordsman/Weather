package com.moliying.dayadayweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by thinkpad on 2016/9/4.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {

    /**
     * Province表建表语句
     */
   public static final String CREATE_PROVINCE = "create table Province(" +
           "id integer primary key autoincrement," +
           "province_name text," +
           "province_code text)";

    /**
     * city表建表语句
     */
    public static final String CREATE_CITY = "create table Province(" +
            "id integer primary key autoincrement," +
            "province_name text," +
            "province_code text," +
            "province_id integer)";
    /**
     * County建表语句
     */
    public static final String CREATE_COUNTY = "create table County(" +
            "id integer primary key autoincrement," +
            "county_name text," +
            "county_code text," +
            "city_id integer)";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVINCE);//创建Province表
        db.execSQL(CREATE_CITY);//创建City表
        db.execSQL(CREATE_COUNTY);//创建County表

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
