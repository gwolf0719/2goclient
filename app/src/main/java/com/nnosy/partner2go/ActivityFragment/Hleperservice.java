package com.nnosy.partner2go.ActivityFragment;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Nosy on 2017/3/28.
 */

public class Hleperservice extends Service {
    private int sum = 0;
    private boolean running = true;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        thread.run();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        running = false;
    }

    Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            while (running) {
                sum++;
                Log.d("ServiceProject", "ServiceSwe : " + sum);
                Intent intent = new Intent();
                intent.putExtra("Swe_sum", "" + sum);
                intent.setAction("gcm.notification.click_action");
                sendBroadcast(intent);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    running = false;
                    e.printStackTrace();
                }
            }
        }
    });
}
