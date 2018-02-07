package ru.geekbrains.stargame.screen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.Background;
import ru.geekbrains.stargame.engine.Base2DScreen;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.ships.MainShip;
import ru.geekbrains.stargame.star.Star;

public class GameScreen extends Base2DScreen {

    private static final float MAIN_SHIP_HEIGHT = 0.15f;
    private static final int NUMBER_OF_STARS = 1000;

    private Texture backgroundTexture;
    private Background background;
    private TextureAtlas atlas, atlasGame;

    private Star[] star = new Star[NUMBER_OF_STARS];
    private MainShip mainShip;


    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void show() {
        super.show();
        backgroundTexture = new Texture("bg.png");
        background = new Background(new TextureRegion(backgroundTexture));
        atlas = new TextureAtlas("menuAtlas.tpack");
        atlasGame = new TextureAtlas("mainAtlas.tpack");
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            star[i] = new Star(atlas, Rnd.nextFloat(-0.005f, 0.005f), Rnd.nextFloat(-0.3f, -0.1f), 0.005f);
        }
        mainShip = new MainShip(atlasGame);
        mainShip.setHeightProportion(MAIN_SHIP_HEIGHT);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        update(delta);
        draw();
    }

    public void update(float delta) {
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            star[i].update(delta);
        }
    }

    public void draw() {
        Gdx.gl.glClearColor(0.7f, 0.3f, 0.7f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        background.draw(batch);
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            star[i].draw(batch);
        }
        mainShip.draw(batch);
        batch.end();
    }

    @Override
    protected void resize(Rect worldBounds) {
        super.resize(worldBounds);
        background.resize(worldBounds);
        for (int i = 0; i < NUMBER_OF_STARS; i++) {
            star[i].resize(worldBounds);
        }
        mainShip.resize(worldBounds);
    }

    @Override
    public void dispose() {
        backgroundTexture.dispose();
        atlas.dispose();
        super.dispose();
    }

    @Override
    protected void touchUp(Vector2 touch, int pointer) {
        super.touchUp(touch, pointer);
    }

    @Override
    protected void touchDown(Vector2 touch, int pointer) {
        super.touchDown(touch, pointer);
        mainShip.pos.set(touch);
    }

    @Override
    protected void touchDragged(Vector2 touch, int pointer) {
        super.touchDragged(touch, pointer);
        mainShip.pos.set(touch.x, touch.y);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (mainShip.pos.x > -0.3f){
                mainShip.pos.set(mainShip.pos.x - 0.01f, mainShip.pos.y);
            } else {
                mainShip.pos.set(mainShip.pos.x, mainShip.pos.y);
            }

        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (mainShip.pos.x < 0.3f) {
                mainShip.pos.set(mainShip.pos.x + 0.01f, mainShip.pos.y);
            } else {
                mainShip.pos.set(mainShip.pos.x, mainShip.pos.y);
            }
        }
        return false;
    }

}
