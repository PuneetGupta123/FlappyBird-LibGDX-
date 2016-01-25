package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Created by Abhishek on 09-01-2016.
 */
public class assets {
     //Disposables
    public static TextureAtlas atlas;
    public static SpriteBatch batch;

    // non-disposables
    public static TextureRegion bird;
    public static TextureRegion bird2;
    public static TextureRegion bird3;
    public static TextureRegion deadBird;
    public static TextureRegion background;
    public static TextureRegion ground;
    public static TextureRegion pipe;
    public static TextureRegion title;
    public static TextureRegion playUp;
    public static TextureRegion playDown;
    public static BitmapFont fontmedium;

    // Animation
    public static Animation birdAnimation;

    public static void load()
    {

        atlas=new TextureAtlas("pack.atlas");
        batch=new SpriteBatch();

        fontmedium=new BitmapFont(Gdx.files.internal("font/font.fnt"),Gdx.files.internal("font/font_0.png"),false);
        bird=atlas.findRegion("bird");
        bird2=atlas.findRegion("bird2");
        bird3=atlas.findRegion("bird3");
        deadBird=atlas.findRegion("peng-dead");
        background=atlas.findRegion("bg");
        ground=atlas.findRegion("ground");
        pipe=atlas.findRegion("pipe");
        birdAnimation= new Animation(0.2f,bird,bird2,bird3);
        birdAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        title=atlas.findRegion("title");
        playUp=atlas.findRegion("play_up");
        playDown=atlas.findRegion("play_down");
    }

    public static void dispose()
    {
        if(atlas!=null)
        {
            atlas.dispose();
        }
        if(batch!=null)
        {
            batch.dispose();
        }
        fontmedium.dispose();


    }
}
