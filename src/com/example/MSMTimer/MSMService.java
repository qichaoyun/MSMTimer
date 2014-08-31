package com.example.MSMTimer;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.telephony.SmsManager;
import android.util.Log;

/**
 * Created by Scott on 14-8-31.
 */
public class MSMService extends Service {

    public static String msm = "";
    public static String time = "";
    public static String phone = "";
    public static boolean isStart = true;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MYMSN","onStartCommand..");
        new Thread(runnable).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d("MYMSN","onDestroy..");
        isStart = false;
        super.onDestroy();
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
                while (isStart){
                    try {
                        int seelpTime = Integer.parseInt(time);
                        Log.d("MYMSN","开始发送短信..");
                        SmsManager.getDefault().sendTextMessage(phone,
                                null, msm, null, null);
                        Thread.sleep(seelpTime*60*1000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
        }
    };

}
