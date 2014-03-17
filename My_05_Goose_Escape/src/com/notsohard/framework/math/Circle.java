package com.notsohard.framework.math;

import com.notsohard.framework.math.Figure.FigType;


public class Circle extends Figure {
	
	public final Vector2 center = new Vector2();
	public float radius;
	
	public Circle(float x, float y, float radius) {
		super(radius*2, radius*2);
		this.center.set(x,y);
		this.radius = radius;
		this.type = FigType.FIGURE_CIRCLE;
	}
	
}