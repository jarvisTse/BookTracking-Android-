package com.example.booktracking;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.util.List;

public class RecordAdapter extends ArrayAdapter<Record> {
    public RecordAdapter(Context context, int resource, List<Record> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Record record = getItem(position);
        @SuppressLint("ViewHolder") View oneRecordView = LayoutInflater.from(getContext()).inflate(R.layout.record_item, parent, false);

        TextView tv_status = (TextView) oneRecordView.findViewById(R.id.tv_status);
        TextView tv_title = (TextView) oneRecordView.findViewById(R.id.tv_title);
        TextView tv_borrow_at = (TextView) oneRecordView.findViewById(R.id.tv_borrow_at);
        TextView tv_deadline_at = (TextView) oneRecordView.findViewById(R.id.tv_deadline_at);
        TextView tv_return_at = (TextView) oneRecordView.findViewById(R.id.tv_return_at);
        TextView tv_renewal_num = (TextView) oneRecordView.findViewById(R.id.tv_renewal_num);

        tv_title.setText(record.getTitle());
        String returned = "Returned";
        if (record.getReturn_at() != null) {
            tv_status.setText("Returned");
            tv_status.setTextColor(Color.parseColor("#27AE60"));
            String return_at = "Return At: " + record.getReturn_at();
            tv_return_at.setText(return_at);
        } else {
            String status = "Deadline: " + record.getDeadline_at();
            tv_status.setTextColor(Color.parseColor("#E74C3C"));
            tv_status.setText(status);
            returned = "Return At: N/A";
            tv_return_at.setText(returned);
        }
        String borrow_at = "Borrow At: " + record.getBorrow_at();
        String deadline_at = "Deadline At: " + record.getDeadline_at();
        String renewal_num = "Renewal Count: " + record.getRenewal_num();
        tv_borrow_at.setText(borrow_at);
        tv_deadline_at.setText(deadline_at);
        tv_renewal_num.setText(renewal_num);

        return oneRecordView;
    }
}
