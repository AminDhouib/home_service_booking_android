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

import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.DialogBoxes.EditServiceDialog_for_sp;
import com.handy.agile.agile_app.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ServiceListForDeleting extends ArrayAdapter<Service>{

    private Activity context;
    private List<Service> services;


    public ServiceListForDeleting(Activity context, List<Service> services) {
        super(context, R.layout.service_list_for_sp, services);

        this.context = context;
        this.services = services;
    }



    @NonNull
    @Override
    public View getView(int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list_for_sp, null, true);

        final TextView textViewServiceType = listViewItem.findViewById(R.id.textViewServiceType);
        final TextView textViewHourlyRate = listViewItem.findViewById(R.id.textViewHourlyRate);

        Service service = services.get(position);
        textViewServiceType.setText(service.getType());

        //Changes here to format AS a currency
        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(service.getHourlyRate());


        textViewHourlyRate.setText(currencyText);

        //Set on click listener to the 'Delete' button of each
        listViewItem.findViewById(R.id.deleteServiceBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Create new Dialog
                EditServiceDialog_for_sp dialog = new EditServiceDialog_for_sp();
                Bundle bundle = new Bundle();

                //Pass values to the dialog
                bundle.putString("Service", (String)textViewServiceType.getText());

                //removing text elements from numeric string so it can be easily edited.
                String tempHourlyRate = (String)textViewHourlyRate.getText();
                tempHourlyRate = tempHourlyRate.replace("$","").replace(",", "");
                bundle.putString("HourlyRate", tempHourlyRate);
                dialog.setArguments(bundle);

                //Show the Dialog
                FragmentActivity activity = (FragmentActivity) context;
                dialog.show(activity.getSupportFragmentManager(), "EditService");


            }
        });

        return listViewItem;
    }
}
