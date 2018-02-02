package ru.geekbrains.stargame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;

public class MenuScreen extends Base2DScreen {

    private Texture backgroundTexture, btnPlayTexture, btnExitTexture;
    private Background background;
    private PlayButton btnPlay;
    private ExitButton btnExit;

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));
        btnPlayTexture = new Texture("btnPlay_256.png");
        btnPlay = new PlayButton(new TextureRegion(btnPlayTexture));
        btnExitTexture = new Texture("btnClose_256.png");
        btnExit = new ExitButton(new TextureRegion(btnExitTexture));
    }

    @Override
    public void render(float delta) {
        super.render(delta);
//        Gdx.gl.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        btnPlay.draw(batch);
        btnExit.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        btnPlay.resize(worldBounds);
        btnExit.resize(worldBounds);
    }

    @Override
    public void dispose() {
        super.dispose();
        backgroundTexture.dispose();
        btnPlayTexture.dispose();
        btnExitTexture.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }
}
