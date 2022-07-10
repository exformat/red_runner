package com.exformatgames.defender.games_ecs.skedush.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.exformatgames.defender.Constants;
import com.github.tommyettinger.textra.TypingLabel;

public class StartScreen implements Screen {

	private final Game game;
	private final AssetManager assetManager;
	private final TextureAtlas atlas;

	private Stage stage;
	private Skin skin;
	
	public StartScreen(Game game, AssetManager assetManager, TextureAtlas atlas) {
		this.game = game;
		this.assetManager = assetManager;
		this.atlas = atlas;
	}
	
	@Override
	public void show() {
		stage = new Stage(new ExtendViewport(720, 1280, 1080, 2400));

		skin = assetManager.get("skins/SkinData.json", Skin.class);

		Gdx.input.setInputProcessor(stage);

		Image imageBkg = new Image(atlas.findRegion("menu_bkg"));
		imageBkg.setBounds(0, 0, 928 * 2, 793 * 2);
		stage.addActor(imageBkg);

		TypingLabel gameNameLabel = new TypingLabel("Red runner", skin, "MyLabelStyle");
		gameNameLabel.setPosition(100, 800);
		stage.addActor(gameNameLabel);


		int maxScore = Gdx.app.getPreferences(Constants.prefsName).getInteger(Constants.prefsMaxScoreKey, 0);
		TypingLabel recordLabel = new TypingLabel("record: " + maxScore, skin, "ScoreLabelStyle");
		recordLabel.setPosition(50, stage.getHeight() - (stage.getHeight() / 100 * 5));
		stage.addActor(recordLabel);

		ImageTextButton startButton = new ImageTextButton("START", skin, "StartGameImageTextButtonStyle");
		startButton.setPosition(360 - 128, 500);
		startButton.addAction(Actions.fadeIn(0.5f));
		startButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				game.setScreen(new GameScreen(game, assetManager, atlas));
			}
		});
		stage.addActor(startButton);

		ImageTextButton exitButton = new ImageTextButton("exit", skin, "ExitGameImageTextButtonStyle");
		exitButton.setPosition(stage.getWidth() - exitButton.getWidth(), stage.getHeight() - exitButton.getHeight());
		exitButton.addAction(Actions.fadeIn(0.5f));
		exitButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		stage.addActor(exitButton);

		stage.setDebugAll(true);
	}

	@Override
	public void render(float dt) {
		ScreenUtils.clear(0,0,0,1);
		
		stage.act(dt);
		stage.draw();
	}

	@Override
	public void resize(int w, int h) {
		stage.getViewport().update(w, h);
		stage.getViewport().apply(true);
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
}
