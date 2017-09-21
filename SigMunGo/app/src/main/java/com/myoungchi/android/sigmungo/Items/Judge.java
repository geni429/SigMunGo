package com.myoungchi.android.sigmungo.Items;

import io.realm.RealmObject;

/**
 * Created by geni on 2017. 9. 21..
 */

public class Judge extends RealmObject {
    private boolean first = true;
    private boolean login = false;

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }
}
