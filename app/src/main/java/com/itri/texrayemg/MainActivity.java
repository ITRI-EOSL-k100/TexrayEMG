package com.itri.texrayemg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.timqi.sectorprogressview.ColorfulRingProgressView;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView ivQuadriceps, ivGluteus, ivBiceps;
    TextView tvPercent1, tvPercent2, tvPercent3;
    ColorfulRingProgressView crpv1, crpv2, crpv3;
    Timer timer;
    int tt=10;
    int color = 0;
    int progress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ivQuadriceps = findViewById(R.id.iv_quadriceps);
        ivBiceps = findViewById(R.id.iv_biceps);
        ivGluteus = findViewById(R.id.iv_gluteus);
        tvPercent1 =findViewById(R.id.tvPercent1);
        tvPercent2 =findViewById(R.id.tvPercent2);
        tvPercent3 =findViewById(R.id.tvPercent3);
        crpv1 = findViewById(R.id.crpv1);
        crpv2 = findViewById(R.id.crpv2);
        crpv3 = findViewById(R.id.crpv3);
        timer = new Timer();//時間函示初始化


        int colors[] = { 0xffa6c0cd, 0xff255779, 0xffa6c0cd };



        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tt--;//時間倒數
                        color++;
                        Random random = new Random();
                        int i =random.nextInt(255)+1;
                        int j =random.nextInt(255)+1;
                        int k =random.nextInt(255)+1;
                        int colors[] = { 0xffa6c0cd, 0xff255779, 0xffa6c0cd };

//                        colorMatrix();
                    ivQuadriceps.setColorFilter(Color.argb(100, 255, i, 0));
                    ivGluteus.setColorFilter(Color.argb(100, 255, j, 0));
                    ivBiceps.setColorFilter(Color.argb(100, 255, k, 0));


                        //Transparent Color Effect Over the Image Bitmap in Android
//                        ivQuadriceps.setImageBitmap(setPopArtGradient(MainActivity.this, R.drawable.athos_quadriceps));


//                        bitmapColor();


//                        gradientDrawable(colors);

//                        if (color > 256){
//                            color = 0;
//                        }

                        if (tt < 1) {//if判斷示裡面放置在時間結束後想要完成的事件
                            tt = 10; //讓時間執行緒保持輪迴
                        }
                    }
                });
            }
        };
        timer.schedule(task, 500, 1000);//時間在幾毫秒過後開始以多少毫秒執行
    }

    @Override
    protected void onResume() {
        super.onResume();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    runOnUiThread(new Runnable(){
                        @Override
                        public void run () {
                            Random random = new Random();
                            progress++;
                            crpv1.setPercent(progress);
                            tvPercent1.setText(progress + "");

                            crpv2.setPercent(progress);
                            tvPercent2.setText(progress + "");

                            crpv3.setPercent(progress);
                            tvPercent3.setText(progress + "");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }

                }
            }
        }).start();
    }

    private void setIVColorFilter(int i, int j, int k) {
        ivQuadriceps.setColorFilter(Color.rgb(i, j, k));
        ivBiceps.setColorFilter(Color.rgb(j, k, i));
        ivGluteus.setColorFilter(Color.rgb(k, i, j));
    }

    private void gradientDrawable(int[] colors) {
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.BOTTOM_TOP, colors);
        gradientDrawable.setShape(GradientDrawable.RING);

        ivGluteus.setImageDrawable(gradientDrawable);
    }

    private void bitmapColor() {
        Bitmap myBitmap = ((BitmapDrawable)ivQuadriceps.getDrawable()).getBitmap();
        Bitmap newBitmap = addGradient(myBitmap);
        ivQuadriceps.setImageDrawable(new BitmapDrawable(getResources(), newBitmap));

        Bitmap myBitmap2 = ((BitmapDrawable)ivBiceps.getDrawable()).getBitmap();
        Bitmap newBitmap2 = addGradient(myBitmap2);
        ivBiceps.setImageDrawable(new BitmapDrawable(getResources(), newBitmap2));
    }

    private void colorMatrix() {
//        final float[] float_color_matrix = new float[] { 2.6f, 0, 0.1f, 0, 0,
//                                                    0.1f, 1.2f, 0, 0, 0,
//                                                    0, 0, 0, 0, 0,
//                                                    0, 0, 0, 1, 0 };
        final float[] float_color_matrix = new float[] { 64, 0, 0, 0, 0,
                                                    0, 128, 0, 0, 0,
                                                    0, 0, 0, 0, 0,
                                                    0, 0, 0, 1, 0 };

//        ColorMatrix cm = new ColorMatrix();
////        cm.postConcat(new ColorMatrix(float_color_matrix));
        ColorMatrix cm = new ColorMatrix(float_color_matrix);
        ColorFilter colorFilter = new ColorMatrixColorFilter(cm);

        ivQuadriceps.setColorFilter(colorFilter);
        ivBiceps.setColorFilter(colorFilter);
        ivGluteus.setColorFilter(colorFilter);
    }

    public Bitmap addGradient(Bitmap originalBitmap) {
        int width = originalBitmap.getWidth();
        int height = originalBitmap.getHeight();
        Bitmap updatedBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(updatedBitmap);

        canvas.drawBitmap(originalBitmap, 0, 0, null);

        Paint paint = new Paint();
        LinearGradient shader = new LinearGradient(0, 0, 0, height, 0xFFF0D252, 0xFFF07305, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawRect(0, 0, width, height, paint);

        return updatedBitmap;
    }

    public static Bitmap setPopArtGradient(Context context, int yourDrawableResource) {
        int[] colors = new int[]{Color.parseColor("#FFD900"),Color.parseColor("#FF5300"),
                Color.parseColor("#FF0D00"),Color.parseColor("#AD009F"),
                Color.parseColor("#1924B1")};
        float[] colorPositions = new float[]{0.2f,0.4f,0.6f,0.8f,1.0f};

        final Resources res = context.getResources();
        Drawable drawable = res.getDrawable(yourDrawableResource);

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        /* Create your gradient. */
        LinearGradient grad = new LinearGradient(0, 0, 0, canvas.getHeight(), colors, colorPositions, Shader.TileMode.CLAMP);

        /* Draw your gradient to the top of your bitmap. */
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setAlpha(110); //adjust alpha for overlay intensity
        p.setShader(grad);
        canvas.drawRect(0, 0, canvas.getWidth(), canvas.getHeight(), p);

        return bitmap;
    }


}
