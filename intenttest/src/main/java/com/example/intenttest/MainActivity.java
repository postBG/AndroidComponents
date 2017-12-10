package com.example.intenttest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button callButton = (Button) this.findViewById(R.id.callbutton);
        callButton.setOnClickListener(this);

        Button smsButton = (Button) this.findViewById(R.id.smsbutton);
        smsButton.setOnClickListener(this);

        Button mailButton = (Button) this.findViewById(R.id.mailbutton);
        mailButton.setOnClickListener(this);

        Button webButton = (Button) this.findViewById(R.id.webbutton);
        webButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Uri uri = null;

        switch (v.getId()) {
            case R.id.callbutton:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 100);
                        Toast.makeText(this,
                                "안드로이드 6.0 마시멜로부터 일부 권한에 대해 사용자에게 동의 필요!", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                uri = Uri.parse("tel:01099990000");
                intent = new Intent(Intent.ACTION_CALL, uri);
                startActivity(intent);
                break;
            case R.id.smsbutton:
                uri = Uri.parse("smsto:01099990000");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", "Hi");
                startActivity(intent);
                break;
            case R.id.mailbutton:
                uri = Uri.parse("mailto:profile269@gmail.com");
                intent = new Intent(Intent.ACTION_SENDTO, uri);
                startActivity(intent);
                break;
            case R.id.webbutton:
                uri = Uri.parse("http://naver.com");
                intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
