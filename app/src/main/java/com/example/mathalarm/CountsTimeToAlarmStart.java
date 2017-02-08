package com.example.mathalarm;

import android.util.Log;


public class CountsTimeToAlarmStart {
    private int resultHours, resultMinutes;
    private static final String TAG = "CountsTimeToAlarmStart";

    public void HowMuchTimeToStart(int currentHour, int currentMinute, int pickedHour, int pickedMinute) {
        // day in minutes
        final int dayInMinutes = 1440;
        // Difference between current hour or minute and picked hour or minute
        int differenceCP;
        int timeToStartAlarm =0;

        //24 hour format time convert to minutes
        int picked, current;
        picked = pickedHour * 60 + pickedMinute;
        current = currentHour * 60 + currentMinute;

        Log.i(TAG, " h current: " + currentHour + "  alarm hour: " + pickedHour);
        Log.i(TAG," min current: " + currentMinute + "  alarm min: " + pickedMinute);

        if (pickedHour>currentHour) {
            timeToStartAlarm = picked - current;
        }
        if (pickedHour < currentHour) {
            //minutes to start
            differenceCP = current - picked;
            timeToStartAlarm = dayInMinutes - differenceCP;
        }
        if(pickedHour == currentHour) {
            if (currentMinute > pickedMinute ) {
                differenceCP = current - picked;
                timeToStartAlarm = dayInMinutes - differenceCP;
            }
            else {
                timeToStartAlarm = picked - current;
            }
        }
        // convert from minutes to 24 hour format
        resultHours = timeToStartAlarm / 60;
        resultMinutes = timeToStartAlarm - resultHours * 60;
    }

    public int getResultHours ()
    {
        return resultHours;
    }
    public int getResultMinutes ()
    {
        return resultMinutes;
    }

}
