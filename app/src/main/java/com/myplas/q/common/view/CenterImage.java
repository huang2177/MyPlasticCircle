package com.myplas.q.common.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.myplas.q.R;
import com.myplas.q.common.utils.AndroidUtil;

/**
 * 作者:huangshuang
 * 事件 2017/9/26 0026.
 * 邮箱： 15378412400@163.com
 */

public class CenterImage extends ImageView {
    private Paint paint;
    private Bitmap bitmap;
    private Context mContext;
    private boolean isCenterImgShow;

    public void setCenterImgShow(boolean centerImgShow) {
        isCenterImgShow = centerImgShow;
        if (isCenterImgShow) {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_free);
            invalidate();
        }
    }

    public CenterImage(Context context) {
        super(context);
        init(context);
    }

    public CenterImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CenterImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        paint = new Paint();
        this.mContext = context;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isCenterImgShow && bitmap != null) {
            canvas.drawBitmap(bitmap
                    , getMeasuredWidth() - bitmap.getWidth() - AndroidUtil.dp2px(mContext, 10)
                    , 0
                    , paint);
        }
    }
}
