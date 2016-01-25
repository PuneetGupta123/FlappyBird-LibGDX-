package com.mygdx.game;

import com.badlogic.gdx.math.MathUtils;

/**
 * Created by Abhishek on 19-01-2016.
 */
public class Utils {


    public static float getRandomYopenings()
    {
        return MathUtils.random(flappy_bird.Height*.15f,flappy_bird.Height*.85f);

    }
}
