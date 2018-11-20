package com.sidm.project1;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.SurfaceView;

//Your game scene 1
public class SampleGame {

    //Write a singleton
    public final static SampleGame Instance = new SampleGame();

    //Define object name
    private Bitmap bmp; //pictures like png files are known as bit map
    float offset = 0.0f; // move things around if it works

    private SampleGame()
    {

    }

    public void Init(SurfaceView _View)
    {
        //Load Resources for e.g. Images

        bmp = BitmapFactory.decodeResource(_View.getResources(), R.drawable.button);
    }

    public void Update(float _deltaTime)
    {
        //Use dt to do some update or run something
        offset+=_deltaTime;
    }

    public void Render(Canvas _canvas)
    {
        //draw something

        int currOffset = (int)(offset*100.f);
        _canvas.drawBitmap(bmp,(10 + currOffset )%500, 10, null); //left = x, top = y, counting from top left corner(0,0)
        //making x value varies with dt and offset

    }
}
