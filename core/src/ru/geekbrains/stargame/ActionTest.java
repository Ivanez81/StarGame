package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class ActionTest extends ApplicationAdapter {
    Stage stage;
    Texture texture;

    @Override
    public void create() {
        stage = new Stage();
        texture = new Texture(Gdx.files.internal("sun.png"), false);
        texture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        final Image img = new Image(new TextureRegion(texture));
        img.setSize(100, 100);
        img.setOrigin(50, 50);
        img.setPosition(100, 100);

        MoveToAction moveAction = new MoveToAction();
        moveAction.setPosition(100f, 100f);
        moveAction.setDuration(5f);

        img.addAction(moveAction);
//        img.addAction(Actions.rotateBy(1000, 50));

//        img.addAction(Actions.after(Actions.scaleTo(2, 2, 3)));

        stage.addActor(img);
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        texture.dispose();
    }
}
