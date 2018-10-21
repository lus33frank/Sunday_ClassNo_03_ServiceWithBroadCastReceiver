package tw.com.frankchang.houli.sunday_classno_03_servicewithbroadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvShow;

    private Intent intent;
    private MyBroadcastReceiver myBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findviewer();
    }

    private void findviewer() {
        tvShow = (TextView)  findViewById(R.id.textView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        //建立MyBroadcastReceiver物件
        myBroadcastReceiver = new MyBroadcastReceiver();
        //註冊接收IntentFilter
        registerReceiver(myBroadcastReceiver, new IntentFilter("myreceiver"));

        //產生Intent物件
        intent = new Intent(this, MyServiceWithBroadcastReceiver.class);
    }

    public void startOnClick(View view) {
        intent.putExtra(getString(R.string.key_01), (int)(Math.random()*42)+1);
        startService(intent);
    }

    public void stopOnClick(View view) {
        stopService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (myBroadcastReceiver != null){
            unregisterReceiver(myBroadcastReceiver);
            myBroadcastReceiver = null;
        }
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            int code = intent.getIntExtra(getString(R.string.key_02), -1);
            tvShow.setText("Value：" + code);
        }
    }
}
