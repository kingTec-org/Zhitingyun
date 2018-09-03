package coder.mylibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;


/**
 * SideBar
 * Created by D on 2017/6/6.
 */
public class SideBar extends View {
    private final String[] c = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
            "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private int width;
    private int height;
    private Rect rect;
    private RectF rectF;
    private Paint paint;
    private Paint paintCur;
    private Paint paintRect;
    private int colorTrans, colorWhite, colorBar, colorRect;
    private int count;
    private int onpice;
    private int widthBar;
    private int widthRect;
    private int rectRadius;
    private float textHeight;
    private float textLightHeight;
    private boolean dValid;
    private int index = -1;
    private float moveY = 0;
    private OnLetterChangedListener listener;

    public SideBar(Context context) {
        this(context, null);
    }

    public SideBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        count = c.length;
        widthRect = dip2px(context, 16);
        rectRadius = dip2px(context, 6);
        colorTrans = Color.parseColor("#00000000");
        colorWhite = Color.parseColor("#ffffff");
        colorBar = Color.parseColor("#aaBBBBBB");
        colorRect = Color.parseColor("#B52C39");

        rect = new Rect();
        rectF = new RectF();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setColor(Color.parseColor("#de666666"));

        paintCur = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintCur.setTextAlign(Paint.Align.CENTER);
        paintCur.setColor(Color.parseColor("#B52C39"));

        paintRect = new Paint(Paint.ANTI_ALIAS_FLAG);
        paintRect.setTextAlign(Paint.Align.CENTER);
        paintRect.setTextSize(dip2px(context, 16));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        resetRect(width - widthBar, 0, width, height, index == -1 ? colorTrans : colorBar);
//        canvas.drawRect(rectF, paintRect);

        for (int i = 0; i < count; i++) {
            canvas.drawText(c[i], width - widthBar / 2, 100 + onpice * i + onpice / 2 + textHeight / 2, i == index ? paintCur : paint);
        }
        if (index >= 0 && index < count) {

            resetRect(width - widthBar - widthRect - widthRect / 2, 50 + (int) moveY - widthRect, width - widthBar - widthRect / 2, 50 + (int) moveY, colorRect);
            canvas.drawRect(rectF, paintRect);

            canvas.drawCircle(width - widthBar - widthRect - widthRect / 2, 50 + (int) moveY - widthRect, widthRect, paintRect);
            paintRect.setColor(colorWhite);
            canvas.drawText(c[index], width - widthBar - widthRect - widthRect / 2, 50 + (int) moveY - widthRect + textLightHeight / 2, paintRect);

//            resetRect((width - widthRect) / 2, (height - widthRect) / 2, (width + widthRect) / 2, (height + widthRect) / 2, colorRect);
//            canvas.drawRect(rectF, paintRect);
//            canvas.drawCircle((width - widthRect) / 2, (height - widthRect) / 2, widthRect, paintRect);
//
//            paintRect.setColor(colorWhite);
//            canvas.drawText(c[index], (width - widthRect) / 2, (height - widthRect + textLightHeight) / 2, paintRect);
        }
    }

    private void resetRect(int left, int top, int right, int bottom, int color) {
        rect.set(left, top, right, bottom);
        rectF.set(rect);
        paintRect.setColor(color);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
        onpice = (height - 100) / count;
        widthBar = (int) (onpice * 1.182f);
        int textSize = dip2px(getContext(), onpice * 0.26f);
        paint.setTextSize(textSize);
        paintCur.setTextSize(textSize);
        textHeight = getTextHeight(paint);
        textLightHeight = getTextHeight(paintRect);
        setMeasuredDimension(width, height);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eX = event.getX();
        float eY = event.getY() - 100;
        moveY = eY;
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                dValid = eX > width - widthBar;
                return delegate(adjustIndex(eY));
            case MotionEvent.ACTION_MOVE:
                return delegate(adjustIndex(eY));
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                return delegate(-1);
        }
        return super.onTouchEvent(event);
    }

    private int adjustIndex(float eY) {
        eY = Math.max(eY, 0);
        eY = Math.min(eY, height);
        int i = (int) (eY / onpice);
        i = Math.max(i, 0);
        i = Math.min(i, count - 1);
        return i;
    }

    private boolean delegate(int i) {
        if (dValid && i != index) {
            index = i;
            if (index != -1 && listener != null) {
                listener.onChange(index, c[index]);
            }
            invalidate();
            return true;
        }
        return false;
    }

    public interface OnLetterChangedListener {
        void onChange(int index, String c);
    }

    public void setOnLetterChangedListener(OnLetterChangedListener listener) {
        this.listener = listener;
    }

    public static int dip2px(Context context, float dpValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (dpValue * (metrics.densityDpi / 160f));
    }

    /**
     * 获取字体高度
     */
    public static float getTextHeight(Paint p) {
        Paint.FontMetrics fm = p.getFontMetrics();// 获取字体高度
        return (float) ((Math.ceil(fm.descent - fm.top) + 2) / 2);
    }
}
