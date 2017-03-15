package com.zrx.live.player;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.zrx.live.R;


public class PlayerActivity extends FragmentActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, PlayerActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_player);
        PlayerFragment liveViewFragment = new PlayerFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.fra_player, liveViewFragment).commit();
        FunFragment funFragment = FunFragment.newInstance();
        funFragment.show(getSupportFragmentManager(), "fun");

    }


}