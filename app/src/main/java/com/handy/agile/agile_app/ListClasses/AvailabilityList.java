package com.handy.agile.agile_app.ListClasses;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handy.agile.agile_app.UtilityClasses.DayEntry;
import com.handy.agile.agile_app.R;

import java.util.List;

public class AvailabilityList extends ArrayAdapter<DayEntry> {

    private Activity context;
    private List<DayEntry> days;

    public AvailabilityList(Activity context, List<DayEntry> days) {
        super(context, R.layout.availability_list,days);
        this.context = context;
        this.days = days;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.availability_list,null,true);

        TextView dayTextView = listViewItem.findViewById(R.id.tvDay);
        TextView isOpenTextView  = listViewItem.findViewById(R.id.tvisOpen);
        TextView startTimeTextView  = listViewItem.findViewById(R.id.tvStart);
        TextView endTimeTextView  = listViewItem.findViewById(R.id.tvEnd);

        DayEntry day = days.get(position);
        dayTextView.setText(day.getDay());
        if (!day.isOpen()) {
            isOpenTextView.setText("Closed");
        } else {
            isOpenTextView.setText("Open");

        }
        if (day.isOpen()) {
            startTimeTextView.setText("From: "+day.getStartTime());
            endTimeTextView.setText("To: "+day.getEndTime());
        } else {
            startTimeTextView.setText("");
            endTimeTextView.setText("");
        }

        return listViewItem;
    }
}
