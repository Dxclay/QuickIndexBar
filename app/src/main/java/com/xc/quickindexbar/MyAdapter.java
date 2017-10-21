package com.xc.quickindexbar;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xc.quickindexbar.util.PinyinUtil;

import java.util.List;

/**
 * Created by xxdeng on 2017/10/20.
 */

public class MyAdapter extends BaseAdapter {
    List<Friend> lists;
    private Context mContext;
    public MyAdapter(Context context,List<Friend> lists) {
        this.lists=lists;
        this.mContext=context;
    }

    @Override
    public int getCount() {
        return lists.size();
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
        if (convertView==null){
            convertView=View.inflate(mContext,R.layout.list_view_item,null);
        }
        ViewHolder holder=ViewHolder.getViewHolder(convertView);
        //设置数据
        Friend friend=lists.get(position);
        holder.name.setText(friend.getName());
//        holder.word.setText(friend.getPinyin());
        String currentWord=friend.getPinyin().charAt(0)+"";
//        Log.e("tag",currentWord);
        if (position>0){
            String lastWord=lists.get(position-1).getPinyin().charAt(0)+"";
            if (currentWord.equals(lastWord)){
                holder.word.setVisibility(View.GONE);
            }else {
                holder.word.setVisibility(View.VISIBLE);
                holder.word.setText(currentWord);
            }
        }else {
            holder.word.setVisibility(View.VISIBLE);
            holder.word.setText(currentWord);
        }
        return convertView;
    }

    /**
     * 封装方法，使得代码简洁点
     */
      static class ViewHolder{
        TextView word;
        TextView name;

        public ViewHolder(View view){
            word= (TextView) view.findViewById(R.id.index_word);
            name= (TextView) view.findViewById(R.id.name);
        }
          static ViewHolder getViewHolder(View view){
            ViewHolder holder= (ViewHolder) view.getTag();
            if (holder==null){
                holder=new ViewHolder(view);
                view.setTag(holder);
            }
            return holder;
        }
    }
}
