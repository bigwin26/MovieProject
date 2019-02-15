package com.example.movie.VO;

public class CommentItem {
    String id;
    String time;
    String star;
    String comment;
    String like;
    int resId;

    public CommentItem(String id, String time, String star, String comment, String like, int resId) {
        this.id = id;
        this.time = time;
        this.star = star;
        this.comment = comment;
        this.like = like;
        this.resId = resId;
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

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "id='" + id + '\'' +
                ", time='" + time + '\'' +
                ", star='" + star + '\'' +
                ", comment='" + comment + '\'' +
                ", like='" + like + '\'' +
                ", resId=" + resId +
                '}';
    }
}
