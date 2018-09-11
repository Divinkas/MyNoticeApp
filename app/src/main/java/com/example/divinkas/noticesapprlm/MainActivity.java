package com.example.divinkas.noticesapprlm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.divinkas.noticesapprlm.Adapter.Holder.NoticeAdapter;
import com.example.divinkas.noticesapprlm.Model.DbRealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etDate, etTime, etDescription;
    Button btnAdd;
    RecyclerView rvList;

    DbRealmHelper dbRealmHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        findElem();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder().name("myNotice.realm").build();
        Realm.setDefaultConfiguration(config);

        dbRealmHelper = new DbRealmHelper(this);

        btnAdd.setOnClickListener(this);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new NoticeAdapter(dbRealmHelper.getAllNotice(), this));
    }

    private void findElem() {
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etDescription = findViewById(R.id.etQuestion);
        btnAdd = findViewById(R.id.btnAddNotice);
        rvList = findViewById(R.id.rvListNotices);
    }

    @Override
    public void onClick(View v) {
        dbRealmHelper.insertNotice(
                etTime.getText().toString(),
                etDate.getText().toString(),
                etDescription.getText().toString(),
                2);

        rvList.getAdapter().notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbRealmHelper.close();
    }
}
