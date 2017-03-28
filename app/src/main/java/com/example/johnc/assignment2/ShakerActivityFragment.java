//John Cetin 100955200 COMP3074 Assignment 2

package com.example.johnc.assignment2;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import static android.content.Context.SENSOR_SERVICE;

/**
 * A placeholder fragment containing a simple view.
 */
public class ShakerActivityFragment extends Fragment implements SensorEventListener {

    private float x, y, z, lastX, lastY, lastZ;

    private Sensor AccSensor;
    private SensorManager SensorManager;

    private long CurrentTime, LastUpdate;
    private final static long UpPeriod = 150;
    private static final int Threshold = 300;


    public ShakerActivityFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        SensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
        AccSensor = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        SensorManager.registerListener(this, AccSensor, SensorManager.SENSOR_DELAY_NORMAL);
        CurrentTime = LastUpdate = (long)0.0;
        x = y = z = lastX = lastY = lastZ = (float)0.0;
        return inflater.inflate(R.layout.fragment_shaker, container, false);

    }

    //Notes about shaking the Phone: I slide the yaw back and forth in order
    //to replicate a shake movement, since there is not shake button on the
    //emulator. The yaw setting is in the settings menu ( three dots [...]) under virtual sensor.
    //I move the slider widely for about 1-3 seconds without causing
    //the phone to become horizontal. It will trigger and give a Toast message.


    @Override
    public void onSensorChanged(SensorEvent event) {

        long currentTime = System.currentTimeMillis();

        if((currentTime - LastUpdate) > UpPeriod) {
            long timeDifference = (currentTime - LastUpdate);
            LastUpdate = currentTime;

            x = event.values[0];
            y = event.values[1];
            z = event.values[2];

            float ShakeSpeed = Math.abs(x + y + z - lastX - lastY - lastZ) / timeDifference * 10000;

            if(ShakeSpeed > Threshold){
                Toast.makeText(getActivity(), "The device has been shook, speed is: " + ShakeSpeed, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);

                getActivity().finish();
            }

            lastX = x;
            lastY = y;
            lastZ = z;

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}
