package com.example.movie.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.movie.R;
import com.example.movie.VO.CommentItem;
import com.example.movie.VO.CommentItemView;

import java.util.ArrayList;

public class CommentListActivity extends AppCompatActivity implements View.OnClickListener {

    Button comment_btn;
    Toast toast;
    ListView listView;
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_list);

        comment_btn = (Button) findViewById(R.id.comment_btn);
        listView = (ListView) findViewById(R.id.Comment);

        adapter = new CommentAdapter();
        listView.setAdapter(adapter);
        adapter.addItem(new CommentItem("대승","2시간전","3점","재밌다","1",R.mipmap.ic_launcher));
        adapter.addItem(new CommentItem("대승2","3분전","4점","연기력이 좋다","2",R.mipmap.ic_launcher));

        comment_btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        toast.makeText(getApplicationContext(), "작성하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
        startActivityForResult(intent, 101);
    }

    class CommentAdapter extends BaseAdapter {
        ArrayList<CommentItem> items = new ArrayList<CommentItem>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(CommentItem item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //뷰 객체 재사용을 위한 변수
            CommentItemView view = null;

            //convertView를 통해 뷰는 일정갯수만 생성되고 데이터만 교체됨.(뷰는 재사용. 뷰에 표시되는 데이터만 갈아끼움)
            if(convertView == null){
                view = new CommentItemView(getApplicationContext());
            } else{
                view = (CommentItemView) convertView;
            }
            CommentItem item = items.get(position);
            view.setId(item.getId());
            view.setTime(item.getTime());
            view.setStar(item.getStar());
            view.setComment(item.getComment());
            view.setLike(item.getLike());
            view.setImage(item.getResId());

            return view;
        }
    }

    //데이터 전달받는 메소드
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //requestCode = 화면코드네임
        //Intent data = 코드네임을 갖는 화면에서 넘겨받은 값
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 101 && resultCode == RESULT_OK) {
            String id = data.getStringExtra("id");
            String star = data.getStringExtra("star");
            String comment = data.getStringExtra("comment");

            Toast.makeText(getApplicationContext(), "코멘트 화면으로부터 응답", Toast.LENGTH_SHORT).show();

            //리스트뷰에 어댑터 붙여주기
            listView.setAdapter(adapter);
            adapter.addItem(new CommentItem(id, "지금", star, comment, "2", R.drawable.user1));
        }
        else{
            Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
        }
    }
}
