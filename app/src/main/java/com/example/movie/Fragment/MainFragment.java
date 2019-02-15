package com.example.movie.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.movie.Activity.CommentActivity;
import com.example.movie.Activity.CommentListActivity;
import com.example.movie.R;
import com.example.movie.VO.CommentItem;
import com.example.movie.VO.CommentItemView;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class MainFragment extends Fragment implements View.OnClickListener{
    //변수선언
    TextView like_count_view, dislike_count_view, textView;
    Toast toast;
    Button comment_btn, all_btn;
    ImageButton like, dislike;
    int like_count = 0;
    int dislike_count = 0;
    ListView listView;
    ImageView imgView;

    //어댑터 클릭이벤트 사용하기 위함
    MainFragment.CommentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.activity_main, container, false);

        //버튼 객체생성
        like = (ImageButton) rootview.findViewById(R.id.like);
        dislike = (ImageButton) rootview.findViewById(R.id.dislike);
        comment_btn = (Button) rootview.findViewById(R.id.comment_btn);
        all_btn = (Button) rootview.findViewById(R.id.all_btn);

        //텍스트뷰 객체생성
        like_count_view = (TextView) rootview.findViewById(R.id.like_count);
        dislike_count_view = (TextView) rootview.findViewById(R.id.dislike_count);
        textView = (TextView) rootview.findViewById(R.id.textView);
        imgView = (ImageView) rootview.findViewById(R.id.imageView);

        //리스트뷰 객체생성
        listView = (ListView) rootview.findViewById(R.id.Comment);

        //어댑터 객체화
        adapter = new MainFragment.CommentAdapter();
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

        return rootview;
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
                toast.makeText(getContext(), "작성하기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), CommentActivity.class);
                startActivityForResult(intent, 101); //requestCode = 화면코드네임

                break;
            case R.id.all_btn:
                toast.makeText(getContext(), "모두보기 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(getContext(), CommentListActivity.class);
                startActivityForResult(intent2,102);
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
                view = new CommentItemView(getContext());
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
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //requestCode = 화면코드네임
        //Intent data = 코드네임을 갖는 화면에서 넘겨받은 값
        super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == 101 && resultCode == RESULT_OK) {

                String id = data.getStringExtra("id");
                String star = data.getStringExtra("star");
                String comment = data.getStringExtra("comment");

                Toast.makeText(getContext(), "코멘트 화면으로부터 응답", Toast.LENGTH_SHORT).show();

                //리스트뷰에 어댑터 붙여주기
                listView.setAdapter(adapter);
                adapter.addItem(new CommentItem(id, "지금", star, comment, "2", R.drawable.user1));
            } else {
                Toast.makeText(getContext(), "취소", Toast.LENGTH_SHORT).show();
            }
    }
    public void setTextView(String text){
        textView.setText(text);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int curId = item.getItemId();
        Intent intent = new Intent(getApplicationContext(), NaviActivity.class);
        startActivityForResult(intent, 103); //requestCode = 화면코드네임
        return super.onOptionsItemSelected(item);
    }*/

}
