package com.xc.quickindexbar;

import android.support.annotation.NonNull;

import com.xc.quickindexbar.util.PinyinUtil;

import java.util.Comparator;

/**
 * Created by xxdeng on 2017/10/20.
 */

public class Friend implements Comparable<Friend> {
    private String name;

    private String pinyin;

    public Friend(String name) {
        this.name = name;
        //优化内存，因为PinyinUtil类的方法在加载的术后会被频繁调用，所以先加载好PinyinUtil方法

        setPinyin(PinyinUtil.getPinyin(name));
    }
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull Friend o) {
        return getPinyin().compareTo(o.getPinyin());
    }
}
