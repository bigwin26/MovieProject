package com.example.movie;

public class CommentItem {
    String id;
    String time;
    String star;
    String comment;
    String like;

    public CommentItem(String id, String time, String star, String comment, String like) {
        this.id = id;
        this.time = time;
        this.star = star;
        this.comment = comment;
        this.like = like;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLike() {
        return like;
    }

    public void setLike(String like) {
        this.like = like;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                ", id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", star='" + star + '\'' +
                ", comment='" + comment + '\'' +
                ", like='" + like + '\'' +
                '}';
    }
}
