package com.example.movie;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //변수선언
    TextView like_count_view, dislike_count_view;
    Toast toast;
    Button comment_btn, all_btn;
    ImageButton like, dislike;
    int like_count = 0;
    int dislike_count = 0;
    ListView listView;

    //어댑터 클릭이벤트 사용하기 위함
    CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 객체생성
        like = (ImageButton) findViewById(R.id.like);
        dislike = (ImageButton) findViewById(R.id.dislike);
        comment_btn = (Button) findViewById(R.id.comment_btn);
        all_btn = (Button) findViewById(R.id.all_btn);

        //텍스트뷰 객체생성
        like_count_view = (TextView) findViewById(R.id.like_count);
        dislike_count_view = (TextView) findViewById(R.id.dislike_count);

        //리스트뷰 객체생성
        listView = (ListView) findViewById(R.id.Comment);

        //어댑터 객체화
        adapter = new CommentAdapter();
        listView.setAdapter(adapter);
        adapter.addItem(new CommentItem("대승","2시간전","3점","재밌다","1",R.mipmap.ic_launcher));
        adapter.addItem(new CommentItem("대승2","3분전","4점","연기력이 좋다","2",R.mipmap.ic_launcher));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CommentItem commentItem = (CommentItem)adapter.getItem(position);
            }
        });

        //클릭이벤트
        like.setOnClickListener(this);
        dislike.setOnClickListener(this);
        comment_btn.setOnClickListener(this);
        all_btn.setOnClickListener(this);

    }

    //클릭이벤트 구현
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.like:
                if(like.isSelected()) {
                    like_count--;
                    like_count_view.setText("" + like_count);
                    like.setSelected(false);
                }
                else{
                        like_count++;
                        like_count_view.setText("" + like_count);
                        like.setSelected(true);
                    }
                break;
            case R.id.dislike:
                if(dislike.isSelected()) {
                    dislike_count--;
                    dislike_count_view.setText("" + dislike_count);
                    dislike.setSelected(false);
                }
                else{
                    dislike_count++;
                    dislike_count_view.setText("" + dislike_count);
                    dislike.setSelected(true);
                }
                break;
            case R.id.comment_btn:
                toast.makeText(getApplicationContext(), "작성하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), CommentActivity.class);
                startActivityForResult(intent, 101); //requestCode = 화면코드네임

                break;
            case R.id.all_btn:
                toast.makeText(getApplicationContext(), "모두보기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    //어댑터 클래스
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

        if(requestCode == 101){
            String id = data.getStringExtra("id");
            String star = data.getStringExtra("star");
            String comment = data.getStringExtra("comment");

            Toast.makeText(getApplicationContext(), "코멘트 화면으로부터 응답",Toast.LENGTH_SHORT).show();

            //리스트뷰에 어댑터 붙여주기
            listView.setAdapter(adapter);
            adapter.addItem(new CommentItem(id,"지금",star,comment,"2",R.drawable.user1));
        }
    }
}