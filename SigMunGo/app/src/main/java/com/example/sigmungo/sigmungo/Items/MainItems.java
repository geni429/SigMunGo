package com.example.sigmungo.sigmungo.Items;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;

import com.example.sigmungo.sigmungo.R;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainItems {
    private String contentID;
    private String restaurantImage;
    private String restaurantName;
    private String restuarantLocation;
    private String sympathyCount;
    private String improved;

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public String getSympathyCount() {
        return sympathyCount;
    }

    public void setSympathyCount(String sympathyCount) {
        this.sympathyCount = sympathyCount;
    }

    public String getRestaurantImage() {
        return restaurantImage;
    }

    public void setRestaurantImage(String restaurantImage) {
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

    public String getImproved() {
        return improved;
    }

    public void setImproved(String improved) {
        this.improved = improved;
    }
}
