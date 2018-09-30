package com.yourmom.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.yourmom.events.BotrGEventCoordinator;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class BotrG extends ApplicationAdapter {
	private OrthographicCamera cam;
	private SpriteBatch batch;
	private BotrGEventCoordinator eventCoordinator;

	private boolean startAnim;
	private Runnable animationTick;
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);


	@Override
	public void create () {
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		batch = new SpriteBatch();
		startAnim = true;

		BotrGStyle.setup(cam.viewportWidth, cam.viewportHeight);

		animationTick = new Runnable() {
			@Override
			public void run() {
				eventCoordinator.updateAnimation();
			}
		};

		eventCoordinator = new BotrGEventCoordinator();

	}

	@Override
	public void render () {
		//logic
		cam.update();

		//eventCoordinator.updateAnimation();
		if(startAnim){
			executor.scheduleAtFixedRate(animationTick, 0, 33, TimeUnit.MILLISECONDS);
			startAnim = false;
		}

		eventCoordinator.update();

		//graphics
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.setProjectionMatrix(cam.combined);

		batch.begin();
		eventCoordinator.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
