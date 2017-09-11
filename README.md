##编写自定义view步骤
* [自定义View的属性](#line1)
* [在View的构造方法中获得我们自定义的属性](#line2)
* [重写onMesure（非必须）](#line3)
* [重写onDraw](#line4)


<a name="line1"/>
###自定义View的属性
首先在res/values/  下建立一个attrs.xml ， 在里面定义我们的属性和声明我们的整个样式

```java
<resources>
    <declare-styleable name="RainView">
        <attr name="rain_num" format="integer" />
        <attr name="rain_color" format="integer" />
        <attr name="rain_size_x" format="integer" />
        <attr name="rain_size_y" format="integer" />
    </declare-styleable>

</resources>
```
format支持的类型有string,boolean,integer,float,reference,color,dimension,fraction,enum,flag

```java
 <declare-styleable name = "名称">
	<attr name = "def_value" format = "string" />
	<attr name = "is_running" format = "boolean" />
	<attr name = "def_size" format = "integer" />
	<attr name = "def_location" format = "float" />
    <attr name = "background" format = "reference" />
	<attr name = "textColor" format = "color" />
	<attr name = "layout_width" format = "dimension" />
	<attr name = "pivotX" format = "fraction" />
	<attr name="orientation">
    	<enum name="horizontal" value="0" />
        <enum name="vertical" value="1" />
    </attr>
    <attr name="windowSoftInputMode">
    	<flag name = "stateUnspecified" value = "0" />
        <flag name = "stateUnchanged" value = "1" />
        <flag name = "stateHidden" value = "2" />
        <flag name = "stateAlwaysHidden" value = "3" />
        <flag name = "adjustUnspecified" value = "0x00" />
        <flag name = "adjustResize" value = "0x10" />
        <flag name = "adjustPan" value = "0x20" />
    </attr>
 </declare-styleable>
```
在布局文件中引入自定义属性

```java
    <paul.com.defined.RainView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@drawable/rain"
        rain:def_value="aaa"
        rain:is_running="true"
        rain:def_size="30"
        rain:def_location="1.0"
		rain:background="@drawable/rain"
		rain:textColor="#00FF00"
		rain:layout_width="20dp"
		android:pivotX = "200%"
		rain:orientation="horizontal"
        rain:windowSoftInputMode="stateUnspecified|adjustPan" />
```

<a name="line2"/>
###在View的构造方法中获得我们自定义的属性

```java
   public class RainView extends View {

	private int rainNum ;
	private int rainColor;
    public RainView(Context context) {
        super(context);
    }

    public RainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray tr = context.obtainStyledAttributes(attrs, R.styleable.RainView);
        rainNum = tr.getInt(R.styleable.RainView_rain_num, 10);
        rainColor = tr.getInt(R.styleable.RainView_rain_color, 0x00ff00);
    }
}
```

<a name="line3"/>
###重写onMeasure(略)

<a name="line4"/>
###重写onDraw

```java
    @Override
    protected void onDraw(Canvas canvas)
    {
		Paint paint = new Paint();
        //设置paint字体大小30像素
        paint.setTextSize(30);
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
        RectF rectf = new RectF(10.0, 210.0, 100.0, 300);
        canvas.drawRect(rectf, paint);
        //画圆角矩形
//        canvas.drawRoundRect(rectf,10,10,paint);
		//画扇形（从0度开始，到startAngle度）
 		canvas.drawArc(rectRound, 0, startAngle, true, colorPaint);

        //画bitmap
        canvas.drawBitmap(bitmap, 10, 330, paint);
 		canvas.drawArc(rectRound, 0, startAngle, true, colorPaint);

    }
```
paint支持画文字，直线，（圆角）矩形，扇形，bitmap。

好了，下面是本demo的效果图
![demo](screenshot_rain.png)


##有问题反馈
在使用中有任何问题，欢迎反馈给我，可以用以下联系方式跟我交流

* 邮件(zhangbiao00@gmail.com)
* QQ: 552499041