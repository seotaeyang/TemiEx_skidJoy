package com.example.temiex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;

import com.robotemi.sdk.Robot;

public class MainActivity extends AppCompatActivity {

    Button btn;
    Handler handler;
    Robot robot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        handler = new Handler(Looper.getMainLooper());
        robot = Robot.getInstance();

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                // 500ms 간격으로 작업을 반복하기 위한 Runnable
                Runnable skidJoyRunnable = new Runnable(){
                    float k = 1;
                    @Override
                    public void run(){

                        float a = 0.1F;
                        // robot.skidJoy(1, 0) 실행
                        robot.skidJoy(k,0);
                        k = k - a;
                        //500ms 후에 다시 실행
                        handler.postDelayed(this,500);
                    }
                };
                handler.postDelayed(skidJoyRunnable, 0);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        handler.removeCallbacks(skidJoyRunnable);
                    }
                }, 10000);// 총 이동시간
            }

        });
    }
}