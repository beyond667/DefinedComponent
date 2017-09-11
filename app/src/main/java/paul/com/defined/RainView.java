package paul.com.defined;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhangbiao on 2017/9/8.
 */

public class RainView extends BaseView {

    private List<Rain> rainList;
    private int rainNum;
    private int rainColor;
    private int sizeX;
    private int sizeY;

    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray tr = context.obtainStyledAttributes(attrs, R.styleable.RainView);
        rainNum = tr.getInt(R.styleable.RainView_rain_num, 10);
        rainColor = tr.getInt(R.styleable.RainView_rain_color, 0x00ff00);
        sizeX = tr.getInt(R.styleable.RainView_rain_size_x, 1);
        sizeY = tr.getInt(R.styleable.RainView_rain_size_y, 10);
    }

    @Override
    protected void init() {
        rainList = new ArrayList<>();
        for (int i = 0; i < rainNum; i++) {
            Rain rain = new Rain(getWidth(), getHeight(), sizeX, sizeY, rainColor);
            rainList.add(rain);
        }
    }

    @Override
    protected void drawSub(Canvas canvas) {
        for (Rain rain : rainList) {
            rain.draw(canvas);
        }
    }

    @Override
    protected void logic() {
        for (Rain rain : rainList) {
            rain.move();
        }
    }

}
