package com.example.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class PackageStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if ("android.intent.action.PACKAGE_ADDED".equals(action)) {
            Toast.makeText(context, "PACKAGE_ADDED", Toast.LENGTH_LONG).show();
        } else if ("android.intent.action.PACKAGE_DATA_CLEARED".equals(action)) {
            Toast.makeText(context, "PACKAGE_DATA_CLEARED", Toast.LENGTH_LONG).show();
        } else if ("android.intent.action.PACKAGE_REMOVED".equals(action)) {
            Toast.makeText(context, "PACKAGE_REMOVED", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "Unknown State", Toast.LENGTH_LONG).show();
        }
    }
}
