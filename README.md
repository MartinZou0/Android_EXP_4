# Android课程设计实验4

## ListView之SimpleAdapter使用

### 1.ListView简介

ListView是Android中最常用的控件控件之一，几乎所有的应用程序都会用到它。由于手机屏幕空间比较有限，能够一次性在屏幕上显示的内容并不多，当我们的程序中有大量的数据需要展示的时候，可以借助ListView来实现。ListView允许用户通过手指上下滑动将屏幕外的数据滚动到屏幕内，同时屏幕上原有的数据则会滚动出屏幕。



### 2.SimpleAdapter适配器的使用

ListView适用于展示大量数据的，需要借助适配器进行数据适配，ListView的适配器有多种，例如ArrayAdapter，SimpleAdapter,BaseAdapter等适配器，今天来讲解SimpleAdapter的简单使用，后续博客会继续讲解其他适配器的使用

下面介绍SimpleAdapter的构造参数设置，构造函数参数如下

```java
SimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to)

/*
参数context：上下文，比如this。关联SimpleAdapter运行的视图上下文

参数data：Map列表，列表要显示的数据，这部分需要自己实现，如例子中的getData()，类型要与上面的一致，每条项目要与from中指定条目一致

参数resource：ListView单项布局文件的Id,这个布局就是你自定义的布局了，你想显示什么样子的布局都在这个布局中。这个布局中必须包括了to中定义的控件id

参数 from：一个被添加到Map上关联每一个项目列名称的列表，数组里面是列名称，也就是key值

参数 to：是一个int数组，数组里面的id是自定义布局中各个控件的id，需要与上面的from对应

*/

```



现在要实现一个例子利用SimpleAdapter，来实现ListView中的每一行内容为文字加上图片,并在点击item的时候会有Toast提醒，这个时候需要设置item的事件监听实现效果如下：

<div aligen="center">
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/1.png"/>
</div>

下面贴出主要代码

1.listviewitem_listview.xml文件，用来确定每个列表项的布局

```xml
<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <TextView
        android:id="@+id/animalname"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:textSize="20sp"
        android:layout_marginStart="10dp"
        android:layout_centerVertical="true"
        />

    <ImageView
        android:id="@+id/animalimage"
        android:layout_width="50dp"
        android:layout_height="50dp"
        android:layout_alignParentEnd="true"
        android:layout_alignParentTop="true"
        android:layout_gravity="right"
        android:layout_marginEnd="15dp" />

</RelativeLayout>
```

2.ListViewActivity.java

```java
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
        //设置点击事件监听
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //对于参数的解释
                // adpaterview：用户所点击的AdapterView，这个参数一般不用。
                // view：当前点击的列表项所对应的布局View对象
                // i：当前点击的列表项的位置，从0开始，也就是点击第n个，i就是n-1。
                // l：当前点击的列表项的序号，也是从0开始，一般情况下i和l是一样的。
          String=name((TextView)view.findViewById(R.id.animalname)).getText().toString();
          String showtext="你点击的是第"+(i+1)+"项，内容为"+name;
          Toast.makeText(ListVIewActivity.this, showtext, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
```

## 自定义dialog

### 1.AlertDialog简介

AlertDialog可以在当前的界面弹出一个对话框，这个对话框是置顶于所有的界面元素之上的，能够屏蔽掉其他控件的交互能力，因此一般AlertDialog都是用于提示一些非常重要的内容或者警告信息。要创建一个AlertDialog,就要用到AlertDialog.Builder中的create()方法。下面给出方法示例

```java
 AlertDialog.Builder builder = new AlertDialog.Builder(this);
 builder.setTitle("普通提示").setMessage("确定退出吗？").create().show();
//在现实方面也可以这么写
  AlertDialog alertDialog=builder.create();
  alertDialog.show()
```

常用的设置方法

```java
 setTitle设置标题
 setIcon设置对话框图标
 setMessage简单消息框
 setItems设置要在对话框中显示的项目列表
 setView设置自定义的对话框样式
 setSingleChoiceItems设置对话框显示一个单选框
 setMultiChoiceItems设置对话框显示一系列的复选框
 setPositiveButton设置对话框显示一个确定的按钮
 setNeutralButton设置对话框显示一个退出按钮
 setNegativeButton 设置对话框显示一个取消按钮
 create创建一个AlertDialog对话框
 show显示对话框

```

### 2.简单的自定义Dialog

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    android:id="@+id/relativelayout"
    xmlns:android="http://schemas.android.com/apk/res/android" android:layout_width="300dp"
    android:layout_height="200dp">


        <ImageView
             android:scaleType="centerCrop"
            android:src="@mipmap/ic_launcher"
           android:layout_width="match_parent"
            android:layout_height="60dp"
            />
        <EditText
            android:id="@+id/username"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:hint="用户名"/>
        <EditText
            android:id="@+id/userpwd"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:inputType="textPassword"
            android:hint="用户密码"/>
        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="horizontal">
                <Button
                    android:layout_weight="1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:text="cancle"
                    android:background="#FFFFFF"
                    />
                <Button
                    android:layout_weight="1"
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:gravity="center"
                    android:text="Sign in"
                    android:background="#FFFFFF"
                    />
        </LinearLayout>


</LinearLayout>
```

### 3.实现效果

<div align="center">
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/2.png"/>
</div>




## 使用XML定义菜单

### 1.Android Menu简介

菜单是Android应用中非常重要且常见的组成部分，主要可以分为三类：**选项菜单、上下文菜单/上下文操作模式以及弹出菜单**。它们的主要区别如下：

- **选项菜单**是一个应用的主菜单项，用于放置对应用产生全局影响的操作，如`搜索/设置`。
- **上下文菜单**是用户长按某一元素时出现的浮动菜单。它提供的操作将影响所选内容，主要应用于列表中的每一项元素（如长按列表项弹出删除对话框）。**上下文操作模式**将在屏幕顶部栏（菜单栏）显示影响所选内容的操作选项，并允许用户选择多项，一般用于对列表类型的数据进行批量操作。
- **弹出菜单**以垂直列表形式显示一系列操作选项，一般由某一控件触发，弹出菜单将显示在对应控件的上方或下方。它适用于提供与特定内容相关的大量操作。

### 2.菜单定义示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item android:title="字体大小">
        <menu>
            <group android:checkableBehavior="single" >
                <item
                    android:id="@+id/font_10"
                    android:title="小字体(10号字)"
                    />
                <item
                    android:id="@+id/font_16"
                    android:title="中字体(16号字)"/>
                <item
                    android:id="@+id/font_20"
                    android:title="大字体(20号字)"/>
            </group>
        </menu>
    </item>
    <item android:title="普通菜单项"
        android:id="@+id/plain_item">
    </item>
    <item android:title="字体颜色">
        <menu>
            <item
                android:id="@+id/font_red"
                android:title="红色"/>
            <item
                android:id="@+id/font_black"
                android:title="黑色"/>
        </menu>
    </item>
</menu>
```

要定义Menu，我们首先需要在`res`文件夹下新建`menu`文件夹，它将用于存储与Menu相关的所有XML文件。

我们可以使用`<menu>`、`<item>`、`<group>`三种XML元素定义Menu，下面简单介绍一下它们：

- `<menu>`是菜单项的容器。`<menu>`元素必须是该文件的根节点，并且能够包含一个或多个`<item>`和`<group>`元素。
- `<item>`是菜单项，用于定义`MenuItem`，可以嵌套`<menu>`元素，以便创建子菜单。
- `<group>`是`<item>`元素的不可见容器（可选）。可以使用它对菜单项进行分组，使一组菜单项共享可用性和可见性等属性。

其中，`<item>`是我们主要需要关注的元素，它的常见属性如下：

- `android:id`：菜单项`(MenuItem)`的唯一标识
- `android:icon`：菜单项的图标（可选）
- `android:title`：菜单项的标题（必选）
- `android:showAsAction`：指定菜单项的显示方式。常用的有`ifRoom、never、always、withText`，多个属性值之间可以使用`|`隔开。

### 3.实现效果
<div align="center">
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/3.png"/>
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/4.png"/>
</div>

<div align="center">
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/5.jpg"/>
</div>

## ActionMode实现ListView长按多选

### 1.实现效果
<div align="center">
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/6.png"/>
<img width="300" src="https://github.com/MartinZou0/Android_EXP_4/blob/master/shortcut/7.png"/>
<div>

### 后续的技术内容可以访问我的博客

http://blog.csdn.net/MartinZou

