package ninja.trek;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Enemy extends Entity{
    static BodyDef bD;
    static FixtureDef fD;
    static {
        bD = new BodyDef();
        fD = new FixtureDef();
        float size = 1f;
        Vector2[] shapeVectors = {
                new Vector2(0, 0),
                new Vector2(0, size),
                new Vector2(size, size),
                new Vector2(size, 0)
        };
        PolygonShape shape = new PolygonShape();
        shape.set(shapeVectors);
        fD.shape = shape;
        bD.type = BodyDef.BodyType.DynamicBody;
    }

    float targetTime;

    @Override
    public void update(float delta, World world) {
        super.update(delta, world);
        if (time > targetTime) remove = true;
    }

    @Override
    public void addToWorld(World world) {
        super.addToWorld(world);
        body.setTransform(5, 6, 0);
        targetTime = 5 + MathUtils.random(1f);
    }

    public Enemy(){
        bodyDef = bD;
        fixDef = fD;
    }
}
