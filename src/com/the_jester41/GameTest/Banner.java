package com.the_jester41.GameTest;


import com.the_jester41.GameFramework.framework.Button;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Graphics.PixmapFormat;
import com.the_jester41.GameFramework.framework.Input;
import com.the_jester41.GameFramework.framework.Pixmap;
import com.the_jester41.GameFramework.framework.Screen;
import com.the_jester41.GameFramework.framework.impl.ASimpleButton;

public class Banner extends Screen{
	Graphics g;
	Input inp;
	Pixmap bmp;
	MainMenu m;
	Button button;
	public Banner(Game game, String path, MainMenu m) {
		super(game);
		g=game.getGraphics();
		inp=game.getInput();
		bmp=g.newPixmap(path, PixmapFormat.RGB565);
		this.m = m;
		button = new ASimpleButton( 146, 400, m.assets.button, m.assets.button.getWidth()/2,  
									m.assets.button.getHeight(), game );
	}

	@Override
	public void update(float deltaTime) {
	 
	  if( button.checkForInterception(inp.getTouchEvents()) == Button.State.RELEASED)
	  {
		   game.setScreen(m);
	  }
	}
	
	public Banner setImg(String path)
	{
		bmp.dispose();
		bmp=g.newPixmap(path, PixmapFormat.RGB565);
		return this;
	}

	@Override
	public void present(float deltaTime) {
		g.drawPixmap(bmp, 0, 0);
		button.drawButton();
	}

	@Override
	public void pause() {
		
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
