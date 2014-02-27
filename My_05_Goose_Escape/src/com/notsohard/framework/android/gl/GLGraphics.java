package com.notsohard.framework.android.gl;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLSurfaceView;

public class GLGraphics {
	GLSurfaceView glView;
	GL10 gl;
	
	public GLGraphics(GLSurfaceView glView) {
		this.glView = glView;
	}
	
	public void setGL(GL10 gl){
		this.gl = gl;
	}
	
	public GL10 getGL(){
		return gl;
	}
	
	public int getHeight(){
		return glView.getHeight();
	}
	
	public int getWidth(){
		return glView.getWidth();
	}
}
