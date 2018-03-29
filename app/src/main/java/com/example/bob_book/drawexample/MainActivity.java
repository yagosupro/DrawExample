package com.example.bob_book.drawexample;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor sensorRotation;
    Sensor sensorGyro;
    Sensor sensorTest;
    Sensor sensorMagnetic;
    int x = 0;
    float[] mGravity;
    float[] mGeomagnetic;
    float azimut;
    float pitch;
    float roll;
    DrawView2 drawView2;
    DrawView drawView;




    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            mGravity = event.values;

        }


        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            mGeomagnetic = event.values;


        }


        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);
            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimut = orientation[0]; // orientation contains: azimut, pitch and roll
                pitch = orientation[1];
                roll = orientation[2];
                drawView2.setPitch(pitch);
                drawView2.refresh();
                drawView.setPitch(pitch);

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView2=findViewById(R.id.DrawView2);
        drawView=findViewById(R.id.DrawView);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorRotation = sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        sensorGyro = sensorManager.getDefaultSensor(sensorGyro.TYPE_GYROSCOPE);
        sensorTest = sensorManager.getDefaultSensor(sensorTest.TYPE_ACCELEROMETER);
        sensorMagnetic = sensorGyro = sensorManager.getDefaultSensor(sensorMagnetic.TYPE_MAGNETIC_FIELD);

    }


    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensorRotation, sensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorGyro, sensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorTest, sensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorTest, sensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, sensorMagnetic, sensorManager.SENSOR_DELAY_UI);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }




}