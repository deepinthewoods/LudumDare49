package ninja.trek;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Entity {
    public FixtureDef fixDef;
    public BodyDef bodyDef;
    public Body body;
    public boolean remove;
    public Vector2 position = new Vector2();
    public float time, animTime;


    public void update(float delta, World world) {
        time += delta;
        position.set(body.getPosition());
    }

    public void addToWorld(World world) {
        remove = false;
        time = 0;
        animTime = 0;
        body = world.createBody(bodyDef);
        body.createFixture(fixDef);
    }

    public void onRemove() {
        body = null;

    }

    public void render(SpriteBatch batch) {

    }
}
