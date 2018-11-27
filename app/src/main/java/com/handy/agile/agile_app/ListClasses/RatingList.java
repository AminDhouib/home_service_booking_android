package com.handy.agile.agile_app.ListClasses;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.handy.agile.agile_app.R;
import com.handy.agile.agile_app.UtilityClasses.Rating;

import java.util.List;

public class RatingList extends ArrayAdapter<Rating> {

    private Activity context;
    private List<Rating> ratings;

    public RatingList(Activity context, List<Rating> ratings) {
        super(context, R.layout.rating_list, ratings);
        this.context = context;
        this.ratings = ratings;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.rating_list, null,true);

        TextView userEmailTextView = listViewItem.findViewById(R.id.userEmailTextView);
        RatingBar ratingBar = listViewItem.findViewById(R.id.ratingBar);
        TextView commentTextView = listViewItem.findViewById(R.id.commentTextView);

        Rating rating = ratings.get(position);
        userEmailTextView.setText(rating.getReviewerEmail());
        ratingBar.setRating(rating.getScore());
        commentTextView.setText(rating.getComment());

        return listViewItem;
    }

}
