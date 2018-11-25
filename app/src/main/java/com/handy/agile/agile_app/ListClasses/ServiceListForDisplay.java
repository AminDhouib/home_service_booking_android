package com.handy.agile.agile_app.ListClasses;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.handy.agile.agile_app.DomainClasses.Service;
import com.handy.agile.agile_app.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ServiceListForDisplay extends ArrayAdapter<Service> {

    private Activity context;
    private List<Service> services;

    public ServiceListForDisplay(Activity context, List<Service> services) {
        super(context, R.layout.service_list_for_display, services);

        this.context = context;
        this.services = services;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.service_list_for_display, null, true);

        final TextView serviceTypeTextView = listViewItem.findViewById(R.id.serviceTypeTextView);
        final TextView serviceRateTextView = listViewItem.findViewById(R.id.serviceRateTextView);

        Service service = services.get(position);
        serviceTypeTextView.setText(service.getType());

        //Change here to format AS a currency
        Locale locale = Locale.US;
        NumberFormat fmt = NumberFormat.getCurrencyInstance(locale);
        String currencyText = fmt.format(service.getHourlyRate());

        serviceRateTextView.setText(currencyText);


        return listViewItem;
    }
}
