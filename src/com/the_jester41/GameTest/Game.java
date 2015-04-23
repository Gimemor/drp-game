package com.the_jester41.GameTest;


import com.the_jester41.GameFramework.framework.Pixmap;
import com.the_jester41.GameFramework.framework.Screen;
import com.the_jester41.GameFramework.framework.Graphics.PixmapFormat;
import com.the_jester41.GameFramework.framework.impl.AndroidGame;

public class Game extends AndroidGame{
	public Assets assets;
	protected MainMenu mainMenu;
	@Override
	public Screen getStartScreen() {
		assets = new Assets();
		assets.buttonEnd = getGraphics().newPixmap("endButton.png", PixmapFormat.ARGB4444);
		assets.buttonPlay = getGraphics().newPixmap("playButton.png", PixmapFormat.ARGB4444);
		assets.car = new Pixmap[3]; 
		assets.car[0] = getGraphics().newPixmap("car1.png", PixmapFormat.ARGB4444);
		assets.car[1] = getGraphics().newPixmap("car2.png", PixmapFormat.ARGB4444);
		assets.car[2] = getGraphics().newPixmap("car3.png", PixmapFormat.ARGB4444);
		assets.playerCar = getGraphics().newPixmap("playerCar.png", PixmapFormat.ARGB4444);
		assets.gameBackground = getGraphics().newPixmap("gameBackground.png", PixmapFormat.ARGB4444);
		assets.menuBackground = getGraphics().newPixmap("menuBackground.png", PixmapFormat.ARGB4444);
		assets.joystick = getGraphics().newPixmap("joystick.png", PixmapFormat.ARGB4444);
		assets.button  = getGraphics().newPixmap("Btemp.png", PixmapFormat.ARGB4444);
		mainMenu = new MainMenu(this, assets);
		return mainMenu;  
	}

}
