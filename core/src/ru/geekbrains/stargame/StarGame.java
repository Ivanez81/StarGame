package ru.geekbrains.stargame;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StarGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture txBackground, txSun, txEarth;
    Sprite sprBackground, sprSun, sprEarth;
    Animator animator;
    float centerSunX, centerSunY, centerEarthX, centerEarthY;
    float alpha;
    float kx, ky;

    @Override
    public void create() {
        batch = new SpriteBatch();
        txBackground = new Texture("Space.png");
        txSun = new Texture("Sun_128x128.png");
        txEarth = new Texture("Earth_64x64.png");
        sprBackground = new Sprite(txBackground);
        sprSun = new Sprite(txSun);
        sprEarth = new Sprite(txEarth);
        centerSunX = (Gdx.graphics.getWidth() / 2) - (txSun.getWidth() / 2);
        centerSunY = (Gdx.graphics.getHeight() / 2) - (txSun.getWidth() / 2);
        centerEarthX = (Gdx.graphics.getWidth() / 2) - (txEarth.getWidth() / 2);
        centerEarthY = (Gdx.graphics.getHeight() / 2) - (txEarth.getWidth() / 2);
        kx = 0;
        ky = 0;
        alpha = 0f;
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();

        // https://github.com/libgdx/libgdx/wiki/Spritebatch%2C-Textureregions%2C-and-Sprites#blending
        // E.g., when drawing a large background image over the whole screen,
        // a performance boost can be gained by first disabling blending:
        batch.disableBlending();
        sprBackground.draw(batch);
        batch.enableBlending();

        sprSun.setPosition(centerSunX, centerSunY);
        sprSun.draw(batch);

        alpha = alpha - 0.025f;
        if (alpha <= 360) {
            kx = (float) (150 * Math.cos(alpha));
            ky = (float) (150 * Math.sin(alpha));
        } else {
            alpha = 0;
            kx = (float) (150 * Math.cos(alpha));
            ky = (float) (150 * Math.sin(alpha));
        }
        sprEarth.setPosition(centerEarthX + kx, centerEarthY + ky);
        sprEarth.draw(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        txBackground.dispose();
        txSun.dispose();
        txEarth.dispose();
    }
}
