package ru.geekbrains.stargame;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import ru.geekbrains.stargame.engine.Sprite;
import ru.geekbrains.stargame.engine.math.Rect;

public class ExitButton extends Sprite{
    public ExitButton(TextureRegion region) {
        super(region);
    }

    @Override
    public void resize(Rect worldBounds) {
        setHeightProportion(worldBounds.getHeight());
        pos.set(worldBounds.getHalfHeight() * 0.35f, -worldBounds.getHalfWidth() * 0.25f);
        scale = 0.1f;
    }
}
