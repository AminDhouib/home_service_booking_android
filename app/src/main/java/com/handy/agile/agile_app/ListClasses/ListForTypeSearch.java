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
import com.handy.agile.agile_app.DialogBoxes.EditServiceDialog;
import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.R;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ListForTypeSearch extends ArrayAdapter<ServiceProvider> {


    private Activity context;
    private List<ServiceProvider> serviceProviders;
    
    public ListForTypeSearch(Activity context, List<ServiceProvider> serviceProviders) {
        super(context, R.layout.service_list_for_type_search, serviceProviders);
        this.context = context;
        this.serviceProviders = serviceProviders;
    }


    @NonNull
    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list_for_type_search, null, true);

        final TextView textViewCompanyNameText = listViewItem.findViewById(R.id.textViewCompanyName);
        final TextView textViewLicenseText = listViewItem.findViewById(R.id.textViewLicense);
        final TextView textViewPhoneNumberText = listViewItem.findViewById(R.id.textViewPhoneNumber);

//        listViewItem.findViewById(R.id.addServiceToProvideButton).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AddProvidedServiceDialog dialog = new AddProvidedServiceDialog();
//                FragmentActivity activity = (FragmentActivity) context;
//                Service service = services.get(position);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("Service", service);
//                dialog.setArguments(bundle);
//                dialog.show(((FragmentActivity) context).getSupportFragmentManager(), "Add ServiceProvider");
//            }
//        });


        ServiceProvider service = serviceProviders.get(position);
        textViewCompanyNameText.setText(service.getCompanyName());

        textViewLicenseText.setText(service.getLicensed());
        textViewPhoneNumberText.setText(service.getPhoneNumber());

        return listViewItem;
    }
}