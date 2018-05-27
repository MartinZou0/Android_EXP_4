package com.example.martinzou.android_exp_4;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 10630 on 2018/5/27.
 */

public class Actionmode extends AppCompatActivity {
    private List datalist;
    private ListView animalitemlist;
    private MyAdapter myAdapter;
    private MultiModeCallback multiModeCallback;
    private int img[] = {R.drawable.cat, R.drawable.dog, R.drawable.elephant, R.drawable.lion, R.drawable.monkey, R.drawable.tiger};
    private  String name[] = {"cat", "dog", "elephant","lion", "monkey", "tiger"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actionmode);
        animalitemlist = (ListView) findViewById(R.id.listview2);
        datalist=new ArrayList<Map<String, Object>>();
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("pic", img[i]);
            item.put("name", name[i]);
            datalist.add(item);
        }
        myAdapter=new MyAdapter(datalist,Actionmode.this);
        multiModeCallback=new MultiModeCallback();
        animalitemlist.setAdapter(myAdapter);
        animalitemlist.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        animalitemlist.setMultiChoiceModeListener(multiModeCallback);

    }

    private class MultiModeCallback implements AbsListView.MultiChoiceModeListener{
        @Override
        public void onItemCheckedStateChanged(ActionMode actionMode, int position, long id, boolean checked) {
            if(checked){
                myAdapter.addSelecteditem(position);
            }else{
                myAdapter.removeSelecteditem(position);
            }
            myAdapter.notifyDataSetChanged();
            actionMode.invalidate();
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            actionMode.getMenuInflater().inflate(R.menu.actionmode,menu);
            myAdapter.setItemmultichekable(true);
            //强制刷新
            myAdapter.notifyDataSetChanged();
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode actionMode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode actionMode, MenuItem menuItem) {
            switch(menuItem.getItemId()){
                case R.id.cancel:
                    //使其变为不可选
                    myAdapter.setItemmultichekable(false);
                    myAdapter.cancelselecteditem();
                    myAdapter.notifyDataSetChanged();
                    actionMode.finish();
                    break;
                case R.id.delete:
                    myAdapter.deleteSelecteditem();
                    myAdapter.notifyDataSetChanged();
                    actionMode.invalidate();
                    actionMode.finish();
                    break;

                default:break;
            }
            return true;
        }

        @Override
        public void onDestroyActionMode(ActionMode actionMode) {
            Toast.makeText(Actionmode.this,"设置完毕",Toast.LENGTH_SHORT).show();
            myAdapter.setItemmultichekable(false);
            myAdapter.cancelselecteditem();
            myAdapter.notifyDataSetChanged();
        }


    }
}
