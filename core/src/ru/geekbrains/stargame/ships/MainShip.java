package ru.geekbrains.stargame.ships;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

public class MainShip extends Sprite{

    public MainShip(TextureAtlas atlas) {
        super(atlas.findRegion("main_ship"));
    }

    @Override
    public void resize(Rect worldBounds) {
        setBottom(worldBounds.getBottom());
    }


}
