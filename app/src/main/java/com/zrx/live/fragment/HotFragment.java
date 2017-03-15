package com.zrx.live.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zrx.live.R;
import com.zrx.live.adapter.HotAdapter;
import com.zrx.live.api.HomeApi;
import com.zrx.live.bean.LiveBean;
import com.zrx.live.network.RetrofitManager;
import com.zrx.live.player.PlayerActivity;
import com.zrx.live.utils.ToastUtils;
import com.zrx.live.widget.OnLoadMoreListener;
import com.zrx.live.widget.OnMultiItemClickListeners;
import com.zrx.live.widget.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.FormBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by xiaoyuan on 17/3/8.
 */

public class HotFragment extends BaseFragment {


    @BindView(R.id.hot_rv)
    RecyclerView hotRv;
    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;

    private HotAdapter mAdapter;
    private List<LiveBean> list = new ArrayList<>();
    private List<LiveBean> loadlist = new ArrayList<>();
    private int page = 1;


    public static HotFragment newInstance() {
        HotFragment fragment = new HotFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_hot, null);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        swiperefreshlayout.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary));
        swiperefreshlayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                getFavLive(page);

            }
        });
        //初始化adapter
        mAdapter = new HotAdapter(getActivity(), null, true);
        //设置加载更多触发的事件监听
        mAdapter.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(boolean isReload) {
                page++;
                getFavLive(page);

            }
        });
        mAdapter.setOnMultiItemClickListener(new OnMultiItemClickListeners<LiveBean.ResultBean.ListBean>() {
            @Override
            public void onItemClick(ViewHolder viewHolder, LiveBean.ResultBean.ListBean data, int position, int viewType) {
                PlayerActivity.start(getActivity());

            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        hotRv.setLayoutManager(layoutManager);
        hotRv.setAdapter(mAdapter);
        getFavLive(1);

    }

    /***
     * 翻页参数
     * @param page
     * 老师用的是retrofit网络框架，你们用的okhttputils
     */
    private void getFavLive(final int page) {
        FormBody formBody = new FormBody.Builder()
                .add("type", 1 + "")
                .add("page", page + "")
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<LiveBean> checkUserCall = homeApi.get_live(formBody);
        checkUserCall.enqueue(new Callback<LiveBean>() {
            @Override
            public void onResponse(Call<LiveBean> call, Response<LiveBean> response) {
                if (response.body().getError_code() == 0) {
                    //如果page=1 说明是下拉刷新或者第一次进入页面
                    if (page == 1) {
                        mAdapter.setNewData(response.body().getResult().getList());//把数据源交给适配器
                        swiperefreshlayout.setRefreshing(false);//刷新动画结束
                        mAdapter.setLoadingView(R.layout.load_more_layout);//初始化加载更多布局
                    } else {
                        //假如数据不为空，说明有数据
                        if (response.body().getResult().getList() != null && response.body().getResult().getList().size() > 0) {
                            mAdapter.setLoadMoreData(response.body().getResult().getList());

                        } else {
                            //假如数据为空，说明加载完了，初始化没有更多数据
                            mAdapter.setLoadEndView(R.layout.load_end_layout);
                        }
                    }

                } else {
                    ToastUtils.showLong("请求失败");
                }
            }

            @Override
            public void onFailure(Call<LiveBean> call, Throwable t) {
                ToastUtils.showLong(getResources().getString(R.string.net_error));
            }
        });
    }


}
