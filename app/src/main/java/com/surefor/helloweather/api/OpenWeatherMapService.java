package com.surefor.helloweather.api;

import com.surefor.helloweather.entity.CurrentWeather;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ethan on 16/11/2015.
 */
public interface OpenWeatherMapService {
    @GET ("/data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("id") Long id, @Query("appid") String appid) ;
}
