package com.notsohard.framework.math;

import android.util.Log;

import com.notsohard.framework.math.Figure.FigType;

public class OverlapTester {
	
	public static boolean overlapCircles(Circle c1, Circle c2) {
		float distance = c1.center.distSquared(c2.center);
		float radiusSum = c1.radius + c2.radius;
		return distance <= radiusSum * radiusSum;
	}
	
	
	public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
		if(r1.getLowerLeft().x < r2.getLowerLeft().x + r2.size.x 
				&& r1.getLowerLeft().x + r1.size.x > r2.getLowerLeft().x 
				&&	r1.getLowerLeft().y < r2.getLowerLeft().y + r2.size.y 
				&& r1.getLowerLeft().y + r1.size.y > r2.getLowerLeft().y)
			return true;
		else
			return false;
	}
	
	
	public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
		float closestX = c.center.x;
		float closestY = c.center.y;
		
		if(c.center.x < r.getLowerLeft().x) {
			closestX = r.getLowerLeft().x;
		} else if(c.center.x > r.getLowerLeft().x + r.size.x) {
			closestX = r.getLowerLeft().x + r.size.x;
		}
		
		if(c.center.y < r.getLowerLeft().y) {
			closestY = r.getLowerLeft().y;
		} else if(c.center.y > r.getLowerLeft().y + r.size.y) {
			closestY = r.getLowerLeft().y + r.size.y;
		}
		
		return c.center.distSquared(closestX, closestY) < c.radius * c.radius;
	}
	
	public static boolean overlap(Figure x, Figure y)
	{
		if( (x.type==FigType.FIGURE_CIRCLE) && (y.type==FigType.FIGURE_CIRCLE) ){
			return overlapCircles((Circle)x, (Circle)y);
		} else if( (x.type==FigType.FIGURE_RECT) && (y.type==FigType.FIGURE_RECT) ) {
			return overlapRectangles((Rectangle)x, (Rectangle)y);
		} else if (x.type==FigType.FIGURE_CIRCLE) {
			return overlapCircleRectangle((Circle)x, (Rectangle)y);
		} else {
			return overlapCircleRectangle((Circle)y, (Rectangle)x);
		}
	}
	
	
	public static boolean pointInCircle(Circle c, Vector2 p) {
		return c.center.distSquared(p) < c.radius * c.radius;
	}
	
	public static boolean pointInCircle(Circle c, float x, float y) {
		return c.center.distSquared(x, y) < c.radius * c.radius;
	}
	
	public static boolean pointInRectangle(Rectangle r, Vector2 p) {
		return r.getLowerLeft().x <= p.x && r.getLowerLeft().x + r.size.x >= p.x &&
				r.getLowerLeft().y <= p.y && r.getLowerLeft().y + r.size.y >= p.y;
	}
	
	public static boolean pointInRectangle(Rectangle r, float x, float y) {
		return r.getLowerLeft().x <= x && r.getLowerLeft().x + r.size.x >= x &&
				r.getLowerLeft().y <= y && r.getLowerLeft().y + r.size.y >= y;
	}
}