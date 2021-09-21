package com.example.service.services;

import static android.content.ContentValues.TAG;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.service.MainActivity;

public class MyDownloadService extends Service {
private static final String TAG = "MyTag";
    //this is started service
    public MyDownloadService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Log.d(TAG,"onStartCommand: "+Thread.currentThread().getName());
        final String songName = intent.getStringExtra(MainActivity.MESSAGE_KEY);

        Thread thread = new Thread(new Runnable()
            {
                @Override
                public void run() {
                   downloadSong(songName);
                }
            });


//        downloadSong(songName);

        thread.start();
        return  Service.START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
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
}
