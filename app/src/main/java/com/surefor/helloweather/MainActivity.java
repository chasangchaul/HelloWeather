package com.surefor.helloweather;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.surefor.helloweather.api.OpenWeatherMapAPI;
import com.surefor.helloweather.entity.City;
import com.surefor.helloweather.entity.CityManager;
import com.surefor.helloweather.entity.CurrentWeather;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    Typeface fontMeteocons = null ;
    Typeface fontGotham = null ;
    Long currentId = 6092122L ;
    String selected = null ;

    GestureDetector gestureDetectorForCurrentWeahter;

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(parent, name, context, attrs);
        return updateFont(view, name, attrs) ;
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        View view = super.onCreateView(name, context, attrs);
        return updateFont(view, name, attrs) ;
    }

    private View updateFont(View view, String name, AttributeSet attrs) {
        View updated = view ;

        if("TextView".equalsIgnoreCase(name)) {
            updated = new TextView(this, attrs) ;
            ((TextView)updated).setTypeface(fontGotham) ;
        }

        return updated ;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.content_main);

        fontMeteocons = Typeface.createFromAsset(getAssets(), "fonts/meteocons.ttf") ;
        fontGotham = Typeface.createFromAsset(getAssets(), "fonts/gotham-light.ttf") ;

        ViewGroup paranet = (ViewGroup) findViewById(R.id.layoutWeather) ;
        View today = LayoutInflater.from(this).inflate(R.layout.today_weather, paranet) ;

        // new WeatherTask().execute(6092122L) ;
        refreshWeather() ;

        gestureDetectorForCurrentWeahter = new GestureDetector(this, new GestureListener()) ;

        AutoCompleteTextView tvCities = (AutoCompleteTextView) findViewById(R.id.txtCityList) ;
        ArrayAdapter<String> cities = new ArrayAdapter<>(this, R.layout.simple_list_item, CityManager.instance().getKeys()) ;
        tvCities.setAdapter(cities);

        Button btnLoad = (Button) findViewById(R.id.btnLoad) ;


        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AutoCompleteTextView tvCities = (AutoCompleteTextView) findViewById(R.id.txtCityList) ;
                City city = CityManager.instance().getCity(tvCities.getText().toString()) ;
                refreshWeather(city.getId()) ;
            }
        });
/*
        today.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gestureDetectorForCurrentWeahter.onTouchEvent(event) ;

            }
        });
*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetectorForCurrentWeahter.onTouchEvent(event) ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        switch (newConfig.orientation){
            case Configuration.ORIENTATION_LANDSCAPE :
                break ;
            case Configuration.ORIENTATION_PORTRAIT :
                break ;
        }
        super.onConfigurationChanged(newConfig);
    }

    private Integer KelvinToCelsisus(Double kelvin)    {
        return (int) (kelvin - 273.0) ;
    }
    private Integer FahrenheitToCelsisus(Double fahrenheit) {
        return  (int) ((5.0 / 9.0) * (fahrenheit - 32.0)) ;

    }

    private void updateCurrentWeather(CurrentWeather weather)
    {
        TextView tvTemplature = (TextView) findViewById(R.id.txtTemperature) ;
        tvTemplature.setText(String.valueOf(KelvinToCelsisus(weather.getMain().getTemp())));

        TextView tvDesc = (TextView) findViewById(R.id.txtDesc) ;
        tvDesc.setText(weather.getWeather().get(0).getDescription());

        TextView tvDetail = (TextView) findViewById(R.id.txtDetail) ;
        tvDetail.setText("Wind: " + String.valueOf(weather.getWind().getSpeed()) + "m/h, Humidity: " + String.valueOf(weather.getMain().getHumidity()) + "%");

        TextView tvDate = (TextView) findViewById(R.id.txtDate) ;
        Date dtNow = new Date(weather.getDt() * 1000L) ;
        SimpleDateFormat dtFormat = new SimpleDateFormat("dd MMM yyyy hh:mm:ss") ;
        dtFormat.setTimeZone(TimeZone.getTimeZone("EST"));
        tvDate.setText(dtFormat.format(dtNow));

    }

    private void refreshWeather(Long id) {
        currentId = id ;
        new WeatherTask().execute(currentId) ;
    }

    private void refreshWeather() {
        new WeatherTask().execute(currentId) ;
    }

    class WeatherTask extends AsyncTask<Long, Void, CurrentWeather> {
        @Override
        protected void onPostExecute(CurrentWeather currentWeather) {
            super.onPostExecute(currentWeather);
            updateCurrentWeather(currentWeather);
        }

        @Override
        protected CurrentWeather doInBackground(Long... params) {
            OpenWeatherMapAPI api = new OpenWeatherMapAPI() ;
            CurrentWeather currentWeather = api.getCurrentWeather(params[0]) ;
            return currentWeather ;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onDoubleTap(MotionEvent e) {
            refreshWeather() ;
            return super.onDoubleTap(e);
        }
    }
}
