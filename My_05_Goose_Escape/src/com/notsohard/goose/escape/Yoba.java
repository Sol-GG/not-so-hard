package com.notsohard.goose.escape;

import java.util.Random;

public class Yoba {
	static final Random rand = new Random();
	public float x, y;
	float dirX, dirY;
	int angle;
	
	public Yoba(){
		x = rand.nextFloat() * 768;
		y = rand.nextFloat() * 1280;
		dirX = 100;
		dirY = 100;
		angle = rand.nextInt() % 180;
	}
	
	public void update(float deltaTime){
		x = x + dirX * deltaTime;
		y = y + dirY * deltaTime;
		
		if(x<0){
			dirX=-dirX;
			x=0;
		}
		
		if(x>1280){
			dirX=-dirX;
			x=1280;
		}
		
		if(y<0){
			dirY=-dirY;
			y=0;
		}
		
		if(y>768){
			dirY=-dirY;
			y=768;
		}
	}
	
}
