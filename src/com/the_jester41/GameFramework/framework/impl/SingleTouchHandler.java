package com.the_jester41.GameFramework.framework.impl;

import java.util.ArrayList;
import java.util.List;
import android.view.MotionEvent;
import android.view.View;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;
import com.the_jester41.GameFramework.framework.impl.TouchHandler;
import com.the_jester41.GameFramework.framework.impl.Pool.PoolObjectFactory;

public class SingleTouchHandler implements TouchHandler{
	boolean isTouched;
	int touchX;
	int touchY;
	Pool<TouchEvent> touchEventPool;
	List<TouchEvent> touchEventsBuffer = new ArrayList<TouchEvent>();
	List<TouchEvent> touchEvents = new ArrayList<TouchEvent>();
	float scaleX, scaleY;
	
	
	public SingleTouchHandler(View v, float scaleX, float scaleY)
	{
		PoolObjectFactory<TouchEvent> factory = new PoolObjectFactory<TouchEvent>()
		{
			@Override
			public TouchEvent createObject() {
				return new TouchEvent();
			}
		};
		touchEventPool = new Pool<TouchEvent>(factory, 100);
		v.setOnTouchListener(this);
		this.scaleX = scaleX;
		this.scaleY = scaleY;
	};
	
	
	
	@Override
	public synchronized boolean onTouch(View v, MotionEvent event) {
		 TouchEvent touchEvent = touchEventPool.newObject();
		switch(event.getAction())
		{
			case  MotionEvent.ACTION_DOWN:
					touchEvent.type = TouchEvent.TOUCH_DOWN;
					isTouched = true;
			case  MotionEvent.ACTION_CANCEL:
			case  MotionEvent.ACTION_UP:
					touchEvent.type = TouchEvent.TOUCH_UP;
					isTouched = false;
			case MotionEvent.ACTION_MOVE:
					touchEvent.type = TouchEvent.TOUCH_DRAGGED;
					isTouched = true;
		}
		touchEvent.x = touchX = (int)(event.getX() * scaleX);
		touchEvent.y = touchY = (int)(event.getY() * scaleY);
		touchEventsBuffer.add(touchEvent);
		return true;
	}
//! sync
	@Override
	public boolean isTouchDown(int pointer) {
		return (pointer==0)? isTouched : false;
	}

	@Override
	public int getTouchX(int pointer) {
		return touchX;
	}

	@Override
	public int getTouchY(int pointer) {
		return touchY;
	}
//////////////////////////////////////////////////////////////////////////////
	@Override
	public synchronized List<TouchEvent> getTouchEvents() {
		int len = touchEvents.size();
		for(int i = 0; i < len; i++)
			touchEventPool.free(touchEvents.get(i));
		touchEvents.clear();
		touchEvents.addAll(touchEventsBuffer);
		touchEventsBuffer.clear();
		return touchEvents;
	}
}
