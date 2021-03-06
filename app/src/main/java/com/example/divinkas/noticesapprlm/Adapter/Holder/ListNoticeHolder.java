package com.example.divinkas.noticesapprlm.Adapter.Holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.divinkas.noticesapprlm.R;

public class ListNoticeHolder extends ViewHolder {
    ImageView iconStatus;
    TextView tvDate, tvTime, tvDescr;
    LinearLayout containerItemNotice;

    public ListNoticeHolder(View itemView) {
        super(itemView);
        containerItemNotice = itemView.findViewById(R.id.containerItemNotice);
        iconStatus = itemView.findViewById(R.id.iconStatus);
        tvDate = itemView.findViewById(R.id.tvDate);
        tvDescr = itemView.findViewById(R.id.tvDescription);
        tvTime = itemView.findViewById(R.id.tvTime);
    }
}
