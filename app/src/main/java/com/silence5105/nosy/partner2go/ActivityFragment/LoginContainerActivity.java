package com.silence5105.nosy.partner2go.ActivityFragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.silence5105.nosy.partner2go.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Nosy on 2017/3/15.
 */

public class LoginContainerActivity extends Activity {
    FrameLayout container;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.logincontainerlayout);
        container = (FrameLayout) findViewById(R.id.container);
        getFragmentManager().beginTransaction().replace(R.id.container, new LoginSelectFragment()).commit();

    }
    private static Boolean isExit = false;
    private static Boolean hasTask = false;

    Timer timerExit = new Timer();
    TimerTask task = new TimerTask() {

        @Override
        public void run() {
            isExit = false;
            hasTask = true;
        }
    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判斷是否按下Back
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            relativeLayout.setVisibility(View.INVISIBLE);
            // 是否要退出
            if (isExit == false) {
                isExit = true; //記錄下一次要退出
//                Toast.makeText(this, "再按一次返回離開程式", Toast.LENGTH_SHORT).show();
                // 如果超過兩秒則恢復預設值
                if (!hasTask) {
                    timerExit.schedule(task, 1000);
                }
            } else {
//                new AlertDialog.Builder(this)
//                        .setTitle("")
//                        .setMessage("exit app?")
//                        .setNegativeButton("no", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                            }
//                        })
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//
//                                System.exit(0);
//
//                            }
//                        }).show();
            }
        }
        return false;
    }
}
