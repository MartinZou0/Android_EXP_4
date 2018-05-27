package com.example.martinzou.android_exp_4;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by 10630 on 2018/5/27.
 */
public class MyAdapter extends BaseAdapter {
    private List<Map<String,Object>> datalist;
    private Context context;
    //判断是否进入可选状态
    private boolean itemmultichekable;
    //记录被选择的位置
    private List<Map<String,Object>> selectedlist=new ArrayList<Map<String,Object>>();
    private ViewHolder viewHolder;


    public MyAdapter(List<Map<String, Object>> datalist, Context context) {
        this.datalist = datalist;
        this.context = context;
    }


    @Override
    public int getCount() {
        return datalist.size();
    }

    @Override
    public Object getItem(int i) {
        return datalist.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null) {
            viewHolder=new ViewHolder();
            view= LayoutInflater.from(context).inflate(R.layout.listviewitem_actionmode,null);
            viewHolder.textView=(TextView)view.findViewById(R.id.textviewls);
            viewHolder.checkBox=(CheckBox)view.findViewById(R.id.checkbox);
            viewHolder.imageView=(ImageView)view.findViewById(R.id.imageviewls);
            view.setTag(viewHolder);
        }{
            viewHolder=(ViewHolder)view.getTag();
        }
        Map<String, Object> map = datalist.get(i);
        viewHolder.imageView.setImageResource((Integer) map.get("pic"));
        viewHolder.textView.setText((CharSequence)map.get("name"));
        //如果进入了多选模式
        if(itemmultichekable){
            //那么checkbox可见
            viewHolder.checkBox.setVisibility(View.VISIBLE);
            //可见之后，根据用户选择亲何况将其他checkbox
            if(selectedlist.contains(datalist.get(i))){
                viewHolder.checkBox.setChecked(true);
            }else{
                viewHolder.checkBox.setChecked(false);
            }
        }else{
            //则为不可见
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        return view;
    }

    public void setItemmultichekable(boolean itemmultichekable) {
        this.itemmultichekable = itemmultichekable;
    }

    //添加和删除项目至选中列表
    public void addSelecteditem(int position){
        selectedlist.add(datalist.get(position));
    }
    public void removeSelecteditem(int position){
        selectedlist.remove(datalist.get(position));
    }
    //清空选中列表
    public void cancelselecteditem(){
        selectedlist.clear();
    }
    //根据选中列表在原有item劣种进行删除
    public void deleteSelecteditem(){
        for(Map<String,Object>map:selectedlist){
            datalist.remove(map);
        }
    }

    class ViewHolder{
        public TextView textView;
        public ImageView imageView;
        public CheckBox checkBox;
    }
}

