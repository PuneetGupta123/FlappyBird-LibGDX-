package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;


/**
 * Created by Abhishek on 09-01-2016.
 */
public class GameplayScreen extends ScreenAdapter{

    public static final float PipeSpacing=270f;
    public static final int PipeSets=3;
    protected flappy_bird game;
    protected OrthographicCamera camera;                  //setting camera view
    private Stage gameplayStage;
    private Stage uistage;
    private Bird bird;
    private Label scoreLabel;
    private Label tapToRestart;
    private int score;

    private Pipe pipe;

    private boolean justTouched;

    private Image background;
    private Image ground;
    private State state=State.playing;
    private enum State{playing,dying,dead};


    Array<PipePair> pipePair;                           //this is libgdx array

    public GameplayScreen(flappy_bird game)               //taking flappy_bird class as parameter

    {
           this.game = game;

        pipePair=new Array<PipePair>();
            camera=new OrthographicCamera(flappy_bird.Width,flappy_bird.Height);
           // camera.setToOrtho(false,flappy_bird.Width,flappy_bird.Height);
        gameplayStage=new Stage(new StretchViewport(flappy_bird.Width,flappy_bird.Height));
        uistage=new Stage(new StretchViewport(flappy_bird.Width,flappy_bird.Height));


        bird=new Bird();
        bird.setPosition(flappy_bird.Width*.25f,flappy_bird.Height/2, Align.center);

//        Pipe toppipe=new Pipe();
//        toppipe.setRotation(180);
//        toppipe.setY(flappy_bird.Height*0.75f);
//        Pipe bottompipe=new Pipe();
//
//
//        PipePair pair =new PipePair(bottompipe,toppipe);
//        pair.initFirst();

        initFirstSetofPipe();
        initSecondSetofPipe();
        initThirdSetofPipe();

        scoreLabel=new Label("0",new Label.LabelStyle(assets.fontmedium , Color.WHITE));
        scoreLabel.setPosition(flappy_bird.Width/2,flappy_bird.Height*.9f,Align.center);
        uistage.addActor(scoreLabel);

        tapToRestart=new Label("Tap To Restart",new Label.LabelStyle(assets.fontmedium , Color.WHITE));
        tapToRestart.setPosition(flappy_bird.Width/2,flappy_bird.Height*.2f,Align.center);
        uistage.addActor(tapToRestart);

        background=new Image(assets.background);
       ground=new Image(assets.ground);
        gameplayStage.addActor(background);


//        gameplayStage.addActor(toppipe);
//        gameplayStage.addActor(bottompipe);

        addPairsToStage(gameplayStage);
        gameplayStage.addActor(ground);
        gameplayStage.addActor(bird);

        initInputProcessor();


    }

    private void addPairsToStage(Stage gameplayStage) {

      for(int i=0;i<pipePair.size;i++)
      {
            PipePair pair=pipePair.get(i);
          gameplayStage.addActor(pair.getBottompipe());
          gameplayStage.addActor(pair.getToppipe());
          gameplayStage.addActor(pair.getCoin());
      }

    }

    private void initFirstSetofPipe()

    {
        Pipe toppipe=new Pipe();
        Pipe bottompipe=new Pipe();
        toppipe.setRotation(180);
        PipePair pair =new PipePair(bottompipe,toppipe);
        pair.initFirst();
        pipePair.add(pair);




    }

    private void initSecondSetofPipe()

    {
        Pipe toppipe=new Pipe();
        Pipe bottompipe=new Pipe();
        toppipe.setRotation(180);
        PipePair pair =new PipePair(bottompipe,toppipe);
        pair.initSecond();
        pipePair.add(pair);




    }


    private void initThirdSetofPipe()

    {
        Pipe toppipe=new Pipe();
        Pipe bottompipe=new Pipe();
        toppipe.setRotation(180);
        PipePair pair =new PipePair(bottompipe,toppipe);
        pair.initThird();
        pipePair.add(pair);




    }


    private void initInputProcessor() {


            Gdx.input.setInputProcessor(new InputAdapter() {

                @Override
                public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                    // justTouched=true;

                    if(state==State.dying)
                    {
                        return true;
                    }
                    if(state==State.dead)
                    {
                        game.setScreen(new GameplayScreen(game));
                        return true;
                    }
                    bird.jump();
                    return true;
                }
            });





    }

    @Override
    public void render(float delta) {

        switch (state){

            case playing:

                updatepipe();

                gameplayStage.act();
                uistage.act();
                checkCollisions();
                gameplayStage.draw();
                uistage.draw();
                if(bird.getState()== Bird.State.dying)

                {   killthepipepair();
                    tapToRestart.addAction(Actions.moveBy(0,200f,0.5f));

                }

                break;
            case dead:


            case dying:

                if (bird.getState()== Bird.State.dead)
                {
                    state=State.dead;
                }


                gameplayStage.act();
                uistage.act();

                gameplayStage.draw();
                uistage.draw();


                break;
        }


    }

    private void checkCollisions() {

        for (int i=0;i<pipePair.size;i++)
        {    PipePair pair = pipePair.get(i);
            if(pair.getBottompipe().getBounds().overlaps(bird.getBounds()))
            {
                state=State.dying;

               bird.Died();
                killthepipepair();




            }

            if(pair.getToppipe().getBounds().overlaps(bird.getBounds()))
            {
                state=State.dying;

              bird.Died();
                killthepipepair();


            }


            if(pair.getCoin().getBounds().overlaps(bird.getBounds()))
            {
                score++;
                updateScoreLabel();
                pair.moveTheCoinOffScreen();
            }
        }


    }

    private void updateScoreLabel() {

        scoreLabel.setText(String.valueOf(score));
    }

    private void killthepipepair() {

        for (PipePair pair: pipePair)
        {
            state=State.dying;
            pair.getBottompipe().setState(Pipe.State.dead);
            pair.getToppipe().setState(Pipe.State.dead);
            pair.getCoin().setVel(0,0);
        }



    }

    private void updatepipe() {
        for(int i=0; i < pipePair.size; i++)
        {

            pipePair.get(i).reinit();
        }

    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false,width,height);
        assets.batch.setProjectionMatrix(camera.combined);
        gameplayStage.getViewport().update(width,height,true);
        uistage.getViewport().update(width,height,true);

    }

    @Override
    public void dispose() {
        gameplayStage.dispose();
        uistage.dispose();
    }

    @Override
    public void show() {
        tapToRestart.addAction(Actions.moveBy(0,-200f));
    }
}
