package com.myoungchi.android.sigmungo.Items;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by geni on 2017. 8. 4..
 */

public class ValueObject extends RealmObject {
    private String id;
    private boolean first;
    private boolean login;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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
