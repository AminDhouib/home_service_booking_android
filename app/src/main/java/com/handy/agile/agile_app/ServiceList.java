package com.handy.agile.agile_app;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import android.support.v4.app.Fragment;

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

        final TextView textViewServiceType = listViewItem.findViewById(R.id.textViewServiceType);
        final TextView textViewHourlyRate = listViewItem.findViewById(R.id.textViewHourlyRate);

        Service service = services.get(position);
        textViewServiceType.setText(service.getType());
        textViewHourlyRate.setText(Float.toString(service.getHourlyRate()));

        //Set on click listener to the 'edit' button of each
        listViewItem.findViewById(R.id.editServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new Dialog
                EditServiceDialog dialog = new EditServiceDialog();
                Bundle bundle = new Bundle();

                //Pass values to tthe dialog
                bundle.putString("Service", (String)textViewServiceType.getText());
                bundle.putString("HourlyRate", (String)textViewHourlyRate.getText());
                dialog.setArguments(bundle);

                //Show the Dialog
                FragmentActivity activity = (FragmentActivity) context;
                dialog.show(activity.getSupportFragmentManager(), "EditService");
            }
        });

        return listViewItem;
    }
}
