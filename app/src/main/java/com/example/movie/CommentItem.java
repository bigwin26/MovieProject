package com.example.movie;

public class CommentItem {
    String id;
    String mobile;

    public CommentItem(String id, String mobile) {
        this.id = id;
        this.mobile = mobile;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Override
    public String toString() {
        return "CommentItem{" +
                "id='" + id + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }

}
