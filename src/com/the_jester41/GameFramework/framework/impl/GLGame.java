package com.the_jester41.GameFramework.framework.impl;
/*
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.the_jester41.GameFramework.framework.Audio;
import com.the_jester41.GameFramework.framework.FileIO;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input;
import com.the_jester41.GameFramework.framework.Screen;

import android.app.Activity;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

public class GLGame extends Activity implements Renderer, Game {
	enum GLGameState {
		Initialized,
		Running,
		Paused,
		Finished,
		Idle
	};
	
	GLSurfaceView glView;
	GLGraphics glGraphics;
	Audio audio;
	Input input;
	FileIO fileIO;
	Screen screen;
	GLGameState state = GLGameState.Initialized;
	Object stateChanged = new Object();
	long startTime = System.nanoTime();
	WakeLock wakeLock;
	
 	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		audio = new AndroidAudio(this);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		glView = new GLSurfaceView(this);
		glView.setRenderer(this);
		setContentView(glView);
		
		glGraphics = new GLGraphics(glView);
		fileIO = new AndroidFileIO(getAssets());
		 PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		 wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "MyGame");
 	}
	
	
	@Override
	public Input getInput() {
		return input;
	}

	@Override
	public FileIO getFileIO() {
		return fileIO;
	}

	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Audio getAudio() {
		return audio;
	}

	@Override
	public void setScreen(Screen screen) {
		if(screen == null)
			throw new IllegalArgumentException("Screen must be not null!");
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
		
	}

	@Override
	public Screen getCurrentScreen() {
		return screen;
	}

	@Override
	public Screen getStartScreen() {
		return null;
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onResume()
	{
		super.onResume();
		glView.onResume();
		wakeLock.acquire();
	}
	
	@Override
	public void onSurfaceChanged(GL10 gl, EGLConfig config) {
		 
		
	}

	@Override
	public void onDrawFrame(GL10 gl) {
		 GLGameState state = null;
		 
		 synchronized(stateChanged)
		 {
			 state = this.state;
		 }
		 
		 if(state == GLGameState.Running)
		 {
			 float deltaTime = (System.nanoTime() - startTime) / 10000000000.0f;
			 startTime = System.nanoTime();
			 screen.update(deltaTime);
		 }
		 
		 // TODO: конец, с 305
		
	}

}
*/
 