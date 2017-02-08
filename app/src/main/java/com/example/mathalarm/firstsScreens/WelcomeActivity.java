package com.example.mathalarm.firstsScreens;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathalarm.R;

public class WelcomeActivity extends AppCompatActivity
{
    private TextView tvTitle, tvProgressIndicator;
    private android.widget.ProgressBar pbHorizontal;
    private int iProgressBarStatus = 0;
    private boolean checkProgressBarStatus = false;
    private Handler handler = new Handler();


    @Override
        protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcom);

        pbHorizontal = (android.widget.ProgressBar) findViewById(R.id.pbHorizontal);
        tvProgressIndicator = (TextView) findViewById(R.id.tvProgressIndicator);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setVisibility(View.VISIBLE);


        ProgressBarMethodSleep();
    }
    private void StartMainActivity()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    private void ProgressBarMethodSleep()
    {
        // Start lengthy operation in a background thread
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                while (iProgressBarStatus < 100)
                {
                    // iProgressBarStatus = doWork(); when application will have real information to update
                    iProgressBarStatus += 1;

                    //Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            pbHorizontal.setProgress(iProgressBarStatus);
                            tvProgressIndicator.setText("Progress: " + iProgressBarStatus + "/"
                                    + pbHorizontal.getMax());

                            if (iProgressBarStatus == 100)
                            {
                                checkProgressBarStatus = true;
                                StartMainActivity();
                            }

                        }
                    });
                    try {
                        Thread.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
