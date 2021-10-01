package ninja.trek;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pools;

public class EntityEngine {
    Array<Entity> entities =new Array<Entity>();
    World world;
    public EntityEngine(World world){
        this.world = world;

    }
    public void add(Entity e){
        entities.add(e);
        e.addToWorld(world);
    }
    public void update(float delta) {
        Array.ArrayIterator<Entity> iter = entities.iterator();
        while (iter.hasNext()){
            Entity e = iter.next();
            e.update(delta, world);
        }
        iter = entities.iterator();
        while (iter.hasNext()){
            Entity e = iter.next();
            if (e.remove){
                world.destroyBody(e.body);
                e.onRemove();
                iter.remove();
                Pools.free(e);
            }
        }

    }

    public void render(SpriteBatch batch) {
        Array.ArrayIterator<Entity> iter = entities.iterator();

        while (iter.hasNext()){
            Entity e = iter.next();
            e.render(batch);
        }
    }
}
