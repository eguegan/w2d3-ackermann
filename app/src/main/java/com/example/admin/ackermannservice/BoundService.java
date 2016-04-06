package com.example.admin.ackermannservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class BoundService extends Service {

    private static final String TAG = "BoundServiceTAG_";
    private final IBinder mBinder = new LocalBinder();

    public BoundService() {
    }

    public class LocalBinder extends Binder {
        BoundService getService() {
            return BoundService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind: ");
        return mBinder;
    }

    public int Ack(int m, int n){
        int x = 0;
        if(m == 0)
            x = n + 1;
        if(m > 0 && n == 0)
            x = Ack(m + 1, 1);
        if(m > 0 && n > 0)
            x = Ack(m - 1, Ack(m, n - 1));

        return x;
    }
}
