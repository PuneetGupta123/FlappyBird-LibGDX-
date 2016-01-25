package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;

/**
 * Created by Abhishek on 18-01-2016.
 */
public class Pipe extends Actor {

    public static final int width=64;
    public static final int height=400;
    public static final float Move_velocity=120f;

    // Actor keeps track of position so we just need to keep track of velocity and acceleration

    private Vector2 vel;
    private TextureRegion region;
    private State state;
    private Rectangle Bounds;

    public enum State{ alive, dead};

    public Pipe()
    {
        region=new TextureRegion(assets.pipe);
        setWidth(width);
        setHeight(height);
        state=State.alive;
        vel=new Vector2(-Move_velocity,0);
        Bounds=new Rectangle(0,0,width,height);

        setOrigin(Align.center);
    }


    @Override
    public void act(float delta) {              //delta is the time passed in the last frame
        super.act(delta);                      //Updates the actor based on time. Typically this is called each frame


        switch (state){

            case alive:
                actAlive(delta);
                break;
            case dead:
                vel=Vector2.Zero;
                break;
        }

        updateBound();



    }

    private void updateBound() {
        Bounds.x=getX();
        Bounds.y=getY();
    }

    private void actAlive(float delta) {
        //applyAccel(delta);
        updatePosition(delta);


    }

    private boolean isbelowGround()
    {
        return (getY(Align.bottom) <= flappy_bird.GroundLevel);
    }

    private boolean isaboveCeiling()
    {
        return (getY(Align.top) >= flappy_bird.Height);
    }

    private void updatePosition(float delta) {

        setX(getX() + (vel.x * delta));
        setY(getY() + (vel.y * delta));
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(region,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    public Rectangle getBounds() {
        return Bounds;
    }

    public void setBounds(Rectangle bounds) {
        Bounds = bounds;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}

