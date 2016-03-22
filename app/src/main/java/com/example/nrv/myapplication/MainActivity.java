package com.example.nrv.myapplication;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity implements SensorEventListener {
    private SensorManager mSensorManager;
    private Sensor mPressure;
    private Sensor mPressure1;
    private Sensor mPressure2;
    String k="";
    @Override
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get an instance of the sensor service, and use that to get an instance of
        // a particular sensor.
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //mPressure = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        //mPressure1 = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mPressure2 = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

        List<Sensor> list = mSensorManager.getSensorList(Sensor.TYPE_ALL);
        for(Sensor sensor: list){
            Toast.makeText(getApplicationContext(),"data "+sensor.getName(),Toast.LENGTH_LONG).show();
        }
/*

        if(mPressure==null){
            Toast.makeText(getApplicationContext(),"NULL ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),mPressure.getName(),Toast.LENGTH_LONG).show();
        }

        if(mPressure1==null){
            Toast.makeText(getApplicationContext(),"NULL ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),mPressure.getName(),Toast.LENGTH_LONG).show();
        }

        if(mPressure2==null){
            Toast.makeText(getApplicationContext(),"NULL ",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(getApplicationContext(),mPressure.getName(),Toast.LENGTH_LONG).show();
        }

*/

    }

    @Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do something here if sensor accuracy changes.
    }

    @Override
    public final void onSensorChanged(SensorEvent event) {
        float millibars_of_pressure = event.values[0];
        // Do something with this sensor data.
        if(millibars_of_pressure>5){
            return;
        }
        TextView tv=(TextView)findViewById(R.id.abc);
        k=k+event.sensor.getName()+" -> "+millibars_of_pressure+"\n";
        tv.setText(k);
        //Toast.makeText(getApplicationContext(),"data "+millibars_of_pressure,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        // Register a listener for the sensor.
        super.onResume();
        //mSensorManager.registerListener(this, mPressure, SensorManager.SENSOR_DELAY_NORMAL);
        //mSensorManager.registerListener(this, mPressure1, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mPressure2, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // Be sure to unregister the sensor when the activity pauses.
        super.onPause();
        mSensorManager.unregisterListener(this);
    }
}