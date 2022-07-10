package com.exformatgames.defender.game.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.exformatgames.defender.game.Skedush;
import com.exformatgames.defender.game.audio.Musics;
import com.exformatgames.defender.game.state.PlayerState;
import com.ray3k.stripe.scenecomposer.SceneComposerStageBuilder;

public class GameScreen implements Screen {
	
	private final Game game;
	private final AssetManager assetManager;
	private final TextureAtlas atlas;

	private Viewport gameViewport;

	private final InputMultiplexer inputMultiplexer = new InputMultiplexer();

	private Stage stage;
	private Skin skin;
	
	private Color colorBkg;

	private Skedush skedushCore;
	
	private boolean debug = true;

	public static int SCORE = 0;
	public static int GEMS = 0;
	public static PlayerState PLAYER_STATE;

	private Label scoreLabel;
	private Label gemsLabel;

	public GameScreen(Game game, AssetManager assetManager, TextureAtlas atlas) {
		this.game = game;
		this.assetManager = assetManager;
		this.atlas = atlas;
	}
	@Override
	public void show() {
		OrthographicCamera camera = new OrthographicCamera();
		gameViewport = new ExtendViewport(7.2f, 12.8f, 10.8f, 23.2f, camera);
		gameViewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		stage = new Stage(new ExtendViewport(720, 1280, 1080, 2400));
		skin = assetManager.get("skins/SkinData.json", Skin.class);
		inputMultiplexer.addProcessor(stage);


		SpriteBatch spriteBatch = new SpriteBatch();

		colorBkg = new Color(1f / 255 * 21, 1f / 255 * 24, 1f / 255 * 38, 1);

		skedushCore = new Skedush(camera, null, spriteBatch, inputMultiplexer, atlas, assetManager);
		skedushCore.create(debug);

		initScene();

		Gdx.input.setInputProcessor(inputMultiplexer);

		Musics.forestMusic.play();
		Musics.forestMusic.setLooping(true);
	}


	private final FPSLogger fps = new FPSLogger();
	@Override
	public void render(float dt) {
		ScreenUtils.clear(colorBkg);
		fps.log();
		gameViewport.apply();
		skedushCore.update(dt);

		scoreLabel.setText("score: " + SCORE);
		gemsLabel.setText("gems: " + GEMS);

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
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
		stage.dispose();
		skin.dispose();
		assetManager.dispose();
	}

	private void initScene(){
		stage.setDebugAll(debug);
		Array<Table> tables = new SceneComposerStageBuilder().build(stage, skin, Gdx.files.internal("skins/GameScreenScene.json"));
		scoreLabel = tables.get(0).findActor("ScoreLabel");
		gemsLabel = tables.get(0).findActor("GemsLabel");
	}
}
