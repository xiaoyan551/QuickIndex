package com.example.xiaoyan.quickindex.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.support.v4.view.MotionEventCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by xiaoyan on 2016/3/2 13:57.
 * 快速索引 绘制a-z
 */
public class QuickIndexBar extends View {

    //-------------------可以在外面定义的属性--------------------
    //绘制的文字
    private static final String[] LETTERS = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    //绘制的字体大小 建议10-12之间
    private int mTextSize = 12;

    //绘制的文本上面留的高度
    private int mSpaceTop = 60;

    //绘制的文本下面留的高度
    private int mSpaceBottom = 60;

    private Paint mPaint;
    private float mCellWidth;
    private float mCellHeight;

    private int index = -1;//按下的索引

    public QuickIndexBar(Context context) {
        this(context, null);
    }

    public QuickIndexBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public QuickIndexBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(Color.WHITE);
        int textSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, mTextSize, getResources().getDisplayMetrics());
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体

    }

    @Override
    protected void onDraw(Canvas canvas) {
        float i = (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 10, getResources().getDisplayMetrics());

        for (int j = 0; j < LETTERS.length; j++) {
            String text = LETTERS[j];

            /**
             * 绘制的单元格的起始点的x坐标
             *  = 单元格的宽/2 - 里面文字的宽/2
             */
            float x = mCellWidth * 0.5f - mPaint.measureText(text) * 0.5f;

            //创建矩形对象
            Rect bounds = new Rect();
            //把text的长宽高赋值给矩形对象
            mPaint.getTextBounds(text, 0, text.length(), bounds);
            //获取矩形的高，矩形的高就也就是文本的高了
            int textHeight = bounds.height();

            /**
             * 绘制的单元格的起始点的y坐标
             * = 单元格的高/2 + 里面文字的高/2
             */
            float y = mCellHeight * 0.5f + textHeight * 0.5f + mCellHeight * j;

            // 如果当前绘制的字母和按下的字母索引一样, 用灰色的画笔
            mPaint.setColor(i == index ? Color.RED : Color.WHITE);

            //注意y轴的坐标要加上距离上端的高度
            canvas.drawText(text, x, y + mSpaceBottom, mPaint);
        }

        super.onDraw(canvas);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        /**
         * 可以在这里获取控件的宽高
         * 控件的宽度，也就是每一个单元格的宽
         */
        mCellWidth = (float) getMeasuredWidth();

        /**
         * 控件的高-绘制文本的上面空的高-下面空的高=文本总的绘制区域的高
         */
        int height = getMeasuredHeight() - mSpaceTop - mSpaceBottom;

        /**
         * 每一个单元格的高 = 控件的高 /单元格个数
         */
        mCellHeight = height * 1.0f / LETTERS.length;

    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {

        float actionY;
        int currentIndex;


        switch (MotionEventCompat.getActionMasked(event)) {
            case MotionEvent.ACTION_DOWN:
                //获取按下点的位置
                actionY = event.getY();
                //按下位置所对应的文本区域索引 要记得减去上文本的上边距
                currentIndex = (int) ((actionY - mSpaceTop) / mCellHeight);

                //判断是否在范围内，防止角标越界
                if (currentIndex >= 0 && currentIndex < LETTERS.length) {
                    //接口回调的方法
                    mOnLetterChangeListener.onLetterChange(LETTERS[currentIndex]);
                }
                index = currentIndex;

                break;
            case MotionEvent.ACTION_MOVE:
                actionY = event.getY();
                currentIndex = (int) ((actionY - mSpaceTop) / mCellHeight);
                if (index != currentIndex && currentIndex >= 0 && currentIndex < LETTERS.length) {
                    mOnLetterChangeListener.onLetterChange(LETTERS[currentIndex]);
                }
                index = currentIndex;


                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                break;

            default:
                break;
        }


        return true;
    }

    /**
     * 定义接口回调,字母更新的监听
     */
    public interface OnLetterChangeListener {
        void onLetterChange(String letter);

    }

    public OnLetterChangeListener getOnLetterChangeListener() {
        return mOnLetterChangeListener;
    }

    public void setOnLetterChangeListener(OnLetterChangeListener onLetterChangeListener) {
        mOnLetterChangeListener = onLetterChangeListener;
    }

    private OnLetterChangeListener mOnLetterChangeListener;

}
