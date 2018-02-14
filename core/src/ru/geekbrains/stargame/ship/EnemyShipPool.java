package ru.geekbrains.stargame.ship;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.bullet.BulletPool;
import ru.geekbrains.stargame.engine.pool.SpritesPool;

public class EnemyShipPool extends SpritesPool<EnemyShip> {

    private final TextureRegion enemyShipRegion;
    private BulletPool enemyBulletPool;
    private TextureRegion bulletRegion;
    private Sound enemySoundShoot;

    public EnemyShipPool(TextureRegion enemyShipRegion, TextureRegion bulletRegion, BulletPool bulletPool, Sound soundShoot) {
        this.enemyShipRegion = enemyShipRegion;
        this.enemyBulletPool = bulletPool;
        this.enemySoundShoot = soundShoot;
        this.bulletRegion = bulletRegion;
    }

    @Override
    protected EnemyShip newObject() {
        return new EnemyShip(enemyShipRegion, enemyBulletPool, enemySoundShoot, bulletRegion);
    }

    @Override
    protected void debugLog() {
        System.out.println("EnemyShipPool change active/free:" + activeObjects.size() + "/" + freeObjects.size());
    }
}
