package com.zrx.live.gift;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zrx.live.R;
import com.zrx.live.bean.Gift;

import java.util.ArrayList;
import java.util.List;

import static com.zrx.live.gift.GiftWidget.ON_EMOJI_CHANGE;


public class GiftPopwin extends PopupWindow {
    private Context context;
    private View view;
    private GiftWidget giftWidget;
    private ViewPager giftViewpager;
    private LinearLayout giftCursor;
    private Handler mHandler;


    public GiftPopwin(final Context context, final Handler handler) {
        super(context);
        this.context = context;
        this.mHandler = handler;
        this.view = LayoutInflater.from(context).inflate(R.layout.gift_pop, null);
        giftViewpager = (ViewPager) view.findViewById(R.id.gift_viewpager);
        giftCursor = (LinearLayout) view.findViewById(R.id.gift_cursor);
        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                int height = view.findViewById(R.id.pop_layout).getTop();

                int y = (int) event.getY();
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (y < height) {
                        dismiss();
                    }
                }
                return true;
            }
        });


    /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(RelativeLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(RelativeLayout.LayoutParams.MATCH_PARENT);
        // 设置弹出窗体可点击
        this.setFocusable(true);
        // 实例化一个ColorDrawable颜色为半透明
//        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.pop_anim);
        initGift();

    }


    private void initGift() {
        List<Gift> gifts = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Gift gift= new Gift();
            gift.name = "飞机"+i;
            gift.gift_url = "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=338834550,1574741889&fm=23&gp=0.jpg";
            gift.gift_price = 100;
            gifts.add(gift);
        }
        GiftPageUtils.INSTANCE.init(context, gifts);
        giftWidget = new GiftWidget(giftViewpager, giftCursor, mHandler, context);

        giftViewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Message msg = mHandler.obtainMessage();
                msg.what = ON_EMOJI_CHANGE;
                msg.obj = position;
                msg.sendToTarget();
                giftWidget.refreshWidgetUI(msg);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

}
