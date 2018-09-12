package com.example.divinkas.noticesapprlm.Model;

import android.content.Context;

import com.example.divinkas.noticesapprlm.Data.Notice;


import java.util.List;
import java.util.Objects;

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

    public void changeNotice(String name){
        realm.beginTransaction();
        List<Notice> list = realm.where(Notice.class).equalTo("notice", name).findAll();
        for (int i = 0; i< list.size(); i++){
            //Objects.requireNonNull(realm.where(Notice.class).equalTo("notice", name).findAll().get(i)).setStatusNotice(3);
            Objects.requireNonNull(list.get(i)).setStatusNotice(3);
        }
        realm.commitTransaction();
    }

    public void dellNotice(String name){
        realm.beginTransaction();
        realm.where(Notice.class).equalTo("notice", name).findAll().deleteAllFromRealm();
        realm.commitTransaction();
    }

    public void close(){
        realm.close();
    }

}
