package com.handy.agile.agile_app.ListClasses;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handy.agile.agile_app.DialogBoxes.AddProvidedServiceDialog;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.R;

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
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list_for_adding, null, true);

        final TextView textViewServiceType = listViewItem.findViewById(R.id.textViewServiceType);
        final TextView textViewHourlyRate = listViewItem.findViewById(R.id.textViewHourlyRate);
        listViewItem.findViewById(R.id.addServiceToProvideButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    AddProvidedServiceDialog dialog = new AddProvidedServiceDialog();
                    FragmentActivity activity = (FragmentActivity) context;
                    Service service = services.get(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("Service", service);
                    dialog.setArguments(bundle);
                    dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "Add ServiceProvider");
            }
        });


        Service service = services.get(position);
        textViewServiceType.setText(service.getType());

        //Changes here to format AS a currency
        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(service.getHourlyRate());


        textViewHourlyRate.setText(currencyText);

       //Set on click listener to the 'add' button of each


        return listViewItem;
    }
}
