package com.the_jester41.GameFramework.framework;

import java.util.List;

import com.the_jester41.GameFramework.framework.Input.TouchEvent;

public interface Button {
	// Button's image is atlas with two horizontal-oriented tiles
	// 1st - image of released button, and second for pressed button.
	public static class State
	{
	    public static int PRESSED  = 1;
		public static int RELEASED = 2;
	}

	public boolean isPressed();
	public void setImage(Pixmap img, int size_w, int size_h);
	public void setCord(int x, int y);
	public void drawButton();
	public int checkForInterception(List<TouchEvent> e);
}
