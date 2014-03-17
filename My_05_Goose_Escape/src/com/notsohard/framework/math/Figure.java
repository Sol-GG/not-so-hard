package com.notsohard.framework.math;

public abstract class Figure {
	public enum FigType{FIGURE_CIRCLE, FIGURE_RECT};
	public FigType type;
	
	public float width, height;
	
	Figure(float width, float height){
		this.width = width;
		this.height = height;
	}
	
}
