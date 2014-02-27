package com.notsohard.framework.android.gl;

import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.notsohard.framework.Audio;
import com.notsohard.framework.FileIO;
import com.notsohard.framework.Game;
import com.notsohard.framework.Graphics;
import com.notsohard.framework.Input;
import com.notsohard.framework.Screen;
import com.notsohard.framework.android.AndroidFileIO;

public abstract class GLGame extends Activity implements Game, Renderer{
	enum GLGameState{
		Initialised,
		Running,
		Paused,
		Finished,
		Idle
	}
	
	GLSurfaceView glView;
	GLGraphics glGraphics;
	Screen screen;
	FileIO fileIO;
	GLGameState state = GLGameState.Initialised;
	Object stateChanged = new Object();
	long startTime = System.nanoTime();
	
	@Override
	public abstract Screen getStartScreen();
	
	protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);    
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        glView = new GLSurfaceView(this);
        glView.setRenderer(this);
        setContentView(glView);
        
        glGraphics = new GLGraphics(glView);  
        fileIO = new AndroidFileIO(this);
    }
	
	public void onResume(){
		super.onResume();
		glView.onResume();
	}
	
	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		Log.d("Text", "SurfaceCreate");
		glGraphics.setGL(gl);
		synchronized(stateChanged){
			if(state == GLGameState.Initialised)
				screen = getStartScreen();
			state = GLGameState.Running;
			screen.resume();
			startTime = System.nanoTime();
		}
	}
	
	@Override
	public void onSurfaceChanged(GL10 arg0, int arg1, int arg2) {
		//We never resize windows in android
	}

	@Override
	public void onDrawFrame(GL10 arg0) {
		//В процессе выполнения может рассинхронизироваться если приложение захочет встать на паузу
		GLGameState state = null;
		
		synchronized(stateChanged){
			state = this.state;
		}
		
		if(state == GLGameState.Running){
			float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
			startTime = System.nanoTime();
			screen.update(deltaTime);
			screen.present(deltaTime);
		}
		
		if(state == GLGameState.Paused){
			screen.pause();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
		}
		
		if(state == GLGameState.Finished){
			screen.pause();
			screen.dispose();
			synchronized (stateChanged) {
				this.state = GLGameState.Idle;
				stateChanged.notifyAll();
			}
		}
	}
	
	public void onPause() {
		synchronized (stateChanged) {
			if(isFinishing())
				state = GLGameState.Finished;
			else
				state = GLGameState.Paused;
			while(true){
				try{
					stateChanged.wait();
					break;
				} catch(InterruptedException e){
				}
			}
		}
		glView.onPause();
		super.onPause();
	}
	
	public GLGraphics getGLGraphics(){
		return glGraphics;
	}

	@Override
	public Input getInput() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		throw new IllegalStateException("We are using OpenGL");
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
	
	static class SimpleRenderer implements Renderer{
		Random rand = new Random();
		@Override
		public void onDrawFrame(GL10 gl) {
			gl.glClearColor(rand.nextFloat(), rand.nextFloat(), rand.nextFloat(), 1);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			Log.d("Testin", "Created");
		}
	}
	
}
