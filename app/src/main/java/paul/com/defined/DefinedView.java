package paul.com.defined;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by zhangbiao on 2017/9/8.
 */

public class DefinedView extends View {
    private Bitmap bitmap;
    private String marqueeText = "这里是自定义滚动条";
    private Thread thread;
    private float rectX;
    private float rectY;
    Paint paint = new Paint();
    Paint colorPaint = new Paint();

    private int startAngle = 0;


    public DefinedView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    public DefinedView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //设置paint字体大小30像素
        paint.setTextSize(30);
        colorPaint.setTextSize(30);
        //画文字
        canvas.drawText("Hello Word", 0, 30, paint);

        //画直线
        canvas.drawLine(0, 60, 120, 60, paint);

        //画矩形
        //第一种，直接根据左上右下坐标来画
        canvas.drawRect(0, 90, 90, 180, paint);
        //第二种，在Rect里定义左上右下坐标来画
        Rect rect = new Rect(10, 90, 100, 180);
        canvas.drawRect(rect, paint);
        //第三种，在RectF里定义左上右下坐标来画，支持float
        RectF rectf = new RectF(10, 210, 100, 300);
        canvas.drawRect(rectf, paint);
        //画圆角矩形
//        canvas.drawRoundRect(rectf,10,10,paint);

        //画bitmap
        canvas.drawBitmap(bitmap, 10, 330, paint);


        //自定义滚动条
        canvas.drawText(marqueeText, rectX, 450, colorPaint);
        //从上往下移动
        canvas.drawText("下雨啦，收衣服啊~~~~~~", 300, rectY, colorPaint);

        RectF rectRound = new RectF(10, 480, 100, 570);
        canvas.drawArc(rectRound, 0, startAngle, true, colorPaint);


        if (thread == null) {
            thread = new MyThread();
            thread.start();
        }
    }

    class MyThread extends Thread {
        Random random = new Random();

        @Override
        public void run() {
            while (true) {
                rectX += 3;
                if (rectX > getWidth()) {
                    rectX = 0 - paint.measureText(marqueeText);
                }
                rectY += 3;
                if (rectY > getHeight()) {
                    rectY = -30;
                }
                startAngle++;
                if (startAngle > 360) {
                    startAngle = 0;
                }

                colorPaint.setARGB(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));
                postInvalidate();

                try {
                    sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
