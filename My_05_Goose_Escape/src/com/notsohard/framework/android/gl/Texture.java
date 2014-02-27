package com.notsohard.framework.android.gl;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.opengles.GL10;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLUtils;

import com.notsohard.framework.FileIO;

public class Texture {
	GLGraphics glGraphics;
	FileIO fileIO;
	String fileName;
	public int textureId;
	int minFilter;
	int magFilter;
	int width;
	int height;
	
	public Texture(GLGame glGame, String fileName){
		this.glGraphics = glGame.getGLGraphics();
		this.fileIO = glGame.getFileIO();
		this.fileName = fileName;
		load();
	}
		
	private void load(){
		GL10 gl =glGraphics.getGL();
		int textureIds[] = new int[1];
		gl.glGenTextures(1, textureIds, 0);
		textureId = textureIds[0];
		
		InputStream in = null;
		try{
			in = fileIO.readAsset(fileName);
			Bitmap bitmap = BitmapFactory.decodeStream(in);
			width = bitmap.getWidth();
			height = bitmap.getHeight();
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
			GLUtils.texImage2D(GL10.GL_TEXTURE_2D, 0, bitmap, 0);
			setFilters(GL10.GL_LINEAR, GL10.GL_LINEAR);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, 0);
		} catch(IOException e){
			throw new RuntimeException("Couldnt load texture '"+fileName+"'", e);
		} finally {
			if(in!=null)
				try{in.close();} catch(IOException e){}
		}
	}
	
	private void reload(){
		load();
		bind();
		setFilters(minFilter, magFilter);
		glGraphics.getGL().glBindTexture(GL10.GL_TEXTURE_2D, 0);
	}
	
	private void setFilters(int minFilter, int magFilter) {
		this.magFilter = magFilter;
		this.minFilter = minFilter;
		GL10 gl = glGraphics.getGL();
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MAG_FILTER, minFilter);
		gl.glTexParameterf(GL10.GL_TEXTURE_2D, GL10.GL_TEXTURE_MIN_FILTER, magFilter);
	}
	
	public void bind(){
		GL10 gl = glGraphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
	}
	
	public void dispose(){
		GL10 gl = glGraphics.getGL();
		gl.glBindTexture(GL10.GL_TEXTURE_2D, textureId);
		int[] textureIds = {textureId};
		gl.glDeleteTextures(1, textureIds, 0);
	}
}
