package com.wwl.can.learn;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.wwl.can.BaseActivity;
import com.wwl.can.R;
import com.wwl.can.learn.bean.BookMessage;
import com.wwl.can.learn.cadapter.CommonAdapter;
import com.wwl.can.learn.cadapter.ViewHolder;
import com.wwl.can.learn.netutil.ApiObserver;
import com.wwl.can.learn.netutil.BaseSubscribe;
import com.wwl.can.learn.netutil.BookService;
import com.wwl.can.learn.netutil.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.Observable;

public class Learn extends BaseActivity {

    @Bind(R.id.lv_learn) ListView lvLearn;

    private List<String> list = new ArrayList<String>();

    private static final String[] Datas = { "android", "python", "java", "php", "c", "c++",
            "c#", "vb", "js", "kotlin", "eclipse", "cad", "pro/e" };
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
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

// ------------------------------rxjava---------------------------------------
//                Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
//                        Log.d(TAG, "Observable thread is : " + Thread.currentThread().getName());
//                        Log.d(TAG, "emit 1");
//                        emitter.onNext(1);
//                    }
//                });
//
//                Consumer<Integer> consumer = new Consumer<Integer>() {
//                    @Override
//                    public void accept(Integer integer) throws Exception {
//                        Log.d(TAG, "Observer thread is :" + Thread.currentThread().getName());
//                        Log.d(TAG, "onNext: " + integer);
//                        Toast.makeText(Learn.this,integer+"",Toast.LENGTH_LONG).show();
//                    }
//                };
//
//                observable.subscribe(consumer);

//                -------------------retrofit------------
//                Retrofit retrofit = new Retrofit.Builder()
//                        .baseUrl(Constant.URL_BASE)
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build();
//                RequestServices requestServices = retrofit.create(RequestServices.class);
//                Call<Bean> cal = requestServices.getString();
//                cal.enqueue(new Callback<Bean>() {
//                    @Override
//                    public void onResponse(Call<Bean> call, Response<Bean> response) {
//                        list.add(2,response.body().getLocation());
//                        adapter.notifyDataSetChanged();
//                    }
//
//                    @Override
//                    public void onFailure(Call<Bean> call, Throwable t) {
//
//                    }
//                });

                //------------------rxjava and retrofit------------------------

                BookService myService = ServiceGenerator.createService(BookService.class);
                Observable<BookMessage> bean = myService.searchBookMessage(list.get(position),1,1);
                new BaseSubscribe(Learn.this).subscribe(bean, new ApiObserver<BookMessage>() {
                    @Override
                    public void success(BookMessage bean) {
                        list.add(0,bean.getBooks().size() > 0 ? bean.getBooks().get(0).getAlt_title() : "没有搜索到书名");
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void error(Throwable e) {
                        Toast.makeText(Learn.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

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
