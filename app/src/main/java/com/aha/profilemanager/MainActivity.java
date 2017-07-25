package com.aha.profilemanager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.R.color.holo_blue_bright;

public class MainActivity extends AppCompatActivity {

    private Intent myService_activate_intent;
    private AudioManager manage_audio;
    private Boolean bool = true;
    private String button_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myService_activate_intent = new Intent(this, MyService.class);
        manage_audio = (AudioManager)getSystemService(AUDIO_SERVICE);

        final Button button_on_off = (Button)findViewById(R.id.button_on_off);
        button_on_off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button_text = (String) button_on_off.getText();
                if(button_text.equals("ON"))
                {

                    button_on_off.setText("OFF");
                    go();
                    button_text = (String) button_on_off.getText();

                    Log.d("button text", "Button text off is working"+" "+(String) button_on_off.getText());
                }
                else if(button_text.equals("OFF"))
                {

                    button_on_off.setText("ON");
                    stop();
                    button_text = (String) button_on_off.getText();

                    Log.d("button text", "Button text ON is working"+" "+(String) button_on_off.getText());
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Button text not working"+" "+(String) button_on_off.getText(), Toast.LENGTH_LONG).show();
                    Log.d("button text", "Button text is not working");
                }
            }
        });

    }

    public void go()
    {
        bool = true;

        startService(myService_activate_intent);
        Log.d("Start bool", "Start is working");
    }

    public void stop()
    {
        if(bool)
        {
            bool = false;
            stopService(myService_activate_intent);
            Log.d("stop bool", "stop bool is working");
        }
        else
            Toast.makeText(getApplicationContext(),"Service is not working", Toast.LENGTH_LONG).show();
        Log.d("stop", "stop is working");
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    public void silent(View v){
        manage_audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    }

    public  void normal(View v){
        manage_audio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    }

    public void vivrate (View v){
        manage_audio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    }



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
