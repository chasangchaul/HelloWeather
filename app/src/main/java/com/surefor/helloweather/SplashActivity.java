package com.surefor.helloweather;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.surefor.helloweather.entity.CityManager;

import java.io.IOException;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE) ;
        setContentView(R.layout.activity_splash);

        TextView tv =(TextView) findViewById(R.id.txtSplashWeatherIcon) ;
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/meteocons.ttf") ;
        tv.setTypeface(font);

        tv =(TextView) findViewById(R.id.txtSplashGreeting) ;
        font = Typeface.createFromAsset(getAssets(), "fonts/gotham-light.ttf") ;
        tv.setTypeface(font);

        ProgressBar pb = (ProgressBar) findViewById(R.id.pbStatus) ;
        pb.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY) ;

        new LoadCities(getAssets()).execute() ;
    }

    class LoadCities extends AsyncTask<Void, Void, Integer>
    {
        AssetManager assetManager ;

        public LoadCities(AssetManager assetManager) {
            this.assetManager = assetManager ;
        }

        @Override
        protected Integer doInBackground(Void... params) {
            CityManager manager = CityManager.instance() ;
            try {
                manager.load(assetManager);
            }
            catch (IOException ex) {

            }
            return manager.size() ;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class) ;
            startActivity(intent);

            finish();
        }
    }
}
