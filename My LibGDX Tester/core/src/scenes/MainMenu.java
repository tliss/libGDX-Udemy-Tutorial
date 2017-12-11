package scenes;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.samort7.mylibgdxtester.GameMain;

import clouds.Cloud;
import helpers.GameInfo;
import player.Player;

public class MainMenu implements Screen, ContactListener {

    private GameMain game;
    private Texture bg;
    private Player player;
    private World world;
    private OrthographicCamera box2DCamera;
    private Box2DDebugRenderer debugRenderer;

    //Constructor for thsi class
    public MainMenu(GameMain game) {
        this.game = game;
        box2DCamera = new OrthographicCamera();

        //setTotOrtho call tells the camera how big a window we're using to look at the world
        box2DCamera.setToOrtho(
                false,
                GameInfo.WIDTH/GameInfo.PPM,
                GameInfo.HEIGHT/GameInfo.PPM);
        //set camera to be in the center
        box2DCamera.position.set(
                GameInfo.WIDTH/2f,
                GameInfo.HEIGHT/2f,
                0);

        debugRenderer = new Box2DDebugRenderer();

        world = new World(new Vector2(
                0,
                -9.8f),
                true);

        world.setContactListener(this);

        bg = new Texture("Game BG.png");

        player = new Player(world,
                "Player 1.png",
                GameInfo.WIDTH/2,
                GameInfo.HEIGHT/2 + 250); //The 250 just starts him a little further up

        Cloud c = new Cloud(world);
    }

    @Override
    public void show() {

    }

    //dt = deltaTime
    void update(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            player.getBody().applyForce(
                    new Vector2(-5f,0),
                    player.getBody().getWorldCenter(),
                    true);
        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            player.getBody().applyForce(
                    new Vector2(5f,0),
                    player.getBody().getWorldCenter(),
                    true);
        }
    }

    @Override
    public void render(float delta) {

        update(delta);

        player.updatePlayer();

        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.getBatch().begin();
        game.getBatch().draw(bg, 0, 0);
        game.getBatch().draw(
                player,
                player.getX(),
                player.getY() - player.getHeight()/2
        );
        game.getBatch().end();

        debugRenderer.render(world, box2DCamera.combined);

        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        bg.dispose();
        player.getTexture().dispose();
    }

    @Override
    public void beginContact(Contact contact) {

        Fixture firstBody, secondBody;

        //This code makes sure first body is always the player
        if(contact.getFixtureA().getUserData() == "Player"){
            firstBody = contact.getFixtureA();
            secondBody = contact.getFixtureB();
        } else {
            firstBody = contact.getFixtureB();
            secondBody = contact.getFixtureA();
        }

        //This will always return "Player"
        System.out.println("The name of the first body is " + firstBody.getUserData());
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
