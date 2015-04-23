package com.the_jester41.GameFramework.framework.impl;

import android.view.View.OnTouchListener;
import java.util.List;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener{
	public boolean isTouchDown(int pointer);
	public int getTouchX(int pointer);
	public int getTouchY(int pointer);
	public List<TouchEvent> getTouchEvents();
}
