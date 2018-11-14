package com.handy.agile.agile_app.ServiceProvider;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.Service.Service;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ServiceListForAdding extends ArrayAdapter<Service> {


    private Activity context;
    private List<Service> services;

    public ServiceListForAdding(Activity context, List<Service> services) {
        super(context, R.layout.service_list_for_adding, services);
        this.context = context;
        this.services = services;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list_for_adding, null, true);

        final TextView textViewServiceType = listViewItem.findViewById(R.id.textViewServiceType);
        final TextView textViewHourlyRate = listViewItem.findViewById(R.id.textViewHourlyRate);

        Service service = services.get(position);
        textViewServiceType.setText(service.getType());

        //Changes here to format AS a currency
        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(service.getHourlyRate());


        textViewHourlyRate.setText(currencyText);

        //Set on click listener to the 'add' button of each
        listViewItem.findViewById(R.id.addServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                //Add code for adding



            }
        });

        return listViewItem;
    }
}
