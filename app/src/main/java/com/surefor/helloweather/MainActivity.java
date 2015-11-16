package com.surefor.helloweather;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Typeface fontMeteocons = null ;
    Typeface fontGotham = null ;

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

        // paranet.addView(today);
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
}
