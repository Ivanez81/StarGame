package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {
    private static final int FR_COLS = 6, FR_ROWS = 5;
    Animation<TextureRegion> walkAnimation;
    Texture walkSheet;
    SpriteBatch spriteBatch;
    float stateTime;

    @Override
    public void create() {
        walkSheet = new Texture(Gdx.files.internal("sprite_animation.png"));
        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FR_COLS,
                walkSheet.getHeight() / FR_ROWS);
        TextureRegion[] walkFrames = new TextureRegion[FR_COLS * FR_ROWS];
        int index = 0;
        for (int i = 0; i < FR_ROWS; i++) {
            for (int j = 0; j < FR_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }

        walkAnimation = new Animation<TextureRegion>(0.025f, walkFrames);
        spriteBatch = new SpriteBatch();
        stateTime = 0f; // сбрасываем на 0 время...
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = walkAnimation.getKeyFrame(stateTime, true);
        spriteBatch.begin();
        spriteBatch.draw(currentFrame, 50, 50);
        spriteBatch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        walkSheet.dispose();
    }
}
