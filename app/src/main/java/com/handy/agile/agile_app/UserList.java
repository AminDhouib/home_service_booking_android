package com.handy.agile.agile_app;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class UserList extends ArrayAdapter<User> {


    private Activity context;
    private List<User> users;

    public UserList(Activity context, List<User> users) {
        super(context, R.layout.user_list, users);
        this.context = context;
        this.users = users;

    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.user_list, null, true);

        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewLastName = listViewItem.findViewById(R.id.textViewLastName);
        TextView textViewEmail = listViewItem.findViewById(R.id.textViewEmail);
        TextView textViewAddress = listViewItem.findViewById(R.id.textViewAddress);
        TextView textViewPhoneNumber = listViewItem.findViewById(R.id.textViewPhoneNumber);
        TextView textViewRole = listViewItem.findViewById(R.id.textViewRole);


        User user = users.get(position);
        textViewName.setText(user.getName()+" ");
        textViewLastName.setText(user.getLastName());
        textViewEmail.setText(user.getEmail());
        textViewAddress.setText(user.getAddress());
        textViewPhoneNumber.setText(user.getPhoneNumber());
        textViewRole.setText(user.getRole());



        return listViewItem;
    }
}
