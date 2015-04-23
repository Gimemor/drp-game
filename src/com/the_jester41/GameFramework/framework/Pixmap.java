package com.the_jester41.GameFramework.framework;

import com.the_jester41.GameFramework.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();
	public int getHeight();
	public PixmapFormat getFormat();
	public void dispose();
}
