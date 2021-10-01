package ninja.trek;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Pools;

/** First screen of the application. Displayed after the application is created. */
public class FirstScreen implements Screen {
	private final World world;
	private final InputMultiplexer mux;
	private final Stage stage;
	private final EntityEngine engine;
	private final SpriteBatch batch;
	private final OrthographicCamera camera;
	private final Box2DDebugRenderer debugRenderer;

	public FirstScreen(){
		world = new World(new Vector2(0, -10), false);
		batch = new SpriteBatch();
		camera = new OrthographicCamera(20, 16);
		stage = new Stage();
		mux = new InputMultiplexer();
		Gdx.input.setInputProcessor(mux);
		mux.addProcessor(stage);
		mux.addProcessor(new InputProcessor(){

			@Override
			public boolean keyDown(int keycode) {
				switch (keycode){
					case Input.Keys.A:
						engine.add(Pools.obtain(Enemy.class));
						break;
				}
				return false;
			}

			@Override
			public boolean keyUp(int keycode) {
				return false;
			}

			@Override
			public boolean keyTyped(char character) {
				return false;
			}

			@Override
			public boolean touchDown(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchUp(int screenX, int screenY, int pointer, int button) {
				return false;
			}

			@Override
			public boolean touchDragged(int screenX, int screenY, int pointer) {
				return false;
			}

			@Override
			public boolean mouseMoved(int screenX, int screenY) {
				return false;
			}

			@Override
			public boolean scrolled(float amountX, float amountY) {
				return false;
			}
		});
		engine = new EntityEngine(world);
		debugRenderer = new Box2DDebugRenderer();

		engine.add(new Terrain(-10, -10, 20, 1));
		engine.add(new Enemy());
		engine.add(new Enemy());

	}
	@Override
	public void show() {
		// Prepare your screen here.
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		world.step(delta, 1, 1);
		engine.update(delta);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		debugRenderer.render(world, camera.combined);
		batch.begin();
		engine.render(batch);
		batch.end();
		// Draw your screen here. "delta" is the time since last render in seconds.
	}

	@Override
	public void resize(int width, int height) {
		// Resize your screen here. The parameters represent the new window size.
	}

	@Override
	public void pause() {
		// Invoked when your application is paused.
	}

	@Override
	public void resume() {
		// Invoked when your application is resumed after pause.
	}

	@Override
	public void hide() {
		// This method is called when another screen replaces this one.
	}

	@Override
	public void dispose() {
		// Destroy screen's assets here.
	}
}