package com.notsohard.goose.escape;

import android.graphics.Color;

import com.notsohard.framework.Game;
import com.notsohard.framework.Screen;
import com.notsohard.framework.Graphics.PixmapFormat;
import com.notsohard.framework.android.AndroidPixmap;

public class MainGameScreen extends Screen{

	AndroidPixmap YOBA;
	float x;
	
	public MainGameScreen(Game game) {
		super(game);
		YOBA = (AndroidPixmap) game.getGraphics().newPixmap("sredni_yoba.png", PixmapFormat.ARGB8888);
		x=-128;
	}

	
	@Override
	public void update(float deltaTime) {
		float deltaTimeBuff;
		x+=30*deltaTime;
		if(x>400)
			x=-128;
	}

	@Override
	public void present(float deltaTime) {
		game.getGraphics().clear(Color.CYAN);
		game.getGraphics().drawPixmap(YOBA, (int)x, (int)x);
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
