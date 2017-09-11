package paul.com.defined;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by zhangbiao on 2017/9/8.
 */

public class Rain {

    private Paint paint = new Paint();
    private Random random = new Random();
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private int width;
    private int height;
    private float opt;
    private int sizeX;
    private int sizeY;
    private int color;

    public Rain(int width, int height, int sizeX, int sizeY, int color) {
        this.width = width;
        this.height = height;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.color = color;
        init();
    }

    private void init() {
        sizeX = 1 + random.nextInt(10);
        sizeY = 10 + random.nextInt(20);
        startX = random.nextInt(width);
        startY = random.nextInt(height);
        endX = sizeX + startX;
        endY = sizeY + startY;
        opt = 0.2f + random.nextFloat();
        paint.setColor(color);
    }

    public void draw(Canvas canvas) {
        canvas.drawLine(startX, startY, endX, endY, paint);
    }

    public void move() {
        startX += opt * sizeX;
        endX += opt * sizeX;

        startY += opt * sizeY;
        endY += opt * sizeY;

        if (startY > height || startX > width) {
            init();
        }
    }

}
