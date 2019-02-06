package com.example.movie;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView like_count_view, dislike_count_view;
    Toast toast;
    Button like, dislike, comment_btn, all_btn;
    int like_count = 0;
    int dislike_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        like = (Button) findViewById(R.id.like);
        dislike = (Button) findViewById(R.id.dislike);
        comment_btn = (Button) findViewById(R.id.comment_btn);
        all_btn = (Button) findViewById(R.id.all_btn);

        like_count_view = (TextView) findViewById(R.id.like_count);
        dislike_count_view = (TextView) findViewById(R.id.dislike_count);

        ListView listView = (ListView) findViewById(R.id.Comment);

        like.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                like_count++;
                like_count_view.setText(""+like_count);
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dislike_count++;
                dislike_count_view.setText(""+dislike_count);
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toast.makeText(getApplicationContext(),"작성하기 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        comment_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toast.makeText(getApplicationContext(),"작성하기 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });
        all_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                toast.makeText(getApplicationContext(),"모두보기 버튼이 눌렸습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    class CommentAdapter extends BaseAdapter{
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return null;
        }
    }
}
