package tw.com.frankchang.houli.sunday_classno_03_servicewithbroadcastreceiver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by 張景翔 on 2017/5/12.
 */

public class MyServiceWithBroadcastReceiver extends Service {

    private int count;
    private Timer timer;
    private Intent receiverintent;

    public MyServiceWithBroadcastReceiver() {
        timer = new Timer();
        receiverintent = new Intent("myreceiver");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 1000, 1000);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        count = intent.getIntExtra(getString(R.string.key_01), -1);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        timer.cancel();
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }

    private class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            count++;
            receiverintent.putExtra(getString(R.string.key_02), count);
            sendBroadcast(receiverintent);
        }
    }
}
