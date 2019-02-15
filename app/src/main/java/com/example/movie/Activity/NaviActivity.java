package com.example.movie.Activity;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.movie.Fragment.Fragment1;
import com.example.movie.Fragment.Fragment2;
import com.example.movie.Fragment.Fragment3;
import com.example.movie.Fragment.Fragment4;
import com.example.movie.Fragment.Fragment5;
import com.example.movie.Fragment.Fragment6;
import com.example.movie.Fragment.MainFragment;
import com.example.movie.R;
import com.example.movie.VO.MovieItem;
import com.example.movie.Volley.AppHelper;
import com.example.movie.Volley.MovieList;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NaviActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, FragmentCallBack {

    //공통변수
    MainFragment mainFragment;
    Toolbar toolbar;
    MovieItem movieItem;
    Bundle bundle;
    Fragment fragment1,fragment2,fragment3;
    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navi);

        //툴바
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //드로어
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        //네비게이션
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //페이저
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setOffscreenPageLimit(3);

        //번들객체
        bundle = new Bundle();

        //상세보기 클릭시 나오는 프래그먼트
        mainFragment = new MainFragment();

            AppHelper.requestQueue = Volley.newRequestQueue(getApplicationContext());
            //String url = "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json?key=430156241533f1d058c603178cc3ca0e&targetDt=20120101";
            //String url = "http://boostcourse-appapi.connect.or.kr:10000/movie/createComment";
            String url = "http://"+AppHelper.host+":"+AppHelper.port+"/movie/readMovieList";
            url += "?"+"type=1";
            //String url = "http://boostcourse-appapi.connect.or.kr:10000/movie/readCommentList";

            StringRequest request = new StringRequest(
                    Request.Method.GET,
                    url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            //println("응답->"+response);
                            Log.d("응답", response);

                            Gson gson = new Gson();
                            MovieList movieList = gson.fromJson(response,MovieList.class);
                            if(movieList != null){
                                for(int i=0; i<movieList.result.size(); i++) {
                                    movieItem = movieList.result.get(i);
                                    String id = movieItem.id;
                                    String title = movieItem.title;
                                    Log.d("title", title);
                                    bundle.putString("id", id);
                                    bundle.putString("title", title);
                               /*     if(i == 0) {
                                        fragment1.setArguments(bundle);
                                    }
                                    else if(i==1){
                                        fragment2.setArguments(bundle);
                                    }
                                    else if(i==2){
                                        fragment3.setArguments(bundle);
                                    }
                                    else{
                                        Log.d("title", title);
                                    }
                                    bundle.clear();*/
                                }
                                }
                                }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            //println("에러->"+error.getMessage());
                            Log.d("에러", error.toString());
                        }
                    }
            ) {
                //요청시 보내줄 정보
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    //params.put("type","1");
                    //params.put("limit","5");
                    //params.put("writer","ds");
                    //params.put("rating","4.5");
                    //params.put("contents","볼만함");
                    return params;
                }
            };
            request.setShouldCache(false); //요청저장 거절
            AppHelper.requestQueue.add(request);


        MoviePagerAdapter adapter = new MoviePagerAdapter(getSupportFragmentManager());


        //프래그먼트 객체
        fragment1 = new Fragment1();
        adapter.addItem(fragment1);

        final Fragment2 fragment2 = new Fragment2();
        adapter.addItem(fragment2);

        final Fragment3 fragment3 = new Fragment3();
        adapter.addItem(fragment3);
        pager.setAdapter(adapter);

        Fragment4 fragment4 = new Fragment4();
        adapter.addItem(fragment4);
        pager.setAdapter(adapter);

        Fragment5 fragment5 = new Fragment5();
        adapter.addItem(fragment5);
        pager.setAdapter(adapter);

        Fragment6 fragment6 = new Fragment6();
        adapter.addItem(fragment6);
        pager.setAdapter(adapter);
    }
    public void onCommand(String command, String data){
    }
    public void onFragmentChanged(int index){
        if(index == 1){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
            setTitle("영화 상세");
        }
        else if(index == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,mainFragment).commit();
        }
    }

    //뒤로가기 버튼
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
                super.onBackPressed();
            }
        }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        // getMenuInflater().inflate(R.menu.navi, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_movie_list) {
            // Handle the camera action
        } else if (id == R.id.nav_movie_api) {

        } else if (id == R.id.nav_movie_book) {

        } else if (id == R.id.nav_setting) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //영화페이저 어댑터
    class MoviePagerAdapter extends FragmentStatePagerAdapter {
        ArrayList<Fragment> items = new ArrayList<Fragment>();
        public MoviePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        public void addItem(Fragment item){
            items.add(item);
        }

        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        @Override
        public int getCount() {
            return items.size();
        }
    }
    }
