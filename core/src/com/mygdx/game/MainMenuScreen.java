package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * Created by Abhishek on 24-01-2016.
 */
public class MainMenuScreen extends ScreenAdapter {

    private flappy_bird game;
    private Stage stage;
    private Image title;
    private Image background;
    private Button playButton;       //Using button instead of image button because we can chnage the height and width of the button
                                     //but cannot do the same using imagebutton playbutton.setwidth(),playbutton.setheight()
    public MainMenuScreen(final flappy_bird game)

    {
        this.game=game;
        stage=new Stage(new StretchViewport(flappy_bird.Width,flappy_bird.Height));
        title=new Image(assets.title);
        title.setPosition(flappy_bird.Width/2,flappy_bird.Height-50f, Align.top);
        title.setOrigin(Align.center);
        title.addAction(Actions.forever(Actions.sequence(Actions.scaleTo(0.7f,0.7f,1f, Interpolation.circle),Actions.scaleTo(1,1,1,Interpolation.circle))));
        background=new Image(assets.background);
        background.setPosition(0,0);
        initPlayButton();


        stage.addActor(background);
        stage.addActor(title);
        stage.addActor(playButton);

        Gdx.input.setInputProcessor(stage);

    }

    private void initPlayButton() {
        playButton=new Button(new TextureRegionDrawable(assets.playUp),new TextureRegionDrawable(assets.playDown));
       // playButton.setWidth(100);
       // playButton.setHeight(50);
        playButton.setPosition(flappy_bird.Width/2,flappy_bird.Height*0.2f, Align.center);

        playButton.addListener(new ClickListener() {

                                   @Override
                                   public void clicked(InputEvent event, float x, float y) {
                                       game.setScreen(new GameplayScreen(game));
                                   }
                               });
    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

    }

    @Override
    public void show() {
        playButton.clearActions();
        playButton.addAction(Actions.moveBy(0,-100f));
        playButton.addAction(Actions.moveBy(0,100f,1f,Interpolation.pow2));
    }
}
