package com.notsohard.goose.escape;

import java.nio.ShortBuffer;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import android.util.Log;

import com.notsohard.framework.Game;
import com.notsohard.framework.Input.TouchEvent;
import com.notsohard.framework.Screen;
import com.notsohard.framework.android.gl.GLGame;
import com.notsohard.framework.android.gl.GLGraphics;
import com.notsohard.framework.android.gl.Texture;
import com.notsohard.framework.android.gl.Vertices;

public class GLTestScreen extends Screen{

	GLGraphics glGraphics;
	//FloatBuffer vertices;
	ShortBuffer indices;
	Vertices vertices;
	Texture texture;
	
	int curX=0;
	int curY=0;
	
	int numYobas = 10;
	Yoba[] yobas;
	FPSCounter fps;
	
	public GLTestScreen(Game game) {
		super(game);
		fps = new FPSCounter();
		glGraphics = ((GLGame) game).getGLGraphics();

		vertices = new Vertices(glGraphics, 4, 6, false, true);
		vertices.setVertices(new float[]{ 	-64.0f, -64.0f, 0.0f, 1.0f, 
											64.0f, -64.0f, 1.0f, 1.0f, 
											64.0f, 64.0f, 1.0f, 0.0f,
											-64.0f, 64.0f, 0.0f, 0.0f }, 0, 16);
		vertices.setIndices(new short[]{0, 1, 2,
										2, 3, 0}, 0, 6);
				
		texture = new Texture( (GLGame)game, "sredni_yoba.png");
		
		yobas = new Yoba[numYobas];
		for (int i=0; i<numYobas; i++){
			yobas[i]=new Yoba();
		}
	}
	


	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		for(int i=0; i<numYobas; i++){
			yobas[i].update(deltaTime);
		}
		

		List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
		int len = touchEvents.size();
		for (int i = 0; i < len; i++) {
			TouchEvent event = touchEvents.get(i);
			if( (event.x!=curX) && (event.y!=curY) ){
				curX = event.x;
				curY = event.y;
				Log.d("Touch","X="+curX+" Y="+curY);
			}
			break;
		}
		
	}

	@Override
	public void present(float deltaTime) {
		GL10 gl = glGraphics.getGL();
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		vertices.bind();
		for(int i=0; i< numYobas; i++){
			gl.glLoadIdentity();
			gl.glTranslatef(yobas[i].x, yobas[i].y, 0);
			gl.glRotatef(yobas[i].angle, 0, 0, 1);
			vertices.draw(GL10.GL_TRIANGLES, 0, 6);
		}
		vertices.unbind();
		fps.logFrame();
	}
 
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		GL10 gl = glGraphics.getGL();
		gl.glViewport(0, 0, glGraphics.getWidth(), glGraphics.getHeight());
		gl.glClearColor(0, 1.0f, 1.0f, 1.0f);
		gl.glMatrixMode(GL10.GL_PROJECTION);	
		gl.glLoadIdentity();
		gl.glOrthof(0f, 1280.0f, 0f, 768.0f, 1, -1);
		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
		texture.reload();
		gl.glEnable(GL10.GL_TEXTURE_2D);
		texture.bind();
		gl.glMatrixMode(GL10.GL_MODELVIEW);

		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
