package com.example.lenovo.firevideo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.lenovo.firevideo.adapter.MyPagerAdapter;
import com.example.lenovo.firevideo.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by yinyin on 2018/2/25 .
 */

//首页界面
public class MainActivity extends AppCompatActivity implements View.OnClickListener,
        ViewPager.OnPageChangeListener {

    private ViewPager vpager_four;
    private ImageView img_cursor;
    private TextView tv_one;
    private TextView tv_two;
    private TextView tv_three;
    private ArrayList<View> listViews;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离
    public SearchView view_search;
    public EditText edt_search;

    private RadioButton btn_group,btn_home,btn_add,btn_mail,btn_mine;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
        // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        btn_home=(RadioButton)findViewById(R.id.btn_home);
        btn_group=(RadioButton)findViewById(R.id.btn_group);
        btn_mail=(RadioButton)findViewById(R.id.btn_mail);
        btn_mine=(RadioButton)findViewById(R.id.btn_mine);
        btn_add=(RadioButton)findViewById(R.id.btn_add);
//        view_search = (SearchView)findViewById(R.id.view_search);
        edt_search = (EditText)findViewById(R.id.edt_search);
//        btn_home.setBackgroundResource(R.mipmap.homepage_fill);
        try {
            initViews();
        } catch (IOException e) {
            e.printStackTrace();
        }
        jump();
    }

    public void jump(){
        btn_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,GroupActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MailActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_mine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MineActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,LiveAdVideoActivity.class);
                startActivity(intent);
                finish();
            }
        });
        edt_search.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    // 先隐藏键盘
//                    ((InputMethodManager) edt_search.getContext()
//                            .getSystemService(Context.INPUT_METHOD_SERVICE))
//                            .hideSoftInputFromWindow(MainActivity.this
//                                            .getCurrentFocus().getWindowToken(),
//                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    ((InputMethodManager)edt_search.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    MainActivity.this
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);
                    String keyWord =edt_search.getText().toString().trim();
                    // 搜索，进行自己要的操作...
                    Intent intent = new Intent(MainActivity.this,SearchUserActivity.class);
                    intent.putExtra("KeyWord",keyWord);
                    startActivity(intent);
                    finish();
                    return true;
                }
                return false;
            }
        });

    }

    private void initViews() throws IOException {
        vpager_four = (ViewPager) findViewById(R.id.vpager_four);
        tv_one = (TextView) findViewById(R.id.tv_one);
        tv_two = (TextView) findViewById(R.id.tv_two);
        img_cursor = (ImageView) findViewById(R.id.img_cursor);

        //下划线动画的相关设置：
        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.line).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度

        offset = (screenW / 2 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        img_cursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = offset * 2 + bmpWidth;// 移动一页的偏移量,比如1->2,或者2->3


        //往ViewPager填充View，同时设置点击事件与页面切换事件
        listViews = new ArrayList<View>();
        LayoutInflater mInflater = getLayoutInflater();
        //listViews.add(mInflater.inflate(R.layout.view_one, null, false));
        //listViews.add(mInflater.inflate(R.layout.view_two, null, false));
        //listViews.add(mInflater.inflate(R.layout.view_three, null, false));
        listViews.add(new ViewOne(this).getView());
        listViews.add(new ViewTwo(this).getView());
//        listViews.add(new ViewThree(this).getView());
        vpager_four.setAdapter(new MyPagerAdapter(listViews));
        vpager_four.setCurrentItem(0);          //设置ViewPager当前页，从0开始算
        tv_one.setOnClickListener(this);
        tv_two.setOnClickListener(this);
//        tv_three.setOnClickListener(this);
        vpager_four.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_one:
                vpager_four.setCurrentItem(0);
                break;
            case R.id.tv_two:
                vpager_four.setCurrentItem(1);
                break;
        }
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
//        AnimationSet animationSet = new AnimationSet(true);
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                }
                break;
        }
        currIndex = index;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
//        animationSet.addAnimation(animation);
        img_cursor.startAnimation(animation);//开始动画
//        img_cursor.startAnimation(animationSet);
        Log.i("得到动画设置","");
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }
}





