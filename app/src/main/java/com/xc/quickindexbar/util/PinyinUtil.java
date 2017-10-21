package com.xc.quickindexbar.util;

import android.text.TextUtils;
import android.util.Log;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * Created by xxdeng on 2017/10/21.
 */

public class PinyinUtil {
    public static String getPinyin(String chinese) {
        if (TextUtils.isEmpty(chinese)) return null;
        char[] charArray = chinese.toCharArray();
        String pinyin = "";
        for (int i = 0; i < charArray.length; i++) {
            //判断是否为空格
            if (Character.isWhitespace(charArray[i])) continue;
            //判断是否为汉字，因为汉字占2个字节，一般是-128到127 ，其外的一般是可硬从键盘输入的字母以及符号；
            //当字母为小写字母的时候，转为大写字母；
            if (charArray[i] > 127||charArray[i] >= 97 && charArray[i] <= 122) {
                try {
                    String arr = PinyinHelper.convertToPinyinString(String.valueOf(charArray[i]), "", PinyinFormat.WITH_TONE_NUMBER).toUpperCase();
                    char[] array = arr.toCharArray();
                    if (array != null) {
                        pinyin += array[0];
                    } else {
                        //没啥操作
                    }
                } catch (PinyinException e) {
                    e.printStackTrace();
                }
            } else {
                pinyin += charArray[i];
            }

        }
        return pinyin;
    }
    //    private void showLog() throws PinyinException {
//        String str = "你好世界";
//        Log.e("tag", PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_MARK));// nǐ,hǎo,shì,jiè
//        Log.e("tag",  PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITHOUT_TONE));// ni3,hao3,shi4,jie4
//        Log.e("tag",  PinyinHelper.convertToPinyinString(str, ",", PinyinFormat.WITH_TONE_NUMBER)); // ni,hao,shi,jie
//    }
}
