package com.example.xemss.stopwatch;

import android.os.Handler;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
//    Button btnStart, btnStop, btnReset;
//    TextView timeView;
    private int seconds = 0;
    private boolean running;
    // TODO: 26.09.2017 create var to know if have saved state
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        if (wasRunning) {
//           wasRunning = savedInstanceState.getBoolean("wasRunning");
//        }

        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        runTimer();
    }
    // TODO: 26.09.2017 when start pushed
    public void onClickStart(View view) {
        running = true;
    }
    // TODO: 26.09.2017 when stop pushed
    public void onClickStop(View view) {
        running = false;
    }
    // TODO: 26.09.2017 when reset pushed
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

//    create timer to count
    public void runTimer() {
        final TextView textView =  (TextView) findViewById(R.id.timeView);
        // TODO: 26.09.2017 create new handler to run
        final Handler handler =  new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                // TODO: 26.09.2017 format seconds to show formated hour, minutes, seconds
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int secs = seconds % 60;
                // TODO: 26.09.2017 string format
                String time = String.format("%d:%20d:%20d", hours, minutes, secs);

                textView.setText(time);
                // TODO: 26.09.2017 check if stopwatch running, start pushed and

                if (running) {
                    seconds++;
                }
                // TODO: 26.09.2017 set delay
                handler.postDelayed(this, 1000);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("seconds", seconds);
        // TODO: 26.09.2017 outState.putString("running", running);
        outState.putBoolean("running", running);
        // TODO: 26.09.2017 save status
        outState.putBoolean("wasRunning", wasRunning);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: 27.09.2017 check status before show to user
        if (wasRunning) {
         running = wasRunning;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO: 27.09.2017 save status before stop
        wasRunning = running;
        running = false;
    }

    //    @Override
//    public void onClick(View view) {
//
//    }
}
