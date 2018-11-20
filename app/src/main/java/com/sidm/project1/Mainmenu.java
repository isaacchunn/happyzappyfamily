package com.sidm.project1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class Mainmenu extends Activity implements View.OnClickListener{
    //Define object button and pass it to another method to use
    private Button btn_start;
    private Button btn_help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.mainmenu);

        //to make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide title bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mainmenu);

        //Set Listener to Buttons
        btn_start = (Button)findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        btn_help = (Button)findViewById(R.id.btn_help);
        btn_help.setOnClickListener(this);
    }

    @Override
    // A callback method
    public void onClick(View v) {
        //Intent = action to be performed
        //Intent is an object that you need to create and a new instance to use it

        Intent intent = new Intent();

        if(v == btn_start)
        {
            intent.setClass(this, Splashpage.class);
        }
        else if(v == btn_help)
        {
            intent.setClass(this, GamePage.class);
        }
        startActivity(intent);
    }

   // View.OnClickListener onClickListener = new View.OnClickListener(){}

}
