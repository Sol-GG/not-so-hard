package com.notsohard.framework.math;

public class Rectangle extends Figure {

	private Vector2 lowerLeft = new Vector2();
	public Rectangle(float x, float y, float width, float height) {
		super(x, y, width, height);
		this.type = FigType.FIGURE_RECT;
	}
	
	public Vector2 getLowerLeft(){
		lowerLeft.set(center.x - size.x/2 , center.y - size.y/2);
		return lowerLeft;
	}
}
