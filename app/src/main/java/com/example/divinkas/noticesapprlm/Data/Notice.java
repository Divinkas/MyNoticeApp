package com.example.divinkas.noticesapprlm.Data;

import io.realm.RealmObject;

public class Notice extends RealmObject {

    private String notice;
    private String dateNotice;
    private String timeNotice;
    private int statusNotice;

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public int getStatusNotice() {
        return statusNotice;
    }

    public void setStatusNotice(int statusNotice) {
        this.statusNotice = statusNotice;
    }

    public String getDateNotice() {
        return dateNotice;
    }

    public void setDateNotice(String dateNotice) {
        this.dateNotice = dateNotice;
    }

    public String getTimeNotice() {
        return timeNotice;
    }

    public void setTimeNotice(String timeNotice) {
        this.timeNotice = timeNotice;
    }
}
