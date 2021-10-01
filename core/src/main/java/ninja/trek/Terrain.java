package ninja.trek;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class Terrain extends Entity{
    static BodyDef bD;
    static FixtureDef fD;

    public Terrain(float x, float y, float w, float h){
        bD = new BodyDef();
        fD = new FixtureDef();
        float size = 1f;
        Vector2[] shapeVectors = {
                new Vector2(0, 0),
                new Vector2(0, h),
                new Vector2(w, h),
                new Vector2(w, 0)
        };
        PolygonShape shape = new PolygonShape();
        shape.set(shapeVectors);
        fD.shape = shape;

        bodyDef = bD;
        fixDef = fD;
    }
}
