package com.wwl.can;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wwl.can.learn.bean.GlobalListBean;
import com.wwl.can.learn.bean.GlobalListItemBean;
import com.wwl.can.learn.netutil.ApiObserver;
import com.wwl.can.learn.netutil.BaseSubscribe;
import com.wwl.can.learn.netutil.BookService;
import com.wwl.can.learn.netutil.ServiceGenerator;
import com.wwl.can.learn.readaper.GlobalListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;

public class GlobalListActivity extends BaseActivity {

    @Bind(R.id.tv_top_back) ImageView tvTopBack;
    @Bind(R.id.topbar_nickname) TextView topbarNickname;
    @Bind(R.id.recyclerview) RecyclerView recyclerview;

    List<GlobalListItemBean> data;
    GlobalListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_list);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    private void initView() {
        data = new ArrayList<>();
//        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//普通的布局模式
        recyclerview.setLayoutManager(new GridLayoutManager(this,2));//网格布局
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//瀑布流布局，需要item高度不一样的情况下
        adapter = new GlobalListAdapter();
        adapter.setContext(this);
        adapter.setData(data);
        recyclerview.setAdapter(adapter);
    }

    private void initData() {
        BookService globalService = ServiceGenerator.createService(BookService.class);
        Map<String, Object> params = new HashMap<>();
        params.put("id", "AP1706A007");
        params.put("lgroup", "0081");
        params.put("contentType", "4");
        Observable<GlobalListBean> globalListData = globalService.getGlobalListData(params);
        new BaseSubscribe(GlobalListActivity.this).subscribe(globalListData, new ApiObserver<GlobalListBean>() {
            @Override
            public void success(GlobalListBean globalListBean) {
                Toast.makeText(GlobalListActivity.this, "网络访问成功", Toast.LENGTH_SHORT).show();
                if (globalListBean.getCode().equals("200")) {
                    List<GlobalListBean.DataBean.PackageListBean.ComponentListBeanX.ComponentListBean> packageList = globalListBean
                            .getData()
                            .getPackageList()
                            .get(0)
                            .getComponentList()
                            .get(0)
                            .getComponentList();
                    for (GlobalListBean.DataBean.PackageListBean.ComponentListBeanX.ComponentListBean bean : packageList) {
                        GlobalListItemBean itemBean = new GlobalListItemBean();
                        itemBean.setImgUrl(bean.getFirstImgUrl());
                        itemBean.setPrice(bean.getSalePrice());
                        itemBean.setTitle(bean.getTitle());
                        data.add(itemBean);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void error(Throwable e) {
                Toast.makeText(GlobalListActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.tv_top_back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
