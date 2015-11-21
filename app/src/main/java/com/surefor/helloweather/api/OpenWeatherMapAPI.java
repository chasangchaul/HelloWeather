package com.surefor.helloweather.api;

import android.util.Log;

import com.surefor.helloweather.R;
import com.surefor.helloweather.application.HelloWeatherApp;
import com.surefor.helloweather.entity.CurrentWeather;

import retrofit.Call;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ethan on 15/11/2015.
 */
public class OpenWeatherMapAPI {
    public String getURL() {
        StringBuilder url = new StringBuilder() ;

        url.append("http://") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.url_open_weather_map)) ;
        url.append("/") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.v_open_weather_map)) ;

        return url.toString() ;
    }

    public String getCurrentWeatherURLWithId(String id) {
        StringBuilder url = new StringBuilder() ;

        url.append("http://") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.url_open_weather_map)) ;
        url.append("/") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.v_open_weather_map)) ;
        url.append("/") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.api_open_weather_map_current_weather)) ;
        url.append("?id") ;
        url.append(id) ;
        url.append("&APPID=") ;
        url.append(HelloWeatherApp.getAppResources().getText(R.string.key_open_weather_map)) ;

        return url.toString() ;
    }

    public CurrentWeather getCurrentWeather(Long id)
    {
        Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(getURL())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build() ;
        OpenWeatherMapService service = retrofit.create(OpenWeatherMapService.class) ;
        CurrentWeather currentWeather = null ;

        try
        {
            Call<CurrentWeather> call = service.getCurrentWeather(id, String.valueOf(HelloWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
            Response<CurrentWeather> response = call.execute() ;
            currentWeather = response.body() ;
        }
        catch (Exception e) {
            Log.e("getCurrentWeather", e.getMessage(), e) ;
        }
        //Call<CurrentWeather> allCurrentWeather = service.getCurrentWeather(id, String.valueOf(HelloWeatherApp.getAppResources().getText(R.string.key_open_weather_map))) ;
        //ResponseCache<CurrentWeather> currentWeather = allCurrentWeather.execute() ;

        return  currentWeather ;
    }
}
