package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by samor on 12/5/2017.
 */

public class Player extends Sprite {

    private World world;
    private Body body;

    public Player(World world, String name, float x,  float y) {
        super(new Texture(name));
        this.world = world;
        setPosition(x - getWidth() / 2, y - getHeight() / 2);
        createBody();
    }

    void createBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX(), getY());
        body = world.createBody(bodyDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getWidth() / 2, getHeight() / 2);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        Fixture fixture = body.createFixture(fixtureDef);

        shape.dispose();
    }

    public void updatePlayer(){
        this.setPosition(body.getPosition().x, body.getPosition().y);
    }
}
