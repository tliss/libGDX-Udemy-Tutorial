package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Created by samor on 12/5/2017.
 */

public class Player extends Sprite {

    public Player(String name, float x,  float y) {
        super(new Texture(name));
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
    }

}
