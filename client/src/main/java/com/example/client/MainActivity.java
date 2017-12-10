package com.example.client;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.remoteservice.RemoteStub;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RemoteStub mBinder = null;

    ServiceConnection srvConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 원격 서비스 연결 성공시 할 일
            mBinder = RemoteStub.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 원격 서비스 연결 종료시 할 일
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) this.findViewById(R.id.startbutton);
        startButton.setOnClickListener(this);

        Button getButton = (Button) this.findViewById(R.id.getbutton);
        getButton.setOnClickListener(this);

        Button stopButton = (Button) this.findViewById(R.id.stopbutton);
        stopButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startbutton:
                Intent intent = new Intent();
                ComponentName name = new ComponentName("com.example.remoteservice", "com.example.remoteservice.RemoteService");
                intent.setComponent(name);
                bindService(intent, srvConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.getbutton:
                String packageName = null;
                try {
                    packageName = mBinder.getServerPackageName();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

                TextView text = (TextView) this.findViewById(R.id.packagenametxt);
                text.setText(packageName);
                break;
            case R.id.stopbutton:
                this.unbindService(srvConn);
                break;
            default:
                break;
        }
    }
}
