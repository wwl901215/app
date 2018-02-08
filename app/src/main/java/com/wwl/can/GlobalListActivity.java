package com.wwl.can;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wwl.can.learn.bean.GlobalListBean;
import com.wwl.can.learn.bean.GlobalListItemBean;
import com.wwl.can.learn.netutil.ApiObserver;
import com.wwl.can.learn.netutil.BaseSubscribe;
import com.wwl.can.learn.netutil.BookService;
import com.wwl.can.learn.netutil.ServiceGenerator;
import com.wwl.can.learn.readaper.GlobalListAdapter;
import com.wwl.can.learn.readaper.GlobalListBaseQuickAdapter;
import com.wwl.can.learn.utils.Utils;

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
    @Bind(R.id.srl) SwipeRefreshLayout srl;

    List<GlobalListItemBean> data;
    GlobalListAdapter adapter;
    GlobalListBaseQuickAdapter quickAdapter;

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
        recyclerview.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));//普通的布局模式
//        recyclerview.setLayoutManager(new GridLayoutManager(this, 2));//网格布局
//        recyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));//瀑布流布局，需要item高度不一样的情况下
        adapter = new GlobalListAdapter();
        quickAdapter = new GlobalListBaseQuickAdapter(this,R.layout.global_list_item,data);
        adapter.setContext(this);
        adapter.setData(data);
        adapter.setOnItemClick(new GlobalListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                GlobalListItemBean itemBean = data.get(position);
                Toast.makeText(GlobalListActivity.this, itemBean.getTitle(), Toast.LENGTH_SHORT).show();
                if (srl.isRefreshing()){
                    srl.setRefreshing(false);
                }else {
                    srl.setRefreshing(true);
                }
            }
        });
//        recyclerview.setAdapter(adapter);

        quickAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                GlobalListItemBean itemBean = data.get(position);
                Toast.makeText(GlobalListActivity.this, "BaseQuick:"+itemBean.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        recyclerview.setAdapter(quickAdapter);
        recyclerview.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                Log.e("onScrolled:state:",String.valueOf(newState));
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e("onScrolled:",String.valueOf(dy));
                boolean isBottom = Utils.isRecyclerViewBottom(recyclerView, dy);
                if (isBottom) {
                    Toast.makeText(GlobalListActivity.this,"滑动到底部了aaaaaa",Toast.LENGTH_SHORT).show();
                }
            }
        });
        srl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.e("refresh:","aaa");
            }
        });
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
//                    adapter.notifyDataSetChanged();
                    quickAdapter.notifyDataSetChanged();
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
