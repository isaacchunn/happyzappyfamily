package com.sidm.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

public class Splashpage  extends Activity{

    protected boolean  _active = true;
    protected int _splashTime = 5000;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.splashpage);

        //thread for displaying the splash screen
        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (_active && (waited < _splashTime)) {
                        sleep(200);
                        if (_active) {
                            waited += 200;
                        }
                    }
                } catch (InterruptedException e) {
                    //do nothing
                } finally {
                    finish();
                    //create new activity based on and intent with currentActivity

                    Intent intent = new Intent(Splashpage.this, Mainmenu.class);
                    startActivity(intent);
                }

            }
        };

        splashThread.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            _active = false;
        }
        return true;
    }
}
