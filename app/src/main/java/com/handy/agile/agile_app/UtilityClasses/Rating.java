package com.handy.agile.agile_app.UtilityClasses;

import com.handy.agile.agile_app.DomainClasses.HomeOwner;
import com.handy.agile.agile_app.DomainClasses.User;

public class Rating {

    private float score;
    private String comment;
    private String reviewerEmail;

    public Rating(String reviewerEmail, float score, String comment) {
        this.score = score;
        this.comment = comment;
        this.reviewerEmail = reviewerEmail;
    }

    public Rating() {

    }

    public float getScore() { return score; }
    public void setScore(float score) { this.score = score; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getReviewerEmail() { return reviewerEmail; }
    public void setReviewerEmail(String reviewerEmail) { this.reviewerEmail = reviewerEmail; }
}
