package com.zrx.live.gift;

import android.content.Context;

import com.zrx.live.bean.Gift;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public enum GiftPageUtils {

    INSTANCE;


    private int pageSize = 9;

    private HashMap<String, String> emojiMap = new HashMap<String, String>();


    private List<Gift> gifts = new ArrayList<Gift>();

    public List<List<Gift>> giftLists = new ArrayList<List<Gift>>();

    private GiftPageUtils() {

    }

    public void init(Context context, List<Gift> gifts) {
        this.gifts = gifts;
        ParseData(gifts,context);
    }

    /**
     * 解析字符
     *
     * @param data
     */
    private synchronized void ParseData(List<Gift> data, Context context) {
        if (this.giftLists.size() > 0) return;
        if (data == null) {
            return;
        }
        int pageCount;
        if(gifts.size()%9 == 0){
            pageCount = (int) Math.ceil(gifts.size() / 9);
        }else {
            pageCount = (int) Math.ceil(gifts.size() / 9)+1;
        }
        for (int i = 0; i < pageCount; i++) {
            giftLists.add(getData(i));
        }
    }

    /**
     * 获取分页数据
     *
     * @param page
     * @return
     */
    private List<Gift> getData(int page) {
        int startIndex = page * pageSize;
        int endIndex = startIndex + pageSize;

        if (endIndex > gifts.size()) {
            endIndex = gifts.size();
        }
        List<Gift> sublist = gifts.subList(startIndex, endIndex);
        return sublist;
    }

}