package com.example.sigmungo.sigmungo.Items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.sigmungo.sigmungo.R;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainItems {
    private int restaurantImage;
    private String restaurantName;
    private String restuarantLocation;
    private int sympathyCount;

    public int getSympathyCount() {
        return sympathyCount;
    }

    public void setSympathyCount(int sympathyCount) {
        this.sympathyCount = sympathyCount;
    }

    public int getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(int restaurantImage) {
        this.restaurantImage = restaurantImage;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestuarantLocation() {
        return restuarantLocation;
    }

    public void setRestuarantLocation(String restuarantLocation) {
        this.restuarantLocation = restuarantLocation;
    }
}
