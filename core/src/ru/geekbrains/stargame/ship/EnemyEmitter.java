package ru.geekbrains.stargame.ship;


import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import ru.geekbrains.stargame.engine.math.Rect;
import ru.geekbrains.stargame.engine.math.Rnd;
import ru.geekbrains.stargame.engine.utils.Regions;

public class EnemyEmitter {

    private static final float ENEMY_SMALL_HEIGHT_1 = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT_1 = 0.015f;
    private static final float ENEMY_SMALL_BULLET_VY_1 = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE_1 = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL_1 = 3f;
    private static final int ENEMY_SMALL_HP_1 = 1;

    private static final float ENEMY_SMALL_HEIGHT_2 = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT_2 = 0.015f;
    private static final float ENEMY_SMALL_BULLET_VY_2 = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE_2 = 1;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL_2 = 3f;
    private static final int ENEMY_SMALL_HP_2 = 1;

    private static final float ENEMY_SMALL_HEIGHT_3 = 0.1f;
    private static final float ENEMY_SMALL_BULLET_HEIGHT_3 = 0.018f;
    private static final float ENEMY_SMALL_BULLET_VY_3 = -0.3f;
    private static final int ENEMY_SMALL_BULLET_DAMAGE_3 = 2;
    private static final float ENEMY_SMALL_RELOAD_INTERVAL_3 = 3f;
    private static final int ENEMY_SMALL_HP_3 = 2;

    private static final float ENEMY_MIDDLE_HEIGHT_1 = 0.1f;
    private static final float ENEMY_MIDDLE_BULLET_HEIGHT_1 = 0.02f;
    private static final float ENEMY_MIDDLE_BULLET_VY_1 = -0.25f;
    private static final int ENEMY_MIDDLE_BULLET_DAMAGE_1 = 3;
    private static final float ENEMY_MIDDLE_RELOAD_INTERVAL_1 = 4f;
    private static final int ENEMY_MIDDLE_HP_1 = 3;

    private static final float ENEMY_MIDDLE_HEIGHT_2 = 0.1f;
    private static final float ENEMY_MIDDLE_BULLET_HEIGHT_2 = 0.025f;
    private static final float ENEMY_MIDDLE_BULLET_VY_2 = -0.25f;
    private static final int ENEMY_MIDDLE_BULLET_DAMAGE_2 = 5;
    private static final float ENEMY_MIDDLE_RELOAD_INTERVAL_2 = 4f;
    private static final int ENEMY_MIDDLE_HP_2 = 5;

    private static final float ENEMY_BIG_HEIGHT_1 = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT_1 = 0.035f;
    private static final float ENEMY_BIG_BULLET_VY_1 = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE_1 = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL_1 = 4f;
    private static final int ENEMY_BIG_HP_1 = 15;

    private static final float ENEMY_BIG_HEIGHT_2 = 0.2f;
    private static final float ENEMY_BIG_BULLET_HEIGHT_2 = 0.04f;
    private static final float ENEMY_BIG_BULLET_VY_2 = -0.3f;
    private static final int ENEMY_BIG_BULLET_DAMAGE_2 = 10;
    private static final float ENEMY_BIG_RELOAD_INTERVAL_2 = 4f;
    private static final int ENEMY_BIG_HP_2 = 20;

    private final Vector2 enemySmallV_1 = new Vector2(0f, -0.2f);
    private final Vector2 enemySmallV_2 = new Vector2(0f, -0.2f);
    private final Vector2 enemySmallV_3 = new Vector2(0f, -0.2f);

    private final Vector2 enemyMiddleV_1 = new Vector2(0f, -0.03f);
    private final Vector2 enemyMiddleV_2 = new Vector2(0f, -0.03f);

    private final Vector2 enemyBigV_1 = new Vector2(0f, -0.005f);
    private final Vector2 enemyBigV_2 = new Vector2(0f, -0.005f);

    private float generateTimer;
    private float generateInterval = 4f;

    private final EnemyPool enemyPool;
    private Rect worldBounds;

    private final TextureRegion[] enemySmallRegion_1;
    private final TextureRegion[] enemySmallRegion_2;
    private final TextureRegion[] enemySmallRegion_3;

    private final TextureRegion[] enemyMiddleRegion_1;
    private final TextureRegion[] enemyMiddleRegion_2;

    private final TextureRegion[] enemyBigRegion_1;
    private final TextureRegion[] enemyBigRegion_2;

    private TextureRegion bulletRegion;

    private int stage;

    public EnemyEmitter(EnemyPool enemyPool, Rect worldBounds, TextureAtlas atlas) {
        this.enemyPool = enemyPool;
        this.worldBounds = worldBounds;

        enemySmallRegion_1 = Regions.split(atlas.findRegion("enemy3"), 1, 2, 2);
        enemySmallRegion_2 = Regions.split(atlas.findRegion("enemy4"), 1, 2, 2);
        enemySmallRegion_3 = Regions.split(atlas.findRegion("enemy5"), 1, 2, 2);

        enemyMiddleRegion_1 = Regions.split(atlas.findRegion("enemy6"), 1, 2, 2);
        enemyMiddleRegion_2 = Regions.split(atlas.findRegion("enemy7"), 1, 2, 2);

        enemyBigRegion_1 = Regions.split(atlas.findRegion("enemy8"), 1, 2, 2);
        enemyBigRegion_2 = Regions.split(atlas.findRegion("enemy9"), 1, 2, 2);

        bulletRegion = atlas.findRegion("redBullet0");
    }

    public void setToNewGame() {
        stage = 1;
    }

    public void generateEnemy(float delta, int frags) {
        stage = frags / 10 + 1;
        generateTimer += delta;
        if (generateInterval <= generateTimer) {
            generateTimer = 0f;
            EnemyShip enemy = enemyPool.obtain();

            float type = (float) Math.random();
            if (type < 0.3f) {
                enemy.set(
                        enemySmallRegion_1,
                        enemySmallV_1,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT_1,
                        ENEMY_SMALL_BULLET_VY_1,
                        ENEMY_SMALL_BULLET_DAMAGE_1 * stage,
                        ENEMY_SMALL_RELOAD_INTERVAL_1,
                        ENEMY_SMALL_HEIGHT_1,
                        ENEMY_SMALL_HP_1 * stage
                );
            } else if (type < 0.5f) {
                enemy.set(
                        enemySmallRegion_2,
                        enemySmallV_2,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT_2,
                        ENEMY_SMALL_BULLET_VY_2,
                        ENEMY_SMALL_BULLET_DAMAGE_2 * stage,
                        ENEMY_SMALL_RELOAD_INTERVAL_2,
                        ENEMY_SMALL_HEIGHT_2,
                        ENEMY_SMALL_HP_2 * stage
                );
            } else if (type < 0.7f) {
                enemy.set(
                        enemySmallRegion_3,
                        enemySmallV_3,
                        bulletRegion,
                        ENEMY_SMALL_BULLET_HEIGHT_3,
                        ENEMY_SMALL_BULLET_VY_3,
                        ENEMY_SMALL_BULLET_DAMAGE_3 * stage,
                        ENEMY_SMALL_RELOAD_INTERVAL_3,
                        ENEMY_SMALL_HEIGHT_3,
                        ENEMY_SMALL_HP_3 * stage
                );
            } else if (type < 0.8f) {
                enemy.set(
                        enemyMiddleRegion_1,
                        enemyMiddleV_1,
                        bulletRegion,
                        ENEMY_MIDDLE_BULLET_HEIGHT_1,
                        ENEMY_MIDDLE_BULLET_VY_1,
                        ENEMY_MIDDLE_BULLET_DAMAGE_1 * stage,
                        ENEMY_MIDDLE_RELOAD_INTERVAL_1,
                        ENEMY_MIDDLE_HEIGHT_1,
                        ENEMY_MIDDLE_HP_1 * stage
                );
            } else if (type < 0.9f) {
                enemy.set(
                        enemyMiddleRegion_2,
                        enemyMiddleV_2,
                        bulletRegion,
                        ENEMY_MIDDLE_BULLET_HEIGHT_2,
                        ENEMY_MIDDLE_BULLET_VY_2,
                        ENEMY_MIDDLE_BULLET_DAMAGE_2 * stage,
                        ENEMY_MIDDLE_RELOAD_INTERVAL_2,
                        ENEMY_MIDDLE_HEIGHT_2,
                        ENEMY_MIDDLE_HP_2 * stage
                );
            } else if (type < 0.95f) {
                enemy.set(
                        enemyBigRegion_1,
                        enemyBigV_1,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT_1,
                        ENEMY_BIG_BULLET_VY_1,
                        ENEMY_BIG_BULLET_DAMAGE_1 * stage,
                        ENEMY_BIG_RELOAD_INTERVAL_1,
                        ENEMY_BIG_HEIGHT_1,
                        ENEMY_BIG_HP_1 * stage
                );
            } else {
                enemy.set(
                        enemyBigRegion_2,
                        enemyBigV_2,
                        bulletRegion,
                        ENEMY_BIG_BULLET_HEIGHT_2,
                        ENEMY_BIG_BULLET_VY_2,
                        ENEMY_BIG_BULLET_DAMAGE_2 * stage,
                        ENEMY_BIG_RELOAD_INTERVAL_2,
                        ENEMY_BIG_HEIGHT_2,
                        ENEMY_BIG_HP_2 * stage
                );
            }
            enemy.pos.x = Rnd.nextFloat(worldBounds.getLeft() + enemy.getHalfWidth(), worldBounds.getRight() - enemy.getHalfWidth());
            enemy.setBottom(worldBounds.getTop());
        }
    }

    public int getStage() {
        return stage;
    }
}
