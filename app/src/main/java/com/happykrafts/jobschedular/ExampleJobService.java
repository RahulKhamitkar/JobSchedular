package com.happykrafts.jobschedular;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.util.Log;
import android.widget.Toast;

public class ExampleJobService extends JobService {

    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;

    @Override
    public boolean onStartJob(JobParameters params) {

        Log.d(TAG,"Job Started");
        doworkBackGround(params);
        return true;
    }

    private void doworkBackGround(JobParameters params) {

        new Thread(new Runnable() {
            @Override
            public void run() {

                for(int i = 0;i>-1;i++){
                    Log.d(TAG,"Run "+i);  //Infinite Loop
                    if(jobCancelled){
                        return;
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        Log.d(TAG,"Job cancelled before Completion");
        jobCancelled = true;
        return false;
    }
}
