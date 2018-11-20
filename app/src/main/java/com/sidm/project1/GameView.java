package com.sidm.project1;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView
{

    //Surface view has a holder to be used to hold the ocntent
    private SurfaceHolder holder = null;

    //Thread to be known for its existence
    private UpdateThread updateThread = new UpdateThread(this);

    public GameView(Context _context)
    {
        super(_context);
        holder = getHolder();

        if(holder != null)
        {

            holder.addCallback(new SurfaceHolder.Callback() {
                @Override
                public void surfaceCreated(SurfaceHolder holder)
                {
                    //setup some stuff to indicate whether thread is running and initialized
                    if(!updateThread.IsRunning())
                    {
                        updateThread.Initialize();
                    }

                    if(!updateThread.isAlive())
                    {
                        updateThread.start();
                    }
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                    //nothing to type here coz it will be handled by the thread
                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder)
                {
                    //Done then thread should not run too
                    updateThread.Terminate();
                }
            });

        }
    }
}
