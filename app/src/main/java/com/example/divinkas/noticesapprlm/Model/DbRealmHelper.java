package com.example.divinkas.noticesapprlm.Model;

import android.content.Context;

import com.example.divinkas.noticesapprlm.Data.Notice;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class DbRealmHelper {
    private Context context;
    private Realm realm;

    public DbRealmHelper(Context context) {
        realm = Realm.getDefaultInstance();
        Realm.init(context);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myNotice.realm").build();
        Realm.setDefaultConfiguration(config);
        this.context = context;
    }

    public void insertNotice(String time, String date, String text, int status){
        realm.beginTransaction();

        //Notice notice = realm.createObject(Notice.class);
        Notice notice = new Notice();
        notice.setDateNotice(time);
        notice.setTimeNotice(date);
        notice.setNotice(text);
        notice.setStatusNotice(status);

        realm.insert(notice);
        realm.commitTransaction();
    }

    public RealmResults<Notice> getAllNotice(){
        return  realm.where(Notice.class).findAll();
    }

    public void close(){
        realm.close();
    }
}
