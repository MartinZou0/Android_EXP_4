package com.example.martinzou.android_exp_4;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by 10630 on 2018/5/7.
 */

public class ListVIewActivity extends AppCompatActivity {
    private ListView listview;
    private List<Map<String, Object>> listitems;
    private int img[] = {R.drawable.cat, R.drawable.dog, R.drawable.elephant, R.drawable.lion, R.drawable.monkey, R.drawable.tiger};
    private  String name[] = {"cat", "dog", "elephant","lion", "monkey", "tiger"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        listview = (ListView) findViewById(R.id.listview1);
        listitems=new ArrayList<Map<String, Object>>();
        //图片资源
        //创建一个键值对的Map集合
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("pic", img[i]);
            item.put("name", name[i]);
            listitems.add(item);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listitems, R.layout.listviewitem_listview, new String[]{"pic","name"}, new int[]{R.id.animalimage, R.id.animalname});
        listview.setAdapter(simpleAdapter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //对于参数的解释
                // adpaterview：官方解释为：The AdapterView where the click happened，也就是用户所点击的AdapterView，这个参数一般不用。
                // view：当前点击的列表项所对应的布局View对象
                // i：当前点击的列表项的位置，从0开始，也就是点击第n个，i就是n-1。
                // l：当前点击的列表项的序号，也是从0开始，一般情况下i和l是一样的。
               String name=((TextView)view.findViewById(R.id.animalname)).getText().toString();
               String showtext="你点击的是第"+(i+1)+"项，内容为"+name;
               Toast.makeText(ListVIewActivity.this, showtext, Toast.LENGTH_SHORT).show();
            }
        });


    }
}
