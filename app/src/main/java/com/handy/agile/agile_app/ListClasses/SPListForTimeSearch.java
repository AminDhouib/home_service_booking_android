package com.handy.agile.agile_app.ListClasses;

import android.app.Activity;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.DayEntry;

import org.w3c.dom.Text;

import java.util.List;

public class SPListForTimeSearch extends ArrayAdapter<ServiceProvider> {

    private Activity context;
    private List<ServiceProvider> serviceProviders;
    private DatabaseReference databaseSP;

    public SPListForTimeSearch(Activity context, List<ServiceProvider> serviceProviders) {
        super(context, R.layout.sp_list_for_time_search, serviceProviders);
        this.context = context;
        this.serviceProviders = serviceProviders;
        databaseSP = FirebaseDatabase.getInstance().getReference("ServiceProviders");


    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.sp_list_for_time_search, null, true);

        TextView spFirstNameTextView = listViewItem.findViewById(R.id.spFirstNameTextView);
        TextView spLastNameTextView = listViewItem.findViewById(R.id.spLastNameTextView);
        TextView companyNameTextView = listViewItem.findViewById(R.id.companyNameTextView);


        ServiceProvider serviceProvider = serviceProviders.get(position);
        spFirstNameTextView.setText(serviceProvider.getName());
        spLastNameTextView.setText(serviceProvider.getLastName());
        companyNameTextView.setText(serviceProvider.getCompanyName());



        return listViewItem;
    }
}
