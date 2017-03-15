package com.zrx.live.gift;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zrx.live.R;
import com.zrx.live.bean.Gift;
import com.zrx.live.utils.ImageOption;

import java.util.List;


public class GiftAdapter extends BaseAdapter {

    private List<Gift> data;
    private LayoutInflater inflater;
    private int size = 0;

    public GiftAdapter(Context context, List<Gift> list) {
        this.inflater = LayoutInflater.from(context);
        this.data = list;
        this.size = list.size();
    }


    @Override
    public int getCount() {
        return this.size;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressWarnings("deprecation")
    ViewHolder viewHolder = null;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.gift_item,
                    parent, false);
            viewHolder.value = (TextView) convertView
                    .findViewById(R.id.item_value);
            viewHolder.pic = (ImageView) convertView
                    .findViewById(R.id.item_gift);
            viewHolder.name = (TextView) convertView
                    .findViewById(R.id.item_name);
            convertView.setTag(viewHolder);


        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }




        viewHolder.value.setText(data.get(position).gift_price + "");
        viewHolder.pic.setBackgroundResource(data.get(position).pic);
        viewHolder.name.setText(data.get(position).name);
        ImageLoader.getInstance().displayImage(data.get(position).gift_url, viewHolder.pic, ImageOption.defaultOptions);
        return convertView;
    }

    private class ViewHolder {
        TextView value;
        TextView name;
        ImageView pic;
    }
}