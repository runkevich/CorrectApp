package com.vironit.correctapp.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public abstract class ConverterUtil {

    public static Bitmap convertToBitmap(Drawable drawable, int width, int height) {
        if (drawable instanceof BitmapDrawable) {
            return scaleBitmap(
                    ((BitmapDrawable)drawable).getBitmap(), width, height);
        }

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        drawable.draw(canvas);

        return scaleBitmap(bitmap, width, height);
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int reqWidth, int reqHeight) {
        Matrix m = new Matrix();
        int scaledWidth = reqWidth;
        int scaledHeight = reqHeight;
        int bmWidth = bitmap.getWidth();
        int bmHeight = bitmap.getHeight();
        if (bmWidth > bmHeight) {
            scaledWidth = bmWidth * scaledHeight / bmHeight;
        } else if (bmWidth < bmHeight) {
            scaledHeight = bmHeight * scaledWidth / bmWidth;
        }
        m.setRectToRect(
                new RectF(0, 0, bmWidth, bmHeight),
                new RectF(0, 0, scaledWidth, scaledHeight),
                Matrix.ScaleToFit.CENTER);
        return Bitmap.createBitmap(
                bitmap, 0, 0, bmWidth, bmHeight, m, true);
    }
}
