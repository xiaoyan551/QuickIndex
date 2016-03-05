package com.example.xiaoyan.quickindex.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.xiaoyan.quickindex.R;
import com.example.xiaoyan.quickindex.adapter.MyAdapter;
import com.example.xiaoyan.quickindex.bean.PersonBean;
import com.example.xiaoyan.quickindex.view.QuickIndexBar;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    public static final String[] NAMES = new String[]{"宋江","卢俊义", "吴用", "公孙胜", "关胜", "林冲", "秦明", "呼延灼", "花荣", "柴进", "李应", "朱仝", "鲁智深", "武松", "董平", "张清", "杨志", "徐宁", "索超", "戴宗", "刘唐", "李逵", "史进", "穆弘", "雷横", "李俊", "阮小二", "张横", "阮小五", " 张顺", "阮小七", "杨雄", "石秀", "解珍", " 解宝", "燕青", "朱武", "黄信", "孙立", "宣赞", "郝思文", "韩滔", "彭玘", "单廷珪", "魏定国", "萧让", "裴宣", "欧鹏", "邓飞", " 燕顺", "杨林", "凌振", "蒋敬", "吕方", "郭 盛", "安道全", "皇甫端", "王英", "扈三娘", "鲍旭", "樊瑞", "孔明", "孔亮", "项充", "李衮", "金大坚", "马麟", "童威", "童猛", "孟康", "侯健", "陈达", "杨春", "郑天寿", "陶宗旺", "宋清", "乐和", "龚旺", "丁得孙", "穆春", "曹正", "宋万", "杜迁", "薛永", "施恩", "周通", "李忠", "杜兴", "汤隆", "邹渊", "邹润", "朱富", "朱贵", "蔡福", "蔡庆", "李立", "李云", "焦挺", "石勇", "孙新", "顾大嫂", "张青", "孙二娘", " 王定六", "郁保四", "白胜", "时迁", "段景柱"};
    ArrayList<PersonBean> persons = new ArrayList<PersonBean>();

    private ListView mListView;
    private QuickIndexBar mQuickIndexBar;
    private TextView mTv_index_center;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
        initEvent();

    }

    private void initEvent() {

        mQuickIndexBar.setOnLetterChangeListener(new QuickIndexBar.OnLetterChangeListener() {
            @Override
            public void onLetterChange(String letter) {
                //响应点击字母对应的回调
                //在中间显示
                showLetter(letter);
                //从集合中查找第一个字母是letter的条目，对其索引进行选中
                for (int i = 0; i < persons.size(); i++) {
                    PersonBean person = persons.get(i);
                    String s = person.getPinyin().charAt(0) + "";
                    if (TextUtils.equals(s, letter)) {
                        //设置listView选中响应的位置
                        mListView.setSelection(i);
                        break;
                    }
                }
            }
        });
    }

    private void initView() {
        mTv_index_center = (TextView) findViewById(R.id.tv_index_center);
        mListView = (ListView) findViewById(R.id.list_item);
        mListView.setAdapter(new MyAdapter(persons, this));

        mQuickIndexBar = (QuickIndexBar) findViewById(R.id.quickIndex);


    }

    private void initData() {

        //将数组中的名字转为集合
        for (int i = 0; i < NAMES.length; i++) {
            persons.add(new PersonBean(NAMES[i]));
        }
        //排序
        Collections.sort(persons);

    }


    private Handler mHandler = new Handler();

    /**
     * 显示字母提示
     *
     * @param letter
     */
    protected void showLetter(String letter) {
        mTv_index_center.setVisibility(View.VISIBLE);
        mTv_index_center.setText(letter);

        // 取消掉刚刚所有的演示操作
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 隐藏
                mTv_index_center.setVisibility(View.GONE);
            }
        }, 1000);

    }


}
