package com.zrx.live.bean;

import java.io.Serializable;

/**
 * Created by xiaoyuan on 16/6/22.
 */
public class Gift implements Serializable {
    public long id;
    public String name;
    public int  gift_price;
    public long created_at;
    public long updated_at;
    public int pic;
    public String gift_url;

}
