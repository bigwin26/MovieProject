package com.example.movie.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.movie.Activity.FragmentCallBack;
import com.example.movie.Activity.NaviActivity;
import com.example.movie.R;

public class Fragment2 extends Fragment {
    FragmentCallBack callBack;
    TextView movie_title;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if(context instanceof FragmentCallBack){
            callBack = (FragmentCallBack) context;
        } else{

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootview = (ViewGroup) inflater.inflate(R.layout.movie_fragment2, container, false);
        Button detail_btn = (Button) rootview.findViewById(R.id.detail_btn);
        movie_title = (TextView) rootview.findViewById(R.id.movie_title2);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            String id = bundle.getString("id");
            String title = bundle.getString("title");
            movie_title.setText(id+"."+"\t"+title);
        }
        detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(callBack != null) {
                    NaviActivity activity = (NaviActivity) getActivity();
                    activity.onFragmentChanged(2);
                    //callBack.onCommand("군도", "문도");
                }
            }
        });
        return rootview;
    }
}