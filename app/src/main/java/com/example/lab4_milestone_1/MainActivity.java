package com.example.lab4_milestone_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private Button button;
    private TextView textView;
    private volatile boolean stopThread = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView2);
    }

    public void mockFileDownloader(){

        runOnUiThread(new Runnable(){
            @Override
            public void run(){
                button.setText("DOWNLOADING...");

            }
        });

        for (int downloadProgress = 0; downloadProgress <= 100; downloadProgress = downloadProgress + 10) {

            if (stopThread){

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("");
                    }
                });

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        button.setText("Start");
                    }
                });
                return;
            }

            int finalDownloadProgress = downloadProgress;
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    textView.setText("Download Progress: " + finalDownloadProgress + "%");
                }
            });

            Log.d(TAG, "Download Progress: " + downloadProgress + "%");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                textView.setText("");
            }
        });

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                button.setText("Start");
            }
        });
    }

    class ExampleRunnable implements Runnable{
        @Override
        public void run(){
            mockFileDownloader();
        }
    }

    public void startDownload(View view){
        stopThread = false;
        ExampleRunnable runnable = new ExampleRunnable();
        new Thread(runnable).start();
    }

    public void stopDownload(View view){
        stopThread = true;
    }
}