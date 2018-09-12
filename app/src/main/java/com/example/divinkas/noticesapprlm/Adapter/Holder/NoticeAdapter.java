package com.example.divinkas.noticesapprlm.Adapter.Holder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.divinkas.noticesapprlm.Data.Notice;
import com.example.divinkas.noticesapprlm.Model.DbRealmHelper;
import com.example.divinkas.noticesapprlm.R;

import java.util.List;

import io.realm.RealmResults;

public class NoticeAdapter extends RecyclerView.Adapter<ListNoticeHolder> {
    private List<Notice> noticeList;
    private Context context;

    public NoticeAdapter(RealmResults<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
        noticeList.addChangeListener(notices -> notifyDataSetChanged());
    }

    @NonNull
    @Override
    public ListNoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListNoticeHolder(LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListNoticeHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.setIsRecyclable(false);

        holder.tvTime.setText(noticeList.get(position).getTimeNotice());
        holder.tvDate.setText(noticeList.get(position).getDateNotice());
        holder.tvDescr.setText(noticeList.get(position).getNotice());

        int res = R.drawable.ic_launcher_background;
        switch (noticeList.get(position).getStatusNotice()){
            case 1: res = R.drawable.infp_ico; break;
            case 2: res = R.drawable.alert_ico_2; break;
            case 3: res = R.drawable.done_ico; break;
        }
        holder.iconStatus.setImageResource(res);
        holder.containerItemNotice.setOnClickListener(v -> {
            DbRealmHelper dbRealmHelper = new DbRealmHelper(context);
            if(noticeList.get(position).getStatusNotice() != 3){
                AlertDialog.Builder dialog = new AlertDialog.Builder(context).setTitle("Добавить в выполненные?");
                dialog.setPositiveButton(R.string.yes, (dialog1, which) -> {
                    dbRealmHelper.changeNotice(noticeList.get(position).getNotice());
                    notifyDataSetChanged();
                });
                dialog.setNegativeButton(R.string.no, (dialog12, which) -> {
                });
                dialog.show();
            }
            else {
                AlertDialog.Builder dialog = new AlertDialog.Builder(context).setTitle("Удалить запись?");
                dialog.setPositiveButton(R.string.yes, (DialogInterface dialog1, int which) -> {
                    dbRealmHelper.dellNotice(noticeList.get(position).getNotice());
                    notifyDataSetChanged();
                });
                dialog.setNegativeButton(R.string.no, (dialog12, which) -> {
                });
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
