package com.notsohard.framework;

import com.notsohard.framework.math.Circle;
import com.notsohard.framework.math.Figure;
import com.notsohard.framework.math.Figure.FigType;
import com.notsohard.framework.math.Rectangle;
import com.notsohard.framework.math.Vector2;

public class GameObject {
	
	public final Vector2 position;
	public  Figure bounds;
	public final FigType type;
	
	public GameObject(float x, float y, float r) {
		this.position = new Vector2(x,y);
		this.bounds = new Circle(x, y, r);
		this.type = this.bounds.type;
	}
	
	public GameObject(float x, float y, float width, float height){
		this.position = new Vector2(x,y);
		this.bounds = new Rectangle(x-width/2, y-height/2, width, height);
		this.type = this.bounds.type;
	}
	
	
	
}
