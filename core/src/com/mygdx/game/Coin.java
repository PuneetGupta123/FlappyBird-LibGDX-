package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by Abhishek on 23-01-2016.
 */
public class Coin extends Actor {

    public static final int width=32;
    public static final int height=32;
    private Vector2 vel;
    private TextureRegion region;
    private Rectangle bounds;

    public Coin()
    {
        setWidth(width);
        setHeight(height);
        vel=new Vector2();
        vel.x=-(Pipe.Move_velocity);
        bounds=new Rectangle(getX(),getY(),width,height);

        region=new TextureRegion(assets.bird);
    }

    @Override
    public void act(float delta) {
        setX(getX()+vel.x*delta);
        bounds.setPosition(getX(),getY());

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    public Vector2 getVel() {
        return vel;
    }

    public void setVel(float X,float Y) {
        this.vel.x = X;
        this.vel.y=Y;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

}

