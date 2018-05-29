package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.vironit.correctapp.R;

public class CustomView extends android.support.v7.widget.AppCompatImageView {

    private Paint mPaintCustomView;
    private Path mPath;
    private float radius;
    private float cx;
    private float cy;
    private RectF rect;

    private Paint imgPaint;
    private Bitmap roundBitmap;


    public CustomView(Context context) {
        super(context);
        init();
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        cx = w / 2;
        cy = h / 2;
        radius = cx;

        Drawable drawable = getDrawable();
        if (drawable != null) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            roundBitmap = getRoundedCroppedBitmap(bitmap, getWidth());
        } else {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.x_1db71d96);
            roundBitmap = getRoundedCroppedBitmap(bitmap, getWidth());
        }

    }

    private void init() {

        imgPaint = new Paint();
        imgPaint.setAntiAlias(true);
        imgPaint.setFilterBitmap(true);
        imgPaint.setDither(true);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(roundBitmap, cx, cy, imgPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth(), imgPaint);

    }

    private Bitmap getRoundedCroppedBitmap(Bitmap bitmap, int radius) {
        Bitmap finalBitmap = bitmap.createScaledBitmap(bitmap, radius, radius, false);
        Bitmap output = Bitmap.createBitmap(finalBitmap.getWidth(), finalBitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Rect rect = new Rect(0, 0, output.getWidth(), output.getHeight());

        Canvas canvas = new Canvas(output);
        canvas.drawCircle(
                finalBitmap.getWidth() / 2,
                finalBitmap.getHeight() / 2,
                finalBitmap.getWidth() / 2,
                imgPaint);

        imgPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(finalBitmap, rect, rect, imgPaint);

        return output;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}
