package com.the_jester41.GameTest;

import java.util.ArrayList;
import java.util.List;



import com.the_jester41.GameFramework.framework.Audio;

import com.the_jester41.GameFramework.framework.Input.KeyEvent;
import com.the_jester41.GameFramework.framework.Game;
import com.the_jester41.GameFramework.framework.Graphics;
import com.the_jester41.GameFramework.framework.Input;
import com.the_jester41.GameFramework.framework.Input.TouchEvent;
import com.the_jester41.GameFramework.framework.Screen;
import com.the_jester41.GameFramework.framework.impl.Joystick;
import com.the_jester41.GameFramework.framework.impl.Pool;
import com.the_jester41.GameFramework.framework.impl.Pool.PoolObjectFactory;

public class GameScreen extends Screen{
	Assets assets;
	Graphics graphics;
	Input input;
	Joystick joystick;
	MainMenu menu;
	Car playerCar;
	float time;
	List<Car> carList;
	int bgox, bgoy;
	Audio audio;
	Pool<Car> carPool;
	int traffic[];
	float deployTime;
	
	public GameScreen(Game game, Assets assets, MainMenu m) {
		super(game);
		this.assets = assets;
		input    =  game.getInput();
		graphics = game.getGraphics();
		int x = graphics.getWidth()  - assets.joystick.getWidth()/3 - 20;
		int y = graphics.getHeight() - assets.joystick.getHeight() - 20;
		joystick = new Joystick(x, y, assets.joystick, assets.joystick.getWidth()/3 , assets.joystick.getHeight(), game);
		menu = m;
		playerCar = new Car(200, 300, 0, 0, assets.playerCar);
		carList =  new ArrayList<Car>();
		time = 0;
		bgox = bgoy = 0;
		audio = game.getAudio();
		deployTime = 0;
		PoolObjectFactory<Car> factory = new PoolObjectFactory<Car>()
				{

					@Override
					public Car createObject() {
						return new Car(40, -40, 0, 200, null);
					}
				};
		carPool = new Pool<Car>(factory, 25);
		traffic = new int[5];
	}

	
	
	@Override
	public void update(float deltaTime) {
		
		List<TouchEvent> te = input.getTouchEvents();
		joystick.checkForInterception(te);
		time += deltaTime;	
		deployTime += deltaTime;
		playerCar.x_speed =  (joystick.getPosX()*450);
		playerCar.y_speed  = -(joystick.getPosY()*450);
		if(time > 1/20)
		{
			bgoy  += 3;
			if( assets.gameBackground.getHeight()/2-bgoy <= 0 )
				bgoy = 0;
			if(playerCar.x + time*playerCar.x_speed >= 0
					&& playerCar.x + time*playerCar.x_speed <= graphics.getWidth() - playerCar.img.getWidth())
					playerCar.x += time*playerCar.x_speed;
			if(playerCar.y + time*playerCar.y_speed >= 0
					&& playerCar.y + time*playerCar.y_speed <= graphics.getHeight() - playerCar.img.getHeight())
					playerCar.y += time*playerCar.y_speed;
			  
			 
			 for(int i = 0; i < carList.size(); i++)
			 {
				 carList.get(i).x += time*carList.get(i).x_speed;
				 carList.get(i).y += time*carList.get(i).y_speed;
				 if(  carList.get(i).y > graphics.getHeight() - 2)
				 {
					 traffic[carList.get(i).row]--;
					 carPool.free(carList.remove(i));
				 }
				 else if(checkForCollision(playerCar, carList.get(i)))
				 {
					 
					 game.setScreen(menu);
				 }
				 
			 }
			 time = 0;
			 if(deployTime > 0.5)
			 {
				 deployCars();
				 deployTime = 0;
			 }
		}
		
		
		
		List<KeyEvent> ke = input.getKeyEvents();
		for(int i = 0; i < ke.size(); i++)
		{
			if( ke.get(i).keyCode == android.view.KeyEvent.KEYCODE_BACK)
			{
					game.setScreen(menu);
					break;
			}
		}
		
	}

	private void deployCars() {
			int sum = 0;
			for(int i=0; i<5; i++)
				sum+=traffic[i];
			sum = sum/5;
			if(sum<5)
			{
				int row = ((int)(Math.random()*100))%5;
				if(traffic[row] > sum ) return;
				int type = (int)(Math.random()*100)%3;
				int speed = (row+1)*100;
				if(speed<200) speed=200;
				int x=10;
				if(row>0) x=row*65;
				int y = -30;
				carList.add(carPool.newObject().set(x, y, 0, speed, assets.car[type], row));
				traffic[row]++;
			}
		
	}



	private boolean checkForCollision(Car car1, Car car2) {
		if(Math.abs(car1.x - car2.x) <= car1.img.getWidth() && Math.abs(car1.y - car2.y) <= car1.img.getHeight())
			return true;
		return false;
	}

	@Override
	public void present(float deltaTime) {
		graphics.clear(0x000000);
		graphics.drawPixmap(assets.gameBackground, 0, 0, bgox, assets.gameBackground.getHeight()/2-bgoy
							, graphics.getWidth(), graphics.getHeight());
		
		graphics.drawPixmap(playerCar.img, (int)playerCar.x, (int)playerCar.y);
		int len = carList.size();
		
		for(int i = 0; i < len; i++)
		{
			graphics.drawPixmap(carList.get(i).img, (int)carList.get(i).x, (int)carList.get(i).y);

		}
		
		joystick.drawButton();
		
	}

	@Override
	public void pause() {
		 playerCar.x = 200;
		 playerCar.y = 300;
		 playerCar.x_speed = playerCar.y_speed = 0;
		 for(int j=0; j<carList.size(); j++)
			 carPool.free(carList.remove(j));
		 for(int j=0; j<5; j++)
			 traffic[j]=0;
		
	}

	@Override
	public void resume() {
		joystick.clearPos();
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
