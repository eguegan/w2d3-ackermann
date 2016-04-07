package com.example.admin.ackermannservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private BoundService mBoundService;
    private EditText mEditText1;
    private EditText mEditText2;
    private TextView mTextViewResult;
    private boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditText1 = (EditText) findViewById(R.id.am_ed_1);
        mEditText2 = (EditText) findViewById(R.id.am_ed_2);
        mTextViewResult = (TextView) findViewById(R.id.am_tv_result);

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
    }

    public void doAckermann(View v){

        Log.d("TAG", "onServiceConnected: " + mBoundService.Ack(Integer.parseInt(mEditText1.getText().toString()), Integer.parseInt(mEditText2.getText().toString())));

    }

    public void doAck2(View v){
        new AckAsync(mEditText1, mEditText2, mTextViewResult).execute();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            BoundService.LocalBinder binder = (BoundService.LocalBinder) service;
            mBoundService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            isBound = false;
        }
    };
}
