package com.the_jester41.GameFramework.framework.impl;

import com.the_jester41.GameFramework.framework.Audio;
import com.the_jester41.GameFramework.framework.FileIO;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input;
import com.the_jester41.GameFramework.framework.Screen;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public abstract class AndroidGame extends Activity implements Game{
	AndroidFastRenderView renderView;
	Graphics graphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	WakeLock wakeLock;
	
	@SuppressWarnings("deprecation")
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
						     WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		boolean isLandscape = (getResources().getConfiguration().orientation 
							== Configuration.ORIENTATION_LANDSCAPE);
		int frameBufferWidth   = isLandscape? 480 : 320;
		int frameBufferHeight  = isLandscape? 320 : 480;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, 
												Config.RGB_565);
		
		
		float scaleX = (float)frameBufferWidth/getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float)frameBufferHeight/getWindowManager().getDefaultDisplay().getHeight();
		renderView = new AndroidFastRenderView(this, frameBuffer);
		graphics = new AndroidGraphics(this.getAssets(), frameBuffer);
		fileIO = new AndroidFileIO(this.getAssets());
		audio = new AndroidAudio(this);
		input = new AndroidInput(this, renderView, scaleX, scaleY);
		screen = getStartScreen();
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "GLGame");
		setContentView(renderView);
	}
	
	
	@Override
	public void onResume() {

		super.onResume();
		screen.resume();
		renderView.resume();
		wakeLock.acquire();
		
	}
	
	@Override
	public void onPause()
	{
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		if(isFinishing())
			screen.dispose();
	}
	
	@Override
	public FileIO getFileIO()
	{ return fileIO; }
	
	@Override
	public Audio getAudio()
	{ return audio; }
	
	@Override
	public Graphics getGraphics()
	{ return graphics; }
	
	@Override
	public Input getInput()
	{ return input; }
	
	@Override
	public void setScreen(Screen screen) {
		if(screen == null)
			throw new IllegalArgumentException("Screen must be not null");
		this.screen.pause();
		this.screen.dispose();
		this.screen = screen;
		screen.resume();
		screen.update(0);
		
	}
	
	public Screen getCurrentScreen()
	{
		return screen;
	}
	
}
