package com.handy.agile.agile_app.HomeOwnerAccountActivities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.handy.agile.agile_app.DomainClasses.ServiceProvider;
import com.handy.agile.agile_app.DomainClasses.User;
import com.handy.agile.agile_app.R;

import java.util.*;
import java.util.List;
import android.widget.RatingBar;

public class RaitingsAdapter extends android.widget.ArrayAdapter<ReveiwStore> {
    public RaitingsAdapter(Context context, List<ReveiwStore> reveiws) {
        super(context, 0, reveiws);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Get the data item for this position
        ReveiwStore reveiw = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.rating_list_item, parent, false);
        }
        // Lookup view for data population
        TextView txtName = (TextView) convertView.findViewById(R.id.txtNameReveiw);
        TextView txtComment = (TextView) convertView.findViewById(R.id.txtCommentReview);
        TextView txtReveiwer = (TextView) convertView.findViewById(R.id.txtReviwerReview);
        RatingBar rbRating = convertView.findViewById(R.id.raitingReveiwBar);
        // Populate the data into the template view using the data object


        txtName.setText(reveiw.getID());
        txtComment.setText(reveiw.getComment());
        txtReveiwer.setText("- " + reveiw.getReviewerEmail());
        rbRating.setRating(reveiw.getScore());
        // Return the completed view to render on screen
        return convertView;
    }
}