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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=findViewById(R.id.list);
        for (int i=0;i<30;i++){
            list.add("测试文字"+i);
        }
        final Myadapter adapter=new Myadapter();
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                adapter.setCurrentItem(position);                                                        //将adapter的currentItem和click的position绑定
                adapter.setClick(true);                                                                 //点击为真触发
                adapter.notifyDataSetChanged();
            }
        });
    }
    class Myadapter extends BaseAdapter{                                                               //自定义适配器
        private int mCurrentItem=0;
        private boolean isClick=false;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                convertView=LayoutInflater.from(MainActivity.this).inflate(R.layout.list,null);          //将自己的listview的item填充进去
            }
            TextView textView=convertView.findViewById(R.id.text);
            ImageView imageView=convertView.findViewById(R.id.image);
            textView.setText((String)list.get(position));
            if (mCurrentItem==position&&isClick){
                textView.setTextColor(Color.RED);
                imageView.setVisibility(View.VISIBLE);
            }else {
                textView.setTextColor(Color.BLACK);
                imageView.setVisibility(View.GONE);
            }
            return convertView;
        }
        public void setCurrentItem(int currentItem){
            this.mCurrentItem=currentItem;
        }
        public void setClick(boolean click){
            this.isClick=click;
        }
    }
}