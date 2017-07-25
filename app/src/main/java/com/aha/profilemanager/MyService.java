package com.aha.profilemanager;


import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Ahanaf on 19-Jul-17.
 */

public class MyService extends Service implements SensorEventListener {

    private AudioManager manage_audio;
    private SensorManager manage_sensore;
    private Sensor accelerometer_sensore, proximity_sensore;
    private int count =0;


    @Override
    public void onCreate() {
        super.onCreate();

        Toast myToast = Toast.makeText(getApplicationContext(),"My Service created",Toast.LENGTH_LONG);
        Log.d("Service","onCreat() in working !");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        manage_sensore=(SensorManager)getSystemService(SENSOR_SERVICE);
        manage_audio = (AudioManager)getSystemService(AUDIO_SERVICE);

        accelerometer_sensore = manage_sensore.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        proximity_sensore = manage_sensore.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        manage_sensore.registerListener(this,proximity_sensore,SensorManager.SENSOR_DELAY_NORMAL);
        manage_sensore.registerListener(this,accelerometer_sensore,SensorManager.SENSOR_DELAY_NORMAL);

        Log.d("Service","onStartCommand() in working !");

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        manage_sensore.unregisterListener(this,accelerometer_sensore);
        manage_sensore.unregisterListener(this,proximity_sensore);
        Log.d("Service","onDestroy() in working !");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_PROXIMITY)
        {

            if(sensorEvent.values[0] == 0)
            {
                count++;
                if(count == 1)
                {
                    manage_audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
                    Log.d("Count","Count 1 in working ! "+count);
                }
                if(count == 2)
                {
                    manage_audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                    Log.d("Count","Count 2 in working ! "+count);
                }
                if(count == 3)
                {
                    manage_audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                    Log.d("Count","Count 3 in working ! "+count);
                    count = 0;
                }
            }

        }

        Log.d("Service","onSensorChanged() in working !");

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
