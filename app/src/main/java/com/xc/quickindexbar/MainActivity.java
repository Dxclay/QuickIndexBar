package com.xc.quickindexbar;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.xc.quickindexbar.util.PinyinUtil;
import com.xc.quickindexbar.view.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements QuickIndexBar.OnTouchLetterListener, AdapterView.OnItemClickListener {

    private List<Friend> mLists = new ArrayList<>();
    private ListView listView;
    private QuickIndexBar indexBar;
    private TextView showWord;
    private Handler handler = new Handler();
    boolean isClick = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //加载布局和ListView适配
        initView();
        //添加点击事件
        indexBar.setOnTouchLetterListener(this);
        listView.setOnItemClickListener(this);
    }

    /**
     * 加载布局和ListView适配
     */
    private void initView() {
        indexBar = (QuickIndexBar) findViewById(R.id.indexBar);
        listView = (ListView) findViewById(R.id.list_view);
        showWord = (TextView) findViewById(R.id.show_word);
        //加载数据
        initData();
        //对集合排序
        Collections.sort(mLists);
        //添加适配器
        listView.setAdapter(new MyAdapter(this, mLists));
    }


    /**
     * 显示隐藏的listView，相当于一个Toast，但比Toast简单
     * @param letter indexBar的字母
     */
    private void showWordView(String letter) {
        showWord.setVisibility(View.VISIBLE);
        showWord.setText(letter);
        //在开始之前一处事件
        handler.removeCallbacksAndMessages(null);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                showWord.setVisibility(View.GONE);
            }
        }, 2000);
    }

    /**
     * 给List添加数据
     */
    private void initData() {
        mLists.add(new Friend("狗子"));
        mLists.add(new Friend("张三"));
        mLists.add(new Friend("阿三"));
        mLists.add(new Friend("阿四"));
        mLists.add(new Friend("蜘蛛侠"));
        mLists.add(new Friend("成龙"));
        mLists.add(new Friend("钢铁侠"));
        mLists.add(new Friend("周杰伦"));
        mLists.add(new Friend("林俊杰1"));
        mLists.add(new Friend("baby"));
        mLists.add(new Friend("王思聪"));
        mLists.add(new Friend("a林俊杰a"));
        mLists.add(new Friend("张四"));
        mLists.add(new Friend("林俊杰"));
        mLists.add(new Friend("王尼玛2"));
        mLists.add(new Friend("王尼玛"));
        mLists.add(new Friend("赵子龙"));
        mLists.add(new Friend("杨洋"));
        mLists.add(new Friend("赵子龙"));
        mLists.add(new Friend("杨树"));
        mLists.add(new Friend("李小龙"));
        mLists.add(new Friend("送人头"));
        mLists.add(new Friend("李青"));
        mLists.add(new Friend("安妮"));
        mLists.add(new Friend("O(∩_∩)O~哒"));
    }

    /**
     * 实现indexBar的点击事件
     * @param letter indexBar的字母
     */
    @Override
    public void TouchLetter(String letter) {
        //                Log.e("index","index ="+letter);
        for (int i = 0; i < mLists.size(); i++) {
            String currentLetter = mLists.get(i).getPinyin().charAt(0) + "";
//                    Log.e("tag", letter);
            if (isClick == true) {
                showWord.setTextSize(50);
            }
            if (letter.equals(currentLetter)) {
                listView.setSelection(i);
//                        Toast.makeText(getApplication(),currentLetter,Toast.LENGTH_SHORT).show();
                showWordView(letter);
                break;
            } else {
                showWordView(letter);
            }
        }
    }

    /**
     *
     * 实现listView的点击事件
     * @param parent
     * @param view
     * @param position listView的子项参数从0开始
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Friend friend = mLists.get(position);
        showWord.setTextSize(16);
        showWordView(friend.getName());
        isClick = !isClick;
    }
}
