package com.myoungchi.android.sigmungo.Items;

import io.realm.RealmObject;

/**
 * Created by geni on 2017. 9. 21..
 */

public class UserData extends RealmObject {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
