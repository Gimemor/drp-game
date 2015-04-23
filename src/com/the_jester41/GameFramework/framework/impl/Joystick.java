package com.the_jester41.GameFramework.framework.impl;

import java.util.List;


import com.the_jester41.GameFramework.framework.Button;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;
import com.the_jester41.GameFramework.framework.Pixmap;

public class Joystick implements Button {
	boolean isActive = false;
	int radius;
	int x, y;
	int size_w, size_h;
	float pos_x, pos_y;
	int cx, cy;
	Graphics graphics;
	Pixmap atlas;
	boolean drawUnusedStick = true;
	int act_pointer = -1;
	
	public Joystick( int x, int y, Pixmap atlas, int size_w, int size_h, Game game )
	{
		setCord(x, y); this.radius = size_w / 2;
		setImage(atlas, size_w, size_h);
		cx = x + size_w / 2;
		cy = y + size_w / 2;
		pos_x = pos_y = 0;
		graphics = game.getGraphics();
		
	}
	
	@Override
	public boolean isPressed() {
		return isActive;
	}

	@Override
	public void setImage(Pixmap atlas, int size_w, int size_h) {
		this.atlas = atlas;
		this.size_w = size_w; this.size_h = size_h;
		
	}

	@Override
	public void setCord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void drawButton() {
		if(!isActive)
			graphics.drawPixmap(atlas, x, y, 0, 0, size_w, size_h);
		else
			graphics.drawPixmap(atlas, x, y, size_w, 0, size_w, size_h);
		
		if(drawUnusedStick || isActive)
			graphics.drawPixmap(atlas, (int)(x+pos_x), (int)(y+pos_y), 2*size_w, 0, size_w, size_h);
		
	}


	@Override
	public int checkForInterception(List<TouchEvent> e) {
		if(e == null) return 0; 
		int len = e.size();
		int x; int y, tx, ty;
		int type; int result=0;
		for(int i = 0; i < len; i++)
		{
			x = e.get(i).x;
			y = e.get(i).y;
			type = e.get(i).type;
			if( x > this.x && x < (this.x + size_w) && y > this.y && y < (this.y + size_h) 
					&& type != TouchEvent.TOUCH_UP )
			{
					isActive = true;
					result =  State.PRESSED;
					act_pointer = e.get(i).pointer;
					tx = x - cx;
					ty = y - cy;
					if( tx * tx + ty * ty <= radius * radius) {
						pos_x = tx;
						pos_y = ty;
					}
					else
					{
						pos_x = (int) (radius*tx/Math.sqrt(ty*ty+tx*tx));
						pos_y = (int) (radius*ty/Math.sqrt(ty*ty+tx*tx));
					}
					
				
			}
			else if(type == TouchEvent.TOUCH_DRAGGED && act_pointer == e.get(i).pointer)
			{
				result =  State.PRESSED;
				tx = x - cx;
				ty = y - cy;
				pos_x = (int) (radius*tx/Math.sqrt(ty*ty+tx*tx));
				pos_y = (int) (radius*ty/Math.sqrt(ty*ty+tx*tx));
			}
			else if( type == TouchEvent.TOUCH_UP && act_pointer == e.get(i).pointer)
			{
					isActive = false;
					result = State.RELEASED;
					act_pointer = -1;
					pos_x = pos_y = 0;
			}
		}	
		
	    return result;	
	}
	
	
	public float getPosX()
	{ return pos_x/radius; }
	
	public float getPosY()
	{ return -pos_y/radius; }
	
	public int getRadius()
	{ return radius; }
	
	public void clearPos()
	{ pos_x = 0; pos_y = 0; isActive = false; }

}
