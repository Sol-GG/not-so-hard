package com.notsohard.framework;

import com.notsohard.framework.math.Vector2;

public class DynamicGameObject extends GameObject {
	
	public final Vector2 velocity;
	public final Vector2 accel;
	
	public DynamicGameObject(float x, float y, float r) {
		super(x,  y, r);
		velocity = new Vector2();
		accel = new Vector2();
	}
}
