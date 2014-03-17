package com.notsohard.framework.math;

import com.notsohard.framework.math.Figure.FigType;


public class Circle extends Figure {
	
	public float radius;
	
	public Circle(float x, float y, float radius) {
		super(x, y, radius*2, radius*2);
		this.radius = radius;
		this.type = FigType.FIGURE_CIRCLE;
	}
	
}