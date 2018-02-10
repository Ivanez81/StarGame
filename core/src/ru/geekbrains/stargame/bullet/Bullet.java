package ru.geekbrains.stargame.bullet;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

public class Bullet extends Sprite {

    private final Vector2 v = new Vector2();

    private Rect worldBounds;

    public Bullet(TextureAtlas atlas, float vx, float vy, float height) {
        super(atlas.findRegion("bulletMainShip"));
        v.set(0, 0.1f);
        pos.set(vx, vy);
        setHeightProportion(height);
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v, delta);
    }

    @Override
    public void resize(Rect worldBounds) {
        this.worldBounds = worldBounds;
    }
}
