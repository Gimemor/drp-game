package com.the_jester41.GameTest;

import java.util.List;


import com.the_jester41.GameFramework.framework.Button;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;
import com.the_jester41.GameFramework.framework.Screen;
import com.the_jester41.GameFramework.framework.impl.ASimpleButton;

public class MainMenu extends Screen{
	ASimpleButton play, exit;
	Graphics g;
	Input inp;
	public Assets assets;
	GameScreen gs;
	Banner hlp;
	public MainMenu(Game game, Assets assets) {
		super(game);
		g = game.getGraphics();
		int x = g.getWidth()/2 - assets.buttonPlay.getWidth()/4;
		play = new ASimpleButton(x, 4*assets.buttonPlay.getHeight(), assets.buttonPlay, 
								assets.buttonPlay.getWidth()/2, assets.buttonPlay.getHeight(), game);
		exit = new ASimpleButton(x, 5*assets.buttonEnd.getHeight(), assets.buttonEnd, 
				assets.buttonEnd.getWidth()/2, assets.buttonEnd.getHeight(), game);
		inp = game.getInput();
		this.assets = assets;
		gs = new GameScreen( game, assets, this);
		hlp = new Banner(game, "help1.png", this);
	}

	@Override
	public void update(float deltaTime) {
		List<TouchEvent> te = inp.getTouchEvents();
		
		
		if(exit.checkForInterception(te) == Button.State.RELEASED)
		{
			System.exit(0);
		}
		
		
		if(play.checkForInterception(te) == Button.State.RELEASED)
		{
			game.setScreen(gs);
		}

		
		
	}

	@Override
	public void present(float deltaTime) {
		g.drawPixmap(assets.menuBackground, 0, 0);
		play.drawButton();
		exit.drawButton();
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
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
