package com.surefor.helloweather;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;

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
        // pb.getProgressDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.SRC_IN);
        pb.getIndeterminateDrawable().setColorFilter(Color.YELLOW, PorterDuff.Mode.MULTIPLY);
    }
}
