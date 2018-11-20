package com.sidm.project1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class UpdateThread extends Thread {

    //Define some parameters
    static final long targetFPS = 60;
    private GameView view = null;
    private SurfaceHolder holder = null;
    private boolean isRunning = false;

    //GameView = Surfaceview
    public UpdateThread(GameView _view)
    {
        view = _view;
        holder = _view.getHolder();

        SampleGame.Instance.Init(view);
    }

    public boolean IsRunning()
    {
        return isRunning;
    }

    public void Initialize()
    {
        isRunning = true;
    }

    public void Terminate()
    {
        isRunning = false;
    }

    @Override //every game thread/ thread will always have a run() method
    public void run()
    {
        // Init variables here
        long framePerSecond = 1000 / targetFPS; // 1000 is milliseconds -> 1 second
        long startTime = 0;
        long prevTime = System.nanoTime();

        while(IsRunning())
        {
            // Update
            startTime = System.currentTimeMillis();

            // Get delta time
            long currTime = System.nanoTime();
            float deltaTime = (float)((currTime - prevTime) / 1000000000.0f);
            prevTime = currTime;
            // End delta time

            SampleGame.Instance.Update(deltaTime);

            // for Render to happen, holder will lock the canvas
            //Canvas is like a plain sheet for you to draw or put images

            Canvas canvas = holder.lockCanvas(); //Have to be locked before you can run or render things

            if (canvas != null)//Canvas cannot be empty!
            {
                synchronized (holder) // Lock the door
                {
                    // Start to do render
                    canvas.drawColor(Color.GREEN); // == GL_ClearColor - background color

                    //Game Scene
                    SampleGame.Instance.Render(canvas);

                }
                holder.unlockCanvasAndPost(canvas); //unlock canvas
            }

            // Post Update
            try
            {
                long sleepTime = framePerSecond - (System.currentTimeMillis() - startTime);

                if (sleepTime > 0)
                    sleep(sleepTime);
            }
            catch(InterruptedException e)
            {
                Terminate();
            }
        }
    }
}
