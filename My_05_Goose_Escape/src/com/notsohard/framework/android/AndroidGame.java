package com.notsohard.framework.android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;

import com.notsohard.framework.Audio;
import com.notsohard.framework.FileIO;
import com.notsohard.framework.Game;
import com.notsohard.framework.Graphics;
import com.notsohard.framework.Input;
import com.notsohard.framework.Screen;
import com.notsohard.framework.android.AndroidFastRenderView;
import com.notsohard.framework.android.AndroidGraphics;

public abstract class AndroidGame extends Activity implements Game {
	
	Graphics graphics;
	Screen screen;
	Input input;
	AndroidFastRenderView renderView;
	
	@Override
	public abstract Screen getStartScreen();
	
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        int frameBufferWidth = 400;
        int frameBufferHeight = 400;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Config.RGB_565);
        
        float scaleX = (float) frameBufferWidth
        	/ getWindowManager().getDefaultDisplay().getWidth();
        float scaleY = (float) frameBufferHeight
        	/ getWindowManager().getDefaultDisplay().getHeight();
        		
        input = new AndroidInput(this, renderView, scaleX, scaleY);
        
        
        graphics = new AndroidGraphics(getAssets(), frameBuffer);
                
        renderView = new AndroidFastRenderView(this, frameBuffer);
        
       
       
        screen = getStartScreen();
        
        setContentView(renderView);
    }
    
    public void onResume(){
    	super.onResume();
    	screen.resume();
    	renderView.resume();
    }
    
    public void onPause(){
    	super.onPause();
    	renderView.pause();
    	screen.pause();
    	
    	if(isFinishing())
    		screen.dispose();
    }
    
	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return input;
	}

	@Override
	public FileIO getFileIO() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Graphics getGraphics() {
		return graphics;
	}

	@Override
	public Audio getAudio() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setScreen(Screen screen) {
		if(screen == null)
			throw new IllegalArgumentException("Screen must not be null");
		
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}

	@Override
	public Screen getCurentScreen() {
		return screen;
	}	
}
