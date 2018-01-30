package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Base2DScreen;

public class MenuScreen extends Base2DScreen {
    private SpriteBatch batch;
    private Texture background, txStarShip;
    private Sprite spStarShip;
    private Vector2 v0, v1, vResult, vNorm;
    private float destX, destY;
    private final float VELOCITY = 3.0f;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        batch = new SpriteBatch();
        background = new Texture("bg.png");
        txStarShip = new Texture("ship_128x128.png");
        spStarShip = new Sprite(txStarShip);
        spStarShip.setPosition(100, 100);
        v0 = new Vector2(spStarShip.getX() + (txStarShip.getWidth() / 2),
                spStarShip.getY() + (txStarShip.getWidth() / 2));
        v1 = new Vector2(0, 0);
        vResult = new Vector2(0, 0);
        vNorm = new Vector2(0, 0);
        System.out.println("v0 = " + v0.x + " " + v0.y);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(background, 0, 0);

        // Move spaceship
        if ((spStarShip.getX() >= destX - VELOCITY) && (spStarShip.getX() <= destX + VELOCITY) ||
                (spStarShip.getY() >= destY - VELOCITY) && (spStarShip.getY() <= destY - VELOCITY)) {
            spStarShip.draw(batch);
        } else {
            // Тут можно было реализовать через скаляр, но мне показалось так нагляднее.
            spStarShip.setPosition(spStarShip.getX() + VELOCITY * vNorm.x,
                    spStarShip.getY() + VELOCITY * vNorm.y);
            spStarShip.draw(batch);
        }
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        txStarShip.dispose();
        super.dispose();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        // Calculate coordinates, vectors.
        v0.x = spStarShip.getX() + (txStarShip.getWidth() / 2);
        v0.y = spStarShip.getY() + (txStarShip.getWidth() / 2);
        destX = screenX - (txStarShip.getWidth() / 2);
        destY = (Gdx.graphics.getHeight() - screenY) - (txStarShip.getHeight() / 2);
        v1.x = destX + (txStarShip.getWidth() / 2);
        v1.y = destY + (txStarShip.getWidth() / 2);
        vResult = v1.cpy().sub(v0);
        vNorm = vResult.cpy().nor();

        // Just logging
        System.out.println("v1 = " + v1.x + " " + v1.y);
        System.out.println("vR = " + vResult.x + " " + vResult.y);
        System.out.println("vN = " + vNorm.x + " " + vNorm.y);
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        // что-то тут стоит прописать, но никак не придумаю что, пока оставил пусто.
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
