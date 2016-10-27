package com.example.jack.coordinatorlayouttest;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ScrollingActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout = null;
    private View mToolbar1 = null;
    private View mToolbar2 = null;

    private ImageView mZhangdan = null;
    private TextView mZhangdan_txt = null;
    private ImageView mTongxunlu = null;
    private ImageView mJiahao = null;

    private ImageView mZhangdan2 = null;
    private ImageView mShaoyishao = null;
    private ImageView mSearch = null;
    private ImageView mZhaoxiang = null;

    private RecyclerView myRecyclerView;
    private View mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);
        mView=findViewById(R.id.view);
        myRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        myRecyclerView.setAdapter(new ToolbarAdapter(this));

        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mToolbar1 = findViewById(R.id.toolbar1);
        mToolbar2 = findViewById(R.id.toolbar2);

        mZhangdan = (ImageView) findViewById(R.id.img_zhangdan);
        mZhangdan_txt = (TextView) findViewById(R.id.img_zhangdan_txt);
        mTongxunlu = (ImageView) findViewById(R.id.tongxunlu);
        mJiahao = (ImageView) findViewById(R.id.jiahao);

        mZhangdan2 = (ImageView) findViewById(R.id.img_shaomiao);
        mShaoyishao = (ImageView) findViewById(R.id.img_fukuang);
        mSearch = (ImageView) findViewById(R.id.img_search);
        mZhaoxiang = (ImageView) findViewById(R.id.img_zhaoxiang);

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                showTitle(verticalOffset, totalScrollRange);
            }
        });

        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "search", Toast.LENGTH_SHORT).show();
            }
        });
    }
    Animation animation;
    private void showTitle(int verticalOffset, int totalScrollRange) {
        int alpha = Math.abs((int) (255 - 255.0f * Math.abs(verticalOffset) / totalScrollRange*2));
        if (Math.abs(verticalOffset)<=totalScrollRange/2){
            if (animation!=null){
                animation.cancel();
            }
            setToolbar1Alpha(alpha);
            mToolbar1.setVisibility(View.VISIBLE);
            mToolbar2.setVisibility(View.GONE);
        }else {
            setToolbar2Alpha(alpha);
            if (mToolbar2.getVisibility()==View.GONE){
                mToolbar1.setVisibility(View.GONE);
                if (animation==null){
                    animation = AnimationUtils.loadAnimation(this, R.anim.title_in);
                }
                mToolbar2.startAnimation(animation);
                mToolbar2.setVisibility(View.VISIBLE);
            }else {

            }
           /* mToolbar1.setVisibility(View.GONE);
            mToolbar2.setVisibility(View.VISIBLE);*/
        }
    }
    private void showTitle2(int verticalOffset, int totalScrollRange) {
        int alpha = Math.abs((int) (255 - 255.0f * Math.abs(verticalOffset) / totalScrollRange));
        setToolbar1Alpha(alpha);
        mToolbar1.setVisibility(View.VISIBLE);
        mToolbar2.setVisibility(View.GONE);

        if (alpha==0){
            if (mToolbar2.getVisibility()==View.GONE){
                mToolbar1.setVisibility(View.GONE);
                Animation animation = AnimationUtils.loadAnimation(this, R.anim.title_in);
                mToolbar2.startAnimation(animation);
                mToolbar2.setVisibility(View.VISIBLE);
            }

        }
    }

    //设置展开时各控件的透明度
    public void setToolbar1Alpha(int alpha) {
        mZhangdan.getDrawable().setAlpha(alpha);
        mZhangdan_txt.setTextColor(Color.argb(alpha, 255, 255, 255));
        mTongxunlu.getDrawable().setAlpha(alpha);
        mJiahao.getDrawable().setAlpha(alpha);
    }

    //设置闭合时各控件的透明度
    public void setToolbar2Alpha(int alpha) {
        mZhangdan2.getDrawable().setAlpha(alpha);
        mShaoyishao.getDrawable().setAlpha(alpha);
        mSearch.getDrawable().setAlpha(alpha);
        mZhaoxiang.getDrawable().setAlpha(alpha);
    }

}
