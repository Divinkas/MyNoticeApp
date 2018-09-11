package com.example.divinkas.noticesapprlm.Adapter.Holder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.divinkas.noticesapprlm.Data.Notice;
import com.example.divinkas.noticesapprlm.R;

import java.util.List;

import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class NoticeAdapter extends RecyclerView.Adapter<ListNoticeHolder> {
    private List<Notice> noticeList;
    private Context context;

    public NoticeAdapter(RealmResults<Notice> noticeList, Context context) {
        this.noticeList = noticeList;
        this.context = context;
        noticeList.addChangeListener(new RealmChangeListener<RealmResults<Notice>>() {
            @Override
            public void onChange(@NonNull RealmResults<Notice> notices) {
                notifyDataSetChanged();
            }
        });
    }

    @NonNull
    @Override
    public ListNoticeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListNoticeHolder(LayoutInflater.from(context).inflate(R.layout.item_notice, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ListNoticeHolder holder, int position) {
        //holder.setIsRecyclable(false);
        holder.tvTime.setText(noticeList.get(position).getTimeNotice());
        holder.tvDate.setText(noticeList.get(position).getDateNotice());
        holder.tvDescr.setText(noticeList.get(position).getNotice());

        int res = R.drawable.ic_launcher_background;
        switch (noticeList.get(position).getStatusNotice()){
            case 1:
                res = R.drawable.done;
                break;
            case 2:
                res = R.drawable.alert;
                break;
            case 3:
                res = R.drawable.info;
                break;
        }
        holder.iconStatus.setImageResource(res);
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
