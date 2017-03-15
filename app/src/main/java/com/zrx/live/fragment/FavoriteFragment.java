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

public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.fav_rv)
    RecyclerView favRv;
    @BindView(R.id.fav_swipe)
    SwipeRefreshLayout favSwipe;


    private HotAdapter mAdapter;
    private List<LiveBean> list = new ArrayList<>();
    private List<LiveBean> loadlist = new ArrayList<>();
    private int page = 1;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_favorite, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }


    private void initView() {
        favSwipe.setColorSchemeColors(getActivity().getResources().getColor(R.color.colorPrimary));
        favSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        favRv.setLayoutManager(layoutManager);
        favRv.setAdapter(mAdapter);
        getFavLive(page);
    }
//


    private void getFavLive(final int page) {
        FormBody formBody = new FormBody.Builder()
                .add("type", 0 + "")
                .add("page", page + "")
                .build();
        HomeApi homeApi = RetrofitManager.getTestRetrofit().create(HomeApi.class);
        Call<LiveBean> checkUserCall = homeApi.get_live(formBody);
        checkUserCall.enqueue(new Callback<LiveBean>() {
            @Override
            public void onResponse(Call<LiveBean> call, Response<LiveBean> response) {
                if (response.body().getError_code() == 0) {
                    if (page == 1) {
                        mAdapter.setNewData(response.body().getResult().getList());
                        favSwipe.setRefreshing(false);
                        mAdapter.setLoadingView(R.layout.load_more_layout);
                    } else {
                        if (response.body().getResult().getList() != null && response.body().getResult().getList().size() > 0) {
                            mAdapter.setLoadMoreData(response.body().getResult().getList());
                        } else {
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
