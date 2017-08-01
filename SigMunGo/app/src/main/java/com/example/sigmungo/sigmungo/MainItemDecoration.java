package com.example.sigmungo.sigmungo;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.example.sigmungo.sigmungo.Items.MainItems;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainItemDecoration extends RecyclerView.ItemDecoration {
    private Context mContext;
    private ImageView imageView;

    public MainItemDecoration(Context context, ImageView imageView){
        this.mContext = context;
        this.imageView = imageView;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        MainItems items = new MainItems();
        Bitmap original = BitmapFactory.decodeResource(mContext.getResources(), items.getRestaurantImage());
        Bitmap mask = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.mask_img_1);
        Bitmap result = Bitmap.createBitmap(mask.getWidth(), mask.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas mCanvas = new Canvas(result);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mCanvas.drawBitmap(original, 0, 0, null);
        mCanvas.drawBitmap(mask, 0, 0, paint);
        paint.setXfermode(null);
        imageView.setBackgroundResource(R.drawable.main_recyclerview);
    }
}
