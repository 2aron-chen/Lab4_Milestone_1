package com.example.lab4_milestone_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void mockFileDownloader(){
        for (int downloadProgress = 0; downloadProgress <= 100; downloadProgress = downloadProgress + 10) {
            Log.d(TAG, "Download Progress: " + downloadProgress + "%");
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public void startDownload(View view){
        mockFileDownloader();
    }
}