package com.example.xiaoyan.quickindex.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xiaoyan.quickindex.R;
import com.example.xiaoyan.quickindex.bean.PersonBean;

import java.util.ArrayList;

/**
 * Created by xiaoyan on 2016/3/4 17:52.
 */
public class MyAdapter extends BaseAdapter {
    private final ArrayList<PersonBean> mPersonBeans;
    private final Context mContext;

    public MyAdapter(ArrayList<PersonBean> personBeans, Context context) {
        mPersonBeans = personBeans;
        mContext = context;
    }


    @Override
    public int getCount() {
        return mPersonBeans.size();
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
        View view;
        if (convertView == null) {
            view = View.inflate(mContext, R.layout.item, null);
        } else {
            view = convertView;
        }

        ViewHolder holder = ViewHolder.getHolder(view);
        PersonBean person= mPersonBeans.get(position);

        //获取当前人名的拼音的首字母
        String currentLetter = person.getPinyin().charAt(0) + "";

        //当前显示的索引字母
        String indexLetter=null;

        if (position==0){
            indexLetter=currentLetter;
        }else {
            //上一个人名拼音的首字母
            String preLetter=mPersonBeans.get(position-1).getPinyin().charAt(0)+"";
            //他们不一样就更改当前显示的索引字母
            if (!TextUtils.equals(currentLetter,preLetter)){
                indexLetter=currentLetter;
            }
        }

        holder.tv_letter.setVisibility(indexLetter==null?View.GONE:View.VISIBLE);
        holder.tv_letter.setText(indexLetter);
        holder.tv_name.setText(person.getName());

        return view;
    }

    static class ViewHolder {
        public TextView tv_letter;
        public TextView tv_name;

        public static ViewHolder getHolder(View view) {
            ViewHolder holder = (ViewHolder) view.getTag();
            if (holder == null) {
                holder = new ViewHolder();
                holder.tv_letter = (TextView) view.findViewById(R.id.letter);
                holder.tv_name = (TextView) view.findViewById(R.id.name);
                view.setTag(holder);
            }

            return holder;
        }
    }

}
