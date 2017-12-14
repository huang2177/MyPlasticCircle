package com.myplas.q.common.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Xfermode;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

import com.myplas.q.R;

import java.text.NumberFormat;

/**
 * @author 黄双
 * @date 2017/12/14 0014
 */

public class ProgressImageView extends ImageView {
    /***************** 外部可改变值 *****************/
    /**
     * 进度值
     */
    private float progress;
    /**
     * 圆角大小，单位pixel
     */
    private int roundPixel = 0;
    /**
     * 是否使用进度值蒙版
     */
    private boolean useProgress = false;
    /**
     * 阴影的颜色
     */
    private int roundShadowColor;
    /**
     * 进度文字大小
     */
    private float roundProgressTextSize;
    /**
     * 进度文字颜色
     */
    private int roundProgressTextColor;

    /***************** 内部使用值 *****************/
    /**
     * 画笔对象
     */
    private Paint paint;
    /**
     * 两张图片相交时的模式
     */
    private Xfermode xfermode;
    /**
     * 进度文字区域Rect
     */
    private Rect rect;
    /**
     * 圆角矩形所占RectF
     */
    private RectF rectF;
    /**
     * float格式化成99.99% String
     */
    private NumberFormat numberFormat;

    public ProgressImageView(Context context) {
        super(context);
        init(null);
    }

    public ProgressImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs != null) {
            TypedArray t = getResources().obtainAttributes(attrs, R.styleable.ProgressImageView);
            roundPixel = t.getDimensionPixelSize(R.styleable.ProgressImageView_roundPixel, 0);
            useProgress = t.getBoolean(R.styleable.ProgressImageView_useProgress, false);
            roundShadowColor = t.getColor(R.styleable.ProgressImageView_roundShadowColor, Color.parseColor("#a6292929"));
            roundProgressTextSize = t.getDimensionPixelSize(R.styleable.ProgressImageView_roundProgressTextSize,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17, getResources().getDisplayMetrics()));
            roundProgressTextColor = t.getColor(R.styleable.ProgressImageView_roundProgressTextColor, Color.WHITE);
            t.recycle();
        } else {
            roundShadowColor = Color.parseColor("#a6292929");
            roundProgressTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 17, getResources().getDisplayMetrics());
            roundProgressTextColor = Color.WHITE;
        }
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        xfermode = new PorterDuffXfermode(PorterDuff.Mode.DST_IN);
        rect = new Rect();
        numberFormat = NumberFormat.getPercentInstance();
        numberFormat.setMaximumFractionDigits(2);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (useProgress) {
            canvas.saveLayer(0, 0, getWidth(), getHeight(), paint, Canvas.ALL_SAVE_FLAG);
            //带阴影的圆角矩形框
            paint.setColor(roundShadowColor);
            if (null == rectF) {
                rectF = new RectF(0, 0, getWidth(), getHeight());
            }
            canvas.drawRoundRect(rectF, roundPixel, roundPixel, paint);
            //设置两张图片相交时的模式
            paint.setXfermode(xfermode);
            //上部分阴影
            paint.setColor(roundShadowColor);
            canvas.drawRect(0, 0, getWidth(), getHeight() * (1 - progress), paint);
            //下部分透明
            paint.setColor(Color.TRANSPARENT);
            canvas.drawRect(0, getHeight() * (1 - progress), getWidth(), getHeight(), paint);
            if (progress < 1f) {
                paint.setXfermode(null);
                paint.setTextSize(roundProgressTextSize);
                paint.setColor(roundProgressTextColor);
                paint.setStrokeWidth(2);
                String progressStr = numberFormat.format(progress);
                paint.getTextBounds(progressStr, 0, progressStr.length(), rect);
                Paint.FontMetricsInt metricsInt = paint.getFontMetricsInt();
                canvas.drawText(progressStr, getWidth() / 2 - rect.width() / 2, (getHeight() - metricsInt.bottom - metricsInt.top) / 2, paint);
            }
        }
    }

    public boolean isUseProgress() {
        return useProgress;
    }

    public void setUseProgress(boolean useProgress) {
        this.useProgress = useProgress;
    }

    public int getRoundPixel() {
        return roundPixel;
    }

    public void setRoundPixel(int roundPixel) {
        this.roundPixel = roundPixel;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        postInvalidate();
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
    }
}
