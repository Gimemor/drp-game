package com.the_jester41.GameTest;

import com.the_jester41.GameFramework.framework.Pixmap;

public class Car {
	public Car(float x, float y, float sx, float sy, Pixmap car) {
		 this.x = x;
		 this.y = y;
		 x_speed = sx;
		 y_speed = sy;
		 img = car; row = 0;
	}
	public Car set(float x, float y, float sx, float sy, Pixmap car, int row)
	{
		 this.x = x;
		 this.y = y;
		 x_speed = sx;
		 y_speed = sy;
		 img = car; this.row = row;
		return this;
	}
	public float x_speed;
	public float y_speed;
	public float x; 
	public float y;
	public int row;
	Pixmap img;
}
