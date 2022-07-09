package com.exformatgames.defender.games_ecs.skedush.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.exformatgames.defender.games_ecs.skedush.*;
import com.exformatgames.defender.games_ecs.skedush.audio.Musics;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;

public class GameScreen implements Screen {
	
	private final Game game;
	private final AssetManager assetManager;
	private final TextureAtlas atlas;

	private OrthographicCamera camera;
	private Viewport gameViewport;
	private SpriteBatch spriteBatch;

	private InputMultiplexer inputMultiplexer = new InputMultiplexer();

	private Stage stage;
	private Skin skin;
	
	private Color colorBkg;

	private Skedush skedushCore;
	
	private boolean debug = true;
	
	public GameScreen(Game game, AssetManager assetManager, TextureAtlas atlas) {
		this.game = game;
		this.assetManager = assetManager;
		this.atlas = atlas;
	}
	@Override
	public void show() {
		camera = new OrthographicCamera();
		gameViewport = new ExtendViewport(7.2f, 12.8f, 10.8f, 23.2f, camera);
		gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		stage = new Stage(new FitViewport(720, 1280));
		//skin = assetManager.get("skins/game_screen/GameScreenSkin.json", Skin.class);
		inputMultiplexer.addProcessor(stage);



		spriteBatch = new SpriteBatch();

		colorBkg = new Color(1f / 255 * 21, 1f / 255 * 24, 1f / 255 * 38, 1);

		skedushCore = new Skedush(camera, null, spriteBatch, inputMultiplexer, atlas, assetManager);
		skedushCore.create(debug);

		initScene();

		Gdx.input.setInputProcessor(inputMultiplexer);

		Musics.forestMusic.play();
		Musics.forestMusic.setLooping(true);
	}

	@Override
	public void render(float dt) {
		ScreenUtils.clear(colorBkg);

		gameViewport.apply();
		skedushCore.update(dt);
		
		stage.act(dt);
		stage.getViewport().apply(true);
		stage.draw();
	}

	@Override
	public void resize(int w, int h) {
		gameViewport.update(w, h, true);
		stage.getViewport().update(w, h, true);
	}

	@Override
	public void pause() {
		// TODO: Implement this method
	}

	@Override
	public void resume() {
		// TODO: Implement this method
	}

	@Override
	public void hide() {
		// TODO: Implement this method
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		assetManager.dispose();
	}

	private void initScene(){
		stage.setDebugAll(debug);
		//new SceneComposerStageBuilder().build(stage, skin, Gdx.files.internal("skins/game_screen/GameScreenScene.json"));
	}
}
