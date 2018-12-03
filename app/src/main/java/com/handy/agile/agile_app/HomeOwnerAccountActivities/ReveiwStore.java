package com.handy.agile.agile_app.HomeOwnerAccountActivities;

public class ReveiwStore{
    private String ID;
    private String reviewerEmail;
    private String comment;
    private int score;

    public ReveiwStore(){
    }

    public ReveiwStore(String ID, String reviewerEmail, String comment, int score){
        this.ID = ID;
        this.reviewerEmail = reviewerEmail;
        this.comment = comment;
        this.score = score;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
