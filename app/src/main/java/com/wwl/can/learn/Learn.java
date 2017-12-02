package com.wwl.can.learn;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wwl.can.R;
import com.wwl.can.learn.cadapter.CommonAdapter;
import com.wwl.can.learn.cadapter.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Learn extends Activity {

    @Bind(R.id.lv_learn) ListView lvLearn;

    private List<String> list = new ArrayList<String>();

    private static final String[] Datas = { "北京", "上海", "广州", "深圳", "苏州", "南京",
            "武汉", "长沙", "杭州", "长春", "无锡", "常州", "绍兴", "嘉兴", "衢州" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        ButterKnife.bind(this);
        iniData();
//        iniView();
        iniView2();
    }

    private void iniData(){
        for (int i = 0; i < Datas.length; i++) {
            list.add(Datas[i]);
        }
    }

    //普通设置adapter的方法
    private void iniView(){
        final CityAdapter adapter = new CityAdapter(this,list);
        lvLearn.setAdapter(adapter);
        lvLearn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Learn.this,"item:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setAllClickListener(new CityAdapter.OnAllClickListener() {
            @Override
            public void itemClick(View view) {
                int position;
                position = (Integer) view.getTag();
                switch (view.getId()){
                    case R.id.iv_learn:
                        Toast.makeText(Learn.this,"button click:"+position,Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void itemLongClick(View view) {
                int position;
                position = (Integer) view.getTag();
                switch (view.getId()){
                    case R.id.iv_learn:
                        Toast.makeText(Learn.this,"button longclick:"+position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }

    //使用封装好的公共CommonAdapter
    private void iniView2(){

        final CommonAdapter<String> adapter = new CommonAdapter<String>(this,list,R.layout.learn_item) {
            @Override
            public void convert(ViewHolder helper, String item) {//用于设置item控件内容和设置点击事件
                helper.setText(R.id.tv_learn,item);
                helper.setButtonClick(R.id.iv_learn);
            }
        };
        lvLearn.setAdapter(adapter);
        lvLearn.setOnItemClickListener(new AdapterView.OnItemClickListener() {//listview自己的item点击事件
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(Learn.this,"item:"+position,Toast.LENGTH_SHORT).show();
            }
        });
        //手动设置item内部需要点击的点击事件
        adapter.setOnItemChildViewClick(new CommonAdapter.OnItemChildViewClickListener() {
            @Override
            public void onChildClick(View view) {
                int position;
                position = (Integer) view.getTag();
                switch (view.getId()){//根据空间id来判断是哪一个控件
                    case R.id.iv_learn:
                        Toast.makeText(Learn.this,"button click:"+position,Toast.LENGTH_SHORT).show();
                        list.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onChildLongClick(View view) {
                int position;
                position = (Integer) view.getTag();
                switch (view.getId()){
                    case R.id.iv_learn:
                        Toast.makeText(Learn.this,"button longclick:"+position,Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }



}
