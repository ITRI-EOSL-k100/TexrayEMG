package com.itri.texrayemg;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView ivQuadriceps, ivGluteus, ivBiceps;
    Timer timer;
    int tt=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivQuadriceps = findViewById(R.id.iv_quadriceps);
        ivGluteus = findViewById(R.id.iv_gluteus);
        ivBiceps = findViewById(R.id.iv_biceps);
        timer = new Timer();//時間函示初始化


        int colors[] = { 0xffa6c0cd, 0xff255779, 0xffa6c0cd };



        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tt--;//時間倒數
                        Random random = new Random();
                        int i =random.nextInt(255)+1;
                        int j =random.nextInt(255)+1;
                        int k =random.nextInt(255)+1;
                        int colors[] = { 0xffa6c0cd, 0xff255779, 0xffa6c0cd };

//                        GradientDrawable gradientDrawable = new GradientDrawable(
//                                GradientDrawable.Orientation.BOTTOM_TOP, colors);
//                        gradientDrawable.setShape(GradientDrawable.RING);
//
//                        ivGluteus.setImageDrawable(gradientDrawable);
                        final float[] float_color_matrix = new float[] { 2.6f, 0, 0.1f, 0, 0, 0.1f, 1.2f, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 };

                        ColorMatrix cm = new ColorMatrix();
                        cm.postConcat(new ColorMatrix(float_color_matrix));
                        ColorFilter colorFilter = new ColorMatrixColorFilter(cm);

                        ivQuadriceps.setColorFilter(colorFilter);
                        ivBiceps.setColorFilter(colorFilter);
                        ivGluteus.setColorFilter(colorFilter);
//                        ivQuadriceps.setColorFilter(Color.rgb(i, j, k));
//                        ivBiceps.setColorFilter(Color.rgb(j, k, i));
//                        ivGluteus.setColorFilter(Color.rgb(k, i, j));
//                        t1.setText(tt+"second");//讓TextView元件顯示時間倒數情況
                        //if判斷示裡面放置在時間結束後想要完成的事件
                        if (tt < 1) {
                            tt = 10; //讓時間執行緒保持輪迴
                        }
                    }
                });
            }
        };
        timer.schedule(task, 1000, 1000);//時間在幾毫秒過後開始以多少毫秒執行
    }
}
