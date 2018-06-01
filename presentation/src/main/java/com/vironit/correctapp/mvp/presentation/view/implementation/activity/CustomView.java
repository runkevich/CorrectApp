package com.vironit.correctapp.mvp.presentation.view.implementation.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import com.vironit.correctapp.R;

public class CustomView extends android.support.v7.widget.AppCompatImageView {

    private final float RADIUS = getResources().getDimension(R.dimen.default_radius);
    private final float SIZE = getResources().getDimension(R.dimen.default_avatar_size);

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

    private void init() {
        setLayerType(LAYER_TYPE_HARDWARE,null);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint shapePaint = new Paint();
        canvas.drawPath(drawShape(), shapePaint);
        Paint photoPaint = new Paint();
        photoPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
       // Bitmap photo = ConverterUtil.convertToBitmap(
        //        ContextCompat.getDrawable(getContext(), R.mipmap.x_1db71d96), (int) SIZE, (int) SIZE);

        setScaleType(ScaleType.CENTER_CROP);
       // canvas.drawBitmap(photo, 0, 0, photoPaint);
    }


    private Path drawShape() {
        Path path = new Path();
        path.moveTo(RADIUS, 0);
        path.lineTo(SIZE - RADIUS, 0);
        path.lineTo(SIZE, RADIUS);
        path.lineTo(SIZE, SIZE);
        path.lineTo(0, SIZE);
        path.lineTo(0, RADIUS);
        path.quadTo(0, 0, RADIUS, 0);
        path.close();
        return path;
    }
}
