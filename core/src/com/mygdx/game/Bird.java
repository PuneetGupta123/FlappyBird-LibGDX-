package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.RotateToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.utils.Align;




/**
 * Created by Abhishek on 10-01-2016.
 */
public class Bird extends Actor {

    public static final int width=32;
    public static final int height=32;
    public static final float gravity=780f;
    public static final float jumpVelocity=310f;

    // Actor keeps track of position so we just need to keep track of velocity and acceleration

    private Vector2 vel;
    private Vector2 accel;
    private TextureRegion region;
    public State state;
    private Rectangle Bounds;
    private float time;

    public enum State{ alive,dying,dead}

    public Bird()
    {
        region=new TextureRegion(assets.bird);
        setWidth(width);
        setHeight(height);
        state=State.alive;
        vel=new Vector2(0,0);
        accel=new Vector2(0,-gravity);
        Bounds=new Rectangle(0,0,width,height);
        setOrigin(Align.center);
    }

    public void jump()
    {
        vel.y=jumpVelocity;
        clearActions();
        RotateToAction a1= Actions.rotateTo(45,.2f);
        DelayAction da =Actions.delay(3);
        RotateToAction a2= Actions.rotateTo(-45,.2f);
        SequenceAction requestAction =Actions.sequence(a1,a2);
        addAction(requestAction);


    }
    @Override
    public void act(float delta) {              //delta is the time passed in the last frame
        super.act(delta);                      //Updates the actor based on time. Typically this is called each frame

        time+=delta;

        switch (state){

            case alive:
                region=assets.birdAnimation.getKeyFrame(time);
                actAlive(delta);
                break;
            case dying:
                region=assets.deadBird;
               // vel.x=0;
                applyAccel(delta);
                updatePosition(delta);

                accel.y=-gravity;
               // accel=Vector2.Zero;

                if(isbelowGround())
                {
                    setY(flappy_bird.GroundLevel);
                    setState(State.dead);

                }
                break;
        }
        
        updateBounds();



    }

    private void updateBounds() {
        Bounds.x=getX();
        Bounds.y=getY();
    }

    private void actAlive(float delta) {
        applyAccel(delta);
        updatePosition(delta);

       // setRotation(MathUtils.clamp(vel.y / jumpVelocity * 45f, -90f, 45f));               //math utils generates random number

        if(isbelowGround())
        {   clearActions();
            setState(State.dead);
            setY(flappy_bird.GroundLevel);

            Died();

        }

        if(isaboveCeiling())
        {    setState(State.dying);
           // setY(flappy_bird.Height-getHeight());
            setPosition(getX(),flappy_bird.Height, Align.topLeft);
            Died();
        }
    }

    private boolean isbelowGround()
    {
       //
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

    private void applyAccel(float delta) {
        vel.add(accel.x*delta,accel.y*delta);


    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
       batch.draw(region,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    public void Died()
    {

        state=State.dying;
        vel.y=0;
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
