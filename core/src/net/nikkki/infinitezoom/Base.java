package net.nikkki.infinitezoom;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class Base implements Screen {
	public Game game;
	public Resolver resolver;
	public GL20 gl;
	public float sW, sH;
	public boolean isAndroid;

	private long diff, start;
	private final float targetFPS = 30f; // 20-30 is enough
	private final long targetDelay = 1000 / (long) targetFPS;

	public Base(Game game, Resolver resolver) {
		this.game = game;
		this.resolver = resolver;
		this.gl = Gdx.gl20;
		this.sW = Gdx.graphics.getWidth();
		this.sH = Gdx.graphics.getHeight();
		this.isAndroid = Gdx.app.getType() == ApplicationType.Android;
	}

	@Override
	public void render(float delta) {
		gl.glClearColor(1f, 1f, 1f, 1f);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gl.glViewport(0, 0, (int) sW, (int) sH);
	}
 
	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	@Override
	public void show() {}

	@Override
	public void resize(int width, int height) {
//		this.sW = Gdx.graphics.getWidth();
//		this.sH = Gdx.graphics.getHeight();
		this.sW = width;
		this.sH = height;
	}

	@Override
	public void hide() {}
	@Override
	public void dispose() {}

	public void limitFPS() {
		diff = System.currentTimeMillis() - start;

		if (diff < targetDelay) {
			try {
				Thread.sleep(targetDelay - diff);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		start = System.currentTimeMillis();
	}
}
