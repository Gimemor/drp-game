package com.the_jester41.GameFramework.framework.impl;

import java.util.ArrayList;
import java.util.List;

import com.the_jester41.GameFramework.framework.impl.Pool.PoolObjectFactory;
import com.the_jester41.GameFramework.framework.Input.KeyEvent;

import android.view.View;
import android.view.View.OnKeyListener;

public class KeyboardHandler implements OnKeyListener {
	boolean[] pressedKeys = new boolean[128];
	Pool<KeyEvent> keyEventPool;
	List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
	List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();
	
	public KeyboardHandler(View view)
	{
		PoolObjectFactory<KeyEvent> factory = new PoolObjectFactory<KeyEvent>()
		{

			@Override
			public KeyEvent createObject() {
				return new KeyEvent();
			}
		};
		
		keyEventPool = new Pool<KeyEvent>(factory, 100);
		view.setOnKeyListener(this);
		view.requestFocus();
	}
	
	@Override
	public boolean onKey(View v, int keyCode, android.view.KeyEvent event)
	{
		if(event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE)
			return false;
		synchronized(this) {
			KeyEvent keyEvent = keyEventPool.newObject();
			keyEvent.keyCode = event.getKeyCode();
			keyEvent.keyChar = (char)event.getUnicodeChar();
			boolean b=false;
			if(event.getAction()==android.view.KeyEvent.ACTION_DOWN)
				b=true;
			keyEvent.type = (b)? KeyEvent.KEY_DOWN : KeyEvent.KEY_UP;
			if(keyCode>0 && keyCode<127)
					pressedKeys[keyCode]=b;
			keyEventsBuffer.add(keyEvent);
		}
		return true;
	}
	

	
		
	public boolean isKeyPressed(int code)
	{
		if(code<0 || code>127)
			return false;
		return pressedKeys[code];
	}
	
	public synchronized List<KeyEvent> getKeyEvents()
	{
		int len = keyEvents.size();
		for(int i = 0; i < len; i++)
			keyEventPool.free(keyEvents.get(i));
		keyEvents.clear();
		keyEvents.addAll(keyEventsBuffer);  
		keyEventsBuffer.clear();
		return keyEvents;
	}
}
