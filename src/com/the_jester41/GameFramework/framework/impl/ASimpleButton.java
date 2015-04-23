package com.the_jester41.GameFramework.framework.impl;

import java.util.List;

import android.util.Log;

import com.the_jester41.GameFramework.framework.Button;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;
import com.the_jester41.GameFramework.framework.Pixmap;

public class ASimpleButton implements Button {
	Pixmap atlas;
	boolean isPressed;
	int x, size_w;
	int y, size_h; 
	Graphics graphics;
	int act_pointer = -1;
	public ASimpleButton(int x, int y, Pixmap img, int size_w, int size_h, Game game)
	{
		setCord(x,y);
		setImage(img, size_w, size_h);
		this.graphics=game.getGraphics();
	}
	
	@Override
	public boolean isPressed() {
		return isPressed();
	}
	


	@Override
	public void setImage(Pixmap img, int size_w, int size_h) {
		if(atlas!=null)
			atlas.dispose();
		atlas = img;
		this.size_w = size_w;
		this.size_h = size_h;
				
	}

	@Override
	public void setCord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void drawButton() {
		if( !isPressed ) 
			graphics.drawPixmap(atlas, x, y, 0, 0, size_w, size_h);
		else
			graphics.drawPixmap(atlas, x, y, size_w+1, 0, size_w, size_h);
		
	}

	@Override
	public int checkForInterception(List<TouchEvent> e) {
		if(e == null) return 0; 
		int len = e.size();
		int x; int y;
		int type; int result=0;
		for(int i = 0; i < len; i++)
		{
			x = e.get(i).x;
			y = e.get(i).y;
			type = e.get(i).type;
			Log.d("MyTag", "WeAre");
			if( x > this.x && x < (this.x + size_w) && y > this.y && y < (this.y + size_h) )
			{
				Log.d("MyTag", "WeAre");
				 if( type == TouchEvent.TOUCH_UP && act_pointer == e.get(i).pointer)
				{
						isPressed = false;
						result = State.RELEASED;
						act_pointer = -1;
				}
				else if( type == TouchEvent.TOUCH_DOWN )// if down 
				{
					isPressed = true;
					result =  State.PRESSED;
					act_pointer = e.get(i).pointer;
				}
				
			}
			else if(  act_pointer == e.get(i).pointer && type == TouchEvent.TOUCH_DRAGGED)
			{
				isPressed = false;
				result = State.RELEASED;
				act_pointer = -1;
			}
		}	
		
	    return result;	
	}


}
