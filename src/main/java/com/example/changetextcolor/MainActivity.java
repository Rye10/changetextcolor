package com.example.changetextcolor;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private List list=new ArrayList<>();
    private String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        for (int i=0;i<30;i++){
            list.add("测试文字"+i);
        }
        value="测试文字9";
        final Myadapter adapter=new Myadapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setCurrentItem(position);                                                        //将adapter的currentItem和click的position绑定
                adapter.setClick(true);
                adapter.notifyDataSetChanged();
            }
        });
    }
    class Myadapter extends BaseAdapter{                                                               //自定义适配器
        private int mCurrentItem=0;
        private boolean isClick=false;                                                           //约束条件，一开始isClick为false，下边的if语句执行else里边的，点击后Click为true，点击变红，没有这个约束则第0项会是红色
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {                                //getView()方法中进行判断，如果convertView为null，LayoutInflater加载布局，不为null直接对convertView进行重用，提升运行效率
            ViewHolder viewHolder;
            if (convertView==null){
                convertView=LayoutInflater.from(MainActivity.this).inflate(R.layout.list,null);          //将自己的listview的item填充进去
                viewHolder=new ViewHolder();
                viewHolder.textView=convertView.findViewById(R.id.text);
                viewHolder.imageView=convertView.findViewById(R.id.image);                                      //控件实例放入viewHolder中
                convertView.setTag(viewHolder);                                                                   //将viewHolder存储在convertView中
            }else {
                viewHolder= (ViewHolder) convertView.getTag();                                                   //重新获取viewHolder
            }

            viewHolder.textView.setText((String)list.get(position));

//            if (list.get(position).equals(value)){                                                           java代码从上往下执行，这段代码是如果value值和list中的一项相同则变色
//                textView.setText((String)list.get(position));                                                 编译完后没有变色因为执行完这段，继续往下执行了下边的if，else语句，最后出来的是黑色的，点击可以变色，两个语句不能同时共存
//                textView.setTextColor(Color.RED);                                                             如果放在下边的if，else语句后边，编译出来则是value值和list中的一项相同为红色，点击后只改变了position，最后一直执行else语句，不相同，一直为黑色
//                imageView.setVisibility(View.VISIBLE);
//            }else {
//                textView.setText((String)list.get(position));
//                textView.setTextColor(Color.BLACK);
//                imageView.setVisibility(View.GONE);
//            }
            if (mCurrentItem==position&&isClick){
                viewHolder.textView.setTextColor(Color.RED);
                viewHolder.imageView.setVisibility(View.VISIBLE);
            }else {
                viewHolder.textView.setTextColor(Color.BLACK);
                viewHolder.imageView.setVisibility(View.GONE);
            }
            if (list.get(position).equals(value)&&!isClick){                                               //解决方法：加多一个约束isClick取非，一开始isClick为false,!isClick为真，编译出来相同项红色，点击后isClick为true，!isClick为false，就不执行该语句
                viewHolder.textView.setTextColor(Color.RED);                                                //不加！isClick语句结果是点击会变红色，但是value与list相同的项一直都是红色，该语句一直被执行
                viewHolder.imageView.setVisibility(View.VISIBLE);                                           //这段if代码要放在if，else代码后边，如果放在前边，也是执行顺序问题，最后编译出来不变红，为黑色，放在这里才会变红色
            }

            return convertView;
        }
        public void setCurrentItem(int currentItem){
            this.mCurrentItem=currentItem;
        }
        public void setClick(boolean click){this.isClick=click;}
        class ViewHolder{
            TextView textView;
            ImageView imageView;
        }
    }
}