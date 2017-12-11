package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private BroadcastReceiver screenOnOffReceiver = null;
    private IntentFilter screenOnOffFilter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        screenOnOffReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();

                switch (action) {
                    case Intent.ACTION_SCREEN_ON:
                        Toast.makeText(context, "ACTION_SCREEN_ON", Toast.LENGTH_LONG).show();
                        break;
                    case Intent.ACTION_SCREEN_OFF:
                        Toast.makeText(context, "ACTION_SCREEN_OFF", Toast.LENGTH_LONG).show();
                        break;
                    default:
                        Toast.makeText(context, "Unknown Action", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        screenOnOffFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        screenOnOffFilter.addAction(Intent.ACTION_SCREEN_OFF);

        registerReceiver(screenOnOffReceiver, screenOnOffFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (screenOnOffReceiver != null) {
            unregisterReceiver(screenOnOffReceiver);
        }
    }
}
