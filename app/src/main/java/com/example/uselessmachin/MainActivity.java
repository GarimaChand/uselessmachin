package com.example.uselessmachin;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button buttonSelfDestruct;
    private Switch switchuseless;

    private long lastTime;
    private long duration = 500;
    private boolean green = false;


    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wireWidgets();
        setListeners();
    }

    private void startSelfDestructSequence() {
        // Diable the button
        buttonSelfDestruct.setEnabled(false);
        //Start a 10 seconf countdowm timer that updates
        //display every seconf
        new CountDownTimer(10000, 10) {
            @Override
            public void onTick(long millisUntilFinished) {
                buttonSelfDestruct.setText("Destruct in" + millisUntilFinished / 1000);
               
            }


            //want the button to show the counydonw
            //destruct in 10..
            //destruct in 9...

            //At the end, close the activity
            //call the finish() method
            @Override
            public void onFinish() {
                finish();
            }

        }.start();
    }
    private void startSwitchOffTimer() {
        new CountDownTimer(5000, 100) {

            public void onTick(long millisUntilFinish)

            {
                if (!switchuseless.isChecked()) {
                    //Log.d(TAG, "onTick: canceling");
                    cancel();
                    Toast.makeText(MainActivity.this, "canceling", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFinish()

            {
                switchuseless.setChecked(false);
                //Log.d(TAG, "onFinish: switch set to false");

            }
        }.start();
    }

    private void setListeners() {
        //TODO SELF DESTRUCT BUTTON
        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();


            }
        });


        switchuseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()

        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startSwitchOffTimer();
                    Toast.makeText(MainActivity.this, "On", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }


    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchuseless = findViewById(R.id.switch_main_useless);
    }
}
