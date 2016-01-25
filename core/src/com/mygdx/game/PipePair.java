package com.mygdx.game;

import com.badlogic.gdx.utils.Align;

/**
 * Created by Abhishek on 18-01-2016.
 */
public class PipePair {

    private static final float starting_x_postion=400f;
    private static final float Gap_size=130f;

    private Pipe toppipe;
    private Coin coin;
    private Pipe bottompipe;

   public PipePair(Pipe bottompipe,Pipe toppipe)
    {
           this.bottompipe=bottompipe;
           this.toppipe=toppipe;
           this.coin=new Coin();

    }

    public void reinit()
    {


        if(toppipe.getX(Align.right) <= 0) {
            float Y = Utils.getRandomYopenings();
            float xDisplacement = GameplayScreen.PipeSpacing * GameplayScreen.PipeSets;
            bottompipe.setPosition(bottompipe.getX() + xDisplacement, Y - Gap_size / 2, Align.topLeft);
            toppipe.setPosition(toppipe.getX() + xDisplacement, Y + Gap_size / 2, Align.bottomLeft);
            coin.setPosition(bottompipe.getX(Align.center),bottompipe.getY(Align.top)+Gap_size/2,Align.center);
        }


    }


    public void initFirst()
    {

        float Y=Utils.getRandomYopenings();
        bottompipe.setPosition(starting_x_postion,Y-Gap_size/2, Align.topLeft);
        toppipe.setPosition(starting_x_postion,Y+Gap_size/2,Align.bottomLeft);
        coin.setPosition(bottompipe.getX(Align.center),bottompipe.getY(Align.top)+Gap_size/2,Align.center);


    }




    public void initSecond()
    {

        float Y=Utils.getRandomYopenings();
        bottompipe.setPosition(starting_x_postion+GameplayScreen.PipeSpacing,Y-Gap_size/2, Align.topLeft);
        toppipe.setPosition(starting_x_postion+GameplayScreen.PipeSpacing,Y+Gap_size/2,Align.bottomLeft);
        coin.setPosition(bottompipe.getX(Align.center),bottompipe.getY(Align.top)+Gap_size/2,Align.center);


    }
    public void initThird()
    {

        float Y=Utils.getRandomYopenings();
        bottompipe.setPosition(starting_x_postion+GameplayScreen.PipeSpacing*2,Y-Gap_size/2, Align.topLeft);
        toppipe.setPosition(starting_x_postion+GameplayScreen.PipeSpacing*2,Y+Gap_size/2,Align.bottomLeft);
        coin.setPosition(bottompipe.getX(Align.center),bottompipe.getY(Align.top)+Gap_size/2,Align.center);

    }

    public Pipe getToppipe() {
        return toppipe;
    }

    public void setToppipe(Pipe toppipe) {
        this.toppipe = toppipe;
    }

    public Pipe getBottompipe() {
        return bottompipe;
    }

    public void setBottompipe(Pipe bottompipe) {
        this.bottompipe = bottompipe;
    }

    public Coin getCoin() {
        return coin;
    }

    public void moveTheCoinOffScreen() {
        coin.setY(coin.getY()*100000);
    }
}
