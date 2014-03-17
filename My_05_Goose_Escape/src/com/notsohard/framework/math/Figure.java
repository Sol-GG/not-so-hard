package com.notsohard.framework.math;

public abstract class Figure {
	public enum FigType{FIGURE_CIRCLE, FIGURE_RECT};
	public FigType type;
	
	public final Vector2 center = new Vector2();
	public final Vector2 size = new Vector2();
	
	Figure(float x, float y, float width, float height){
		this.size.set(width, height);
		this.center.set(x, y);
	}
		
}
