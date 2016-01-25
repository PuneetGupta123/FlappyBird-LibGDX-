package com.mygdx.game;

import com.badlogic.gdx.Game;


public class flappy_bird extends Game {
    public static final int Width = 300;                              //height and width of the screen
    public static final int Height = 480;
    public static final int GroundLevel = 24;


	
	@Override
	public void create () {

        assets.load();

//        setScreen(new GameplayScreen(this));          //set screen works when game class is extended
        setScreen(new MainMenuScreen(this));
    }

	@Override
	public void dispose () {
		assets.dispose();

	}


}
