package paul.com.defined;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangbiao on 2017/9/8.
 */

public abstract class BaseView extends View {
    private Thread thread;
    private boolean isRunning = true;

    public BaseView(Context context) {
        super(context);
    }

    public BaseView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    protected abstract void drawSub(Canvas canvas);

    protected abstract void logic();

    protected abstract void init();

    @Override
    protected final void onDraw(Canvas canvas) {
        if (thread == null) {
            thread = new MyThread();
            thread.start();
        } else {
            drawSub(canvas);
        }
    }

    class MyThread extends Thread {
        @Override
        public void run() {
            init();
            while (isRunning) {
                logic();
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
