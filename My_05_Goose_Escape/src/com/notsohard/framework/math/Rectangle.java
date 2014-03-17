package com.notsohard.framework.math;

public class Rectangle extends Figure {
	
	public final Vector2 lowerLeft;
	//public float width, height;
	
	public Rectangle(float x, float y, float width, float height) {
		super(width, height);
		this.lowerLeft = new Vector2(x,y);
		this.type = FigType.FIGURE_RECT;
	}
	
}
