package com.myoungchi.android.sigmungo.Items;

/**
 * Created by geni on 2017. 8. 3..
 */

public class UserInformation {
    private String userName;
    private String userId;
    private String myWritingCount;
    private String mySympathyCount;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMyWritingCount() {
        return myWritingCount;
    }

    public void setMyWritingCount(String myWritingCount) {
        this.myWritingCount = myWritingCount;
    }

    public String getMySympathyCount() {
        return mySympathyCount;
    }

    public void setMySympathyCount(String mySympathyCount) {
        this.mySympathyCount = mySympathyCount;
    }
}
