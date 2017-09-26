package com.myoungchi.android.sigmungo.Items;

/**
 * Created by geni on 2017. 7. 26..
 */

public class MainItems {
    private String contentID;
    private String restaurantName;
    private String restuarantLocation;
    private int sympathyCount;
    private int improved;
    private String image;

    public String getContentID() {
        return contentID;
    }

    public void setContentID(String contentID) {
        this.contentID = contentID;
    }

    public int getSympathyCount() {
        return sympathyCount;
    }

    public void setSympathyCount(int sympathyCount) {
        this.sympathyCount = sympathyCount;
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

    public int getImproved() {
        return improved;
    }

    public void setImproved(int improved) {
        this.improved = improved;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
