package com.surefor.helloweather.api;

import android.util.Log;

import com.squareup.okhttp.ResponseBody;
import com.surefor.helloweather.R;
import com.surefor.helloweather.application.HelloWeatherApp;
import com.surefor.helloweather.entity.CurrentWeather;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class OpenWeatherMapAPI {
    public CurrentWeather getCurrentWeather(Long id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(HelloWeatherApp.getAppResources().getText(R.string.url_open_weather_map).toString())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build() ;

        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class) ;

        CurrentWeather currentWeather = null ;

        try
        {
            Call<CurrentWeather> call = service.getCurrentWeather(id,
                    String.valueOf(HelloWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
            Response<CurrentWeather> response = call.execute() ;
            currentWeather = response.body() ;
        }
        catch (Exception e) {
            Log.e("getCurrentWeather", e.getMessage(), e) ;
        }

        return  currentWeather ;
    }
}
