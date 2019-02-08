package com.example.movie;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class CommentActivity extends AppCompatActivity implements View.OnClickListener {

    Button save_btn,cancel_btn;
    RatingBar comment_ratingBar;
    TextView ratingBar_tv;
    EditText comment_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        //객체생성
        comment_ratingBar = (RatingBar) findViewById(R.id.comment_ratingBar);
        ratingBar_tv = (TextView) findViewById(R.id.ratingBar_tv);
        comment_et = (EditText) findViewById(R.id.comment_et);

        save_btn = (Button) findViewById(R.id.save_btn);
        cancel_btn = (Button) findViewById(R.id.cancel_btn);

        //클릭이벤트
        save_btn.setOnClickListener(this);
        cancel_btn.setOnClickListener(this);
        comment_ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                ratingBar_tv.setText(rating+"점");
            }
        });

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.save_btn:
                intent.putExtra("id", "대승승");
                intent.putExtra("comment", comment_et.getText().toString());
                Log.i("comment값 =", comment_et.getText().toString());
                intent.putExtra("star", ratingBar_tv.getText().toString());
                Log.i("rating값=",ratingBar_tv.getText().toString());

                setResult(Activity.RESULT_OK, intent);

            finish(); //코멘트 액티비티 화면 종료
                break;
            case R.id.cancel_btn:
                setResult(Activity.RESULT_CANCELED,intent);
                finish();
        }
    }
}
