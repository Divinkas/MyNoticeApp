package com.example.divinkas.noticesapprlm;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.divinkas.noticesapprlm.Adapter.Holder.NoticeAdapter;
import com.example.divinkas.noticesapprlm.Model.DbRealmHelper;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etDate, etTime, etDescription;
    Button btnAdd;
    Spinner spTypeNotice;
    RecyclerView rvList;


    DbRealmHelper dbRealmHelper;
    DatePickerDialog.OnDateSetListener dateSetListener;
    TimePickerDialog.OnTimeSetListener timeSetListener;
    String date, time;

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

        String[] data = new String[]{"Инфо", "Важное"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTypeNotice.setAdapter(adapter);

        dateSetListener = (view, year, month, dayOfMonth) -> {
            date = dayOfMonth + "/" + month + "/" + year;
            etDate.setText(date);
        };

        timeSetListener = (view, hourOfDay, minute) -> {
            time = hourOfDay + ":" + minute;
            etTime.setText(time);
        };
    }

    private void findElem() {
        spTypeNotice = findViewById(R.id.spTypeNotice);
        etDate = findViewById(R.id.etDate);
        etTime = findViewById(R.id.etTime);
        etDescription = findViewById(R.id.etQuestion);
        btnAdd = findViewById(R.id.btnAddNotice);
        rvList = findViewById(R.id.rvListNotices);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddNotice:
                dbRealmHelper.insertNotice(
                        etTime.getText().toString(),
                        etDate.getText().toString(),
                        etDescription.getText().toString(),
                        spTypeNotice.getSelectedItemPosition()+1);
                rvList.getAdapter().notifyDataSetChanged();
                break;
            case R.id.etDate:
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, dateSetListener, 2018, 1, 1);
                datePickerDialog.show();
                break;
            case R.id.etTime:
                TimePickerDialog timePickerDialog = new TimePickerDialog(this, timeSetListener, 20, 20, true);
                timePickerDialog.show();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dbRealmHelper.close();
    }
}
