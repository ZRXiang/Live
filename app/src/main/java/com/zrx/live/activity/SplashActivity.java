package com.zrx.live.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.Toast;

import com.zrx.live.R;
import com.zrx.live.permission.MPermission;
import com.zrx.live.permission.OnMPermissionDenied;
import com.zrx.live.permission.OnMPermissionGranted;


/**
 * Created by xiaoyuan on 16/6/12.
 */
public class SplashActivity extends Activity {

    private Animation alphaAnimation;
    private final int BASIC_PERMISSION_REQUEST_CODE = 110;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);//设置全屏
        setContentView(R.layout.act_splash);
        if (!isTaskRoot()) {
            finish();
            return;
        }

        requestBasicPermission();

    }

    /**
     * 请求权限
     * 因为是在6.0开发，这些权限需要手动去请求
     */
    private void requestBasicPermission() {
        MPermission.with(SplashActivity.this)
                .addRequestCode(BASIC_PERMISSION_REQUEST_CODE)
                .permissions(
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO
                )
                .request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        MPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @OnMPermissionGranted(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionSuccess() {
        //延迟3秒进入主界面
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if (!TextUtils.isEmpty(PreferenceUtils.getsessionId(SplashActivity.this))) {
//                    Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }, 3000);

    }

    @OnMPermissionDenied(BASIC_PERMISSION_REQUEST_CODE)
    public void onBasicPermissionFailed() {
        Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
    }


}
