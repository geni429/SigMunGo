package com.myoungchi.android.sigmungo.Items;

import io.realm.RealmObject;

/**
 * Created by geni on 2017. 9. 21..
 */

public class UserData extends RealmObject {
    private String userId;
    private String userName;
    private int sympathyCount;
    private int discontentCount;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSympathyCount() {
        return sympathyCount;
    }

    public void setSympathyCount(int sympathyCount) {
        this.sympathyCount = sympathyCount;
    }

    public int getDiscontentCount() {
        return discontentCount;
    }

    public void setDiscontentCount(int discontentCount) {
        this.discontentCount = discontentCount;
    }
}
