package com.example.xiaoyan.quickindex.bean;

import com.example.xiaoyan.quickindex.util.PinyinUtil;

/**
 * Created by xiaoyan on 2016/3/4 17:48.
 */
public class PersonBean implements Comparable<PersonBean> {
    private String name;
    private String pinyin;

    public PersonBean(String name) {
        this.name = name;
        this.pinyin = PinyinUtil.getPinyin(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }


    @Override
    public int compareTo(PersonBean another) {
        return pinyin.compareTo(another.pinyin);
    }
}
