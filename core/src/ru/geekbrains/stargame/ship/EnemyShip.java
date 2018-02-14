package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.bullet.Bullet;
import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;

public class EnemyShip extends Ship {

    private static final float SHIP_HEIGHT = 0.15f;

    private Rect worldBounds;

    private final Vector2 v0 = new Vector2(0.0f, -0.05f);

    public EnemyShip(TextureRegion region, BulletPool bulletPool, Sound soundShoot, TextureRegion bulletRegion) {
        super(region, 1, 2, 2, soundShoot);
        setHeightProportion(SHIP_HEIGHT);
        this.bulletPool = bulletPool;
        this.bulletHeight = 0.02f;
        this.bulletRegion = bulletRegion;
        this.bulletV.set(0, -0.5f);
        this.bulletDamage = 10;
        this.reloadInterval = 1.0f;
        this.soundShoot = soundShoot;
    }

    public void set(Rect worldBounds) {
        this.pos.set(Rnd.nextFloat(worldBounds.getLeft() + this.getHalfWidth(),
                worldBounds.getRight() - this.getHalfWidth()), worldBounds.getTop());
        this.worldBounds = worldBounds;
    }

    @Override
    public void update(float delta) {
        pos.mulAdd(v0, delta);
        reloadTimer += delta;
        if (reloadTimer >= reloadInterval) {
            reloadTimer = 0f;
            shoot();
        }
        if (isOutside(worldBounds)) {
            setDestroyed(true);
        }
    }

    @Override
    public void resize(Rect worldBounds) {
        super.resize(worldBounds);
    }

    public Vector2 getV() {
        return v;
    }

    @Override
    protected void shoot() {
        Bullet bullet = bulletPool.obtain();
        bullet.set(this, bulletRegion, pos, bulletV, bulletHeight, worldBounds, bulletDamage);
        soundShoot.play();
    }
}
