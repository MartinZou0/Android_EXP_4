package com.example.martinzou.android_exp_4;

import android.content.Intent;
import android.app.AlertDialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button button1;
    private Button button2;
    private Button button3;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=(Button)findViewById(R.id.listviewbutton);
        button2=(Button)findViewById(R.id.dialogbutton);
        button3=(Button)findViewById(R.id.actionmodebutton);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        editText=(EditText)findViewById(R.id.editText);
        registerForContextMenu(editText);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu,menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater=new MenuInflater(this);
        inflater.inflate(R.menu.mainmenu,menu);
        menu.setHeaderTitle("请选择你要修改的项目");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        item.setChecked(true);
        switch (item.getItemId())
        {
            case R.id.font_10:
                item.setChecked(true);
                editText.setTextSize(10*2) ;
                break;
            case R.id.font_16:
                item.setChecked(true);
                editText.setTextSize(16*2) ;
                break;
            case R.id.font_20:
                item.setChecked(true);
                editText.setTextSize(20*2) ;
                break;
            case R.id.font_black:
                item.setChecked(true);
                editText.setTextColor(Color.BLACK);
                break;
            case R.id.font_red:
                item.setChecked(true);
                editText.setTextColor(Color.RED);
                break;
            case R.id.plain_item:
                Toast toast = Toast.makeText(MainActivity.this
                        , "您单击了普通菜单项", Toast.LENGTH_SHORT);
                toast.show();
                break;
        }
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.actionmodebutton:{
                Intent intent=new Intent(MainActivity.this,Actionmode.class);
                startActivity(intent);break;}
            case R.id.listviewbutton:{
                Intent intent=new Intent(MainActivity.this,ListVIewActivity.class);
                startActivity(intent);break;
            }
            case R.id.dialogbutton:{
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                LayoutInflater inflater=getLayoutInflater();
                View view1=inflater.inflate(R.layout.mydialog,null);
                builder.setView(view1);
                AlertDialog alertDialog=builder.create();
                alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                alertDialog.show();

            }
        }


    }
}
