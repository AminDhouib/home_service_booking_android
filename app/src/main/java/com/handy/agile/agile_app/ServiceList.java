package com.handy.agile.agile_app;


import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> services;

    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.service_list, services);
        this.context = context;
        this.services = services;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list, null, true);

        TextView textViewServiceType = listViewItem.findViewById(R.id.textViewServiceType);
        TextView textViewHourlyRate = listViewItem.findViewById(R.id.textViewHourlyRate);

        Service service = services.get(position);
        textViewServiceType.setText(service.getType());
        textViewHourlyRate.setText(Float.toString(service.getHourlyRate()));

        return listViewItem;
    }
}
