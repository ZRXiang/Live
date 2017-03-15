package com.zrx.live.gift;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;

import com.zrx.live.R;
import com.zrx.live.bean.Gift;

import java.util.ArrayList;
import java.util.List;

public class GiftWidget implements OnItemClickListener {
    public final static int ON_EMOJI_CHANGE = 900;
    public final static int SEND_GIFT = 1000;

    private Context context;
    private Activity activity; // 用来表明是哪个activity发起的

    private ViewPager emoji_viewpage;

    private LinearLayout emoji_cursor;

    private ArrayList<View> emojiPageViews;

    private List<GiftAdapter> emojiAdapters;

    private ArrayList<ImageView> emojiCursorViews;

    private List<List<Gift>> giftLists;
    private int current = 0;
    private EditText et_sendmessage;
    private int UI_FLAG;
    private Handler mUIHandler;

    public GiftWidget(ViewPager viewPager, LinearLayout linearLayout, Handler handler , final Context context) {
        this.giftLists = GiftPageUtils.INSTANCE.giftLists;
        this.context = context;
        this.emoji_viewpage = viewPager;
        this.emoji_cursor = linearLayout;
        this.mUIHandler = handler;
        Init_viewPager();
        Init_Point();
        Init_Data();
    }


    /**
     * 初始化显示表情的ViewPager
     */
    @SuppressWarnings("deprecation")
    private void Init_viewPager() {
        this.emojiPageViews = new ArrayList<View>();
        // 中间添加表情页
        this.emojiAdapters = new ArrayList<GiftAdapter>();
        for (int i = 0; i < giftLists.size(); i++) {
            GridView view = new GridView(context);
            GiftAdapter adapter = new GiftAdapter(context, giftLists.get(i));
            view.setAdapter(adapter);
            emojiAdapters.add(adapter);
            view.setOnItemClickListener(this);
            view.setNumColumns(3);
            view.setBackgroundColor(Color.TRANSPARENT);
            view.setHorizontalSpacing(1);
            view.setVerticalSpacing(1);
            view.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
            view.setCacheColorHint(0);
            view.setPadding(5, 0, 5, 0);

            view.setSelector(new ColorDrawable(Color.TRANSPARENT));
            view.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,
                    LayoutParams.WRAP_CONTENT));
            view.setGravity(Gravity.CENTER);
            this.emojiPageViews.add(view);
        }

//		// 右侧添加空页面
//		View nullView2 = new View(context);
//		// 设置透明背景
//		nullView2.setBackgroundColor(Color.TRANSPARENT);
//		this.emojiPageViews.add(nullView2);
    }



    public void refreshWidgetUI(Message msg) {
        switch(msg.what) {
            case ON_EMOJI_CHANGE:onPageSelected((Integer)msg.obj);
        }
    }
    /**
     * 初始化填充数据
     */
    private void Init_Data() {
        emoji_viewpage.getParent().getParent().requestDisallowInterceptTouchEvent(true);
        this.emoji_viewpage.setAdapter(new ViewPagerAdapter(this.emojiPageViews));

		this.emoji_viewpage.setOffscreenPageLimit(3);
        current = 0;
        this.emoji_viewpage.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                current = arg0;
                Message msg = mUIHandler.obtainMessage();
                msg.what = ON_EMOJI_CHANGE;
                msg.obj = arg0;
                msg.sendToTarget();

            }


            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

    }

    /**
     * 初始化游标
     */
    private void Init_Point() {
        ImageView imageView;
        for (int i = 0; i < emojiPageViews.size(); i++) {
            imageView = new ImageView(context);
            imageView.setBackgroundResource(R.mipmap.emoji_cursor_1);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
            layoutParams.leftMargin = 10;
            layoutParams.rightMargin = 10;
            layoutParams.width = 8;
            layoutParams.height = 8;
            emoji_cursor.addView(imageView, layoutParams);
            if (i == 0) {
                imageView.setBackgroundResource(R.mipmap.emoji_cursor_2);
            }
        }
    }





    /**
     * 当切换页面时会回调该方法
     */
    private void onPageSelected(int arg0) {
        for (int i = 0; i < emoji_cursor.getChildCount(); i++) {
            ImageView imageView = (ImageView) emoji_cursor.getChildAt(i);
            if (i == arg0) {
                imageView.setBackgroundResource(R.mipmap.emoji_cursor_2);

            }else {
                imageView.setBackgroundResource(R.mipmap.emoji_cursor_1);
            }

        }
    }


    @Override
    public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
        Gift gift = (Gift) this.emojiAdapters.get(this.current).getItem(position);
        Message msg = mUIHandler.obtainMessage();
        msg.what = SEND_GIFT;
        msg.obj = gift;
        msg.sendToTarget();
    }


}



