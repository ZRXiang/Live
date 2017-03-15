package com.zrx.live.player;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.zrx.live.R;


public class UserAdapter extends BaseAdapter {

    private Context mContext;

    public UserAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return 1000;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return View.inflate(mContext, R.layout.item_user, null);
    }
}