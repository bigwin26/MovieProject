package com.example.movie.VO;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.movie.R;

public class CommentItemView extends LinearLayout {
    TextView idTextView, timeTextView, starTextView, commentTextView, likeTextView;
    ImageView customer_pic;

    public CommentItemView(Context context) {
        super(context);

        init(context);
    }

    public CommentItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context){
       LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
       inflater.inflate(R.layout.comment_layout, this , true);

       idTextView = (TextView) findViewById(R.id.comment_id_tv);
       timeTextView = (TextView) findViewById(R.id.comment_time_tv);
       starTextView = (TextView) findViewById(R.id.comment_star_tv);
       commentTextView = (TextView) findViewById(R.id.comment_tv);
       likeTextView = (TextView) findViewById(R.id.comment_like_tv);
       customer_pic = (ImageView) findViewById(R.id.customer_pic);
    }

    public void setId(String id) {
        idTextView.setText(id);
    }

    public void setTime(String time){
        timeTextView.setText(time);
    }

    public void setStar(String star){
        starTextView.setText(star);
    }

    public void setComment(String comment){
        commentTextView.setText(comment);
    }

    public void setLike(String like){
        likeTextView.setText(like);
    }

    public void setImage(int resId){
        customer_pic.setImageResource(resId);
    }
}
