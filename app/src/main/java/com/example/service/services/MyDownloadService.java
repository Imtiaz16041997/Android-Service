package com.example.service.services;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.service.DownloadThread;
import com.example.service.MainActivity;

public class MyDownloadService extends Service {
private static final String TAG = "MyTag";
private DownloadThread mDownloadThread;

    //this is started service
    public MyDownloadService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate: called");
        mDownloadThread = new DownloadThread();
        mDownloadThread.start();

        while (mDownloadThread.mHandler == null) {

        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: "+Thread.currentThread().getName());
        final String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        Message message = Message.obtain();
        message.obj = songName;
        mDownloadThread.mHandler.sendMessage(message);


        return  Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind: called ");
        return null;
    }

    private void downloadSong (final String songName)
    {
        Log.d(TAG,"run: starting download");

        try {
            Thread.sleep(4000);
        }catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        Log.d(TAG, "downloadSong: "+songName+" Downloaded..." );
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy: called ");

    }
}
