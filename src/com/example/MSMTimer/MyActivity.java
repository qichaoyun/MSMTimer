package com.example.MSMTimer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */

    private Button bt_start,bt_stop;
    EditText ed_msm,ed_time,ed_phone;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        ed_msm = (EditText) findViewById(R.id.ed_msm);
        ed_time = (EditText) findViewById(R.id.ed_time);
        ed_phone = (EditText) findViewById(R.id.ed_phone);

        ed_msm.setText(getMsnContent());

        bt_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MSMService.isStart = true;
                startMSMServer();
                Toast.makeText(MyActivity.this,"Server start..",0).show();
                bt_start.setEnabled(false);
            }
        });

        bt_stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MSMService.isStart = false;
                bt_start.setEnabled(true);
                stopService(new Intent(MyActivity.this, MSMService.class));
                Toast.makeText(MyActivity.this,"Stop.. start..",0).show();

            }
        });

    }

    private void startMSMServer() {
        String msn = ed_msm.getText().toString();
        String time = ed_time.getText().toString();
        String phone = ed_phone.getText().toString();
        if (TextUtils.isEmpty(msn) || TextUtils.isEmpty(time)) {
            Toast.makeText(this,"输入内容啊...",0).show();
            return;
        }
        MSMService.msm = msn;
        MSMService.time = time;
        MSMService.phone = phone;
        startService(new Intent(this,MSMService.class));
    }

    private String getMsnContent(){
        Calendar calendar = Calendar.getInstance();
        StringBuffer sb = new StringBuffer("今天");
        sb.append(calendar.get(Calendar.DATE)).append("号了,请把剩余的500转我卡上!!!");
        return sb.toString();
    }

}
