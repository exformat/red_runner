package com.exformatgames.defender.games_ecs.skedush.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.exformatgames.defender.Constants;

public class StartScreen implements Screen {

	private final Game game;
	private final AssetManager assetManager;
	private final TextureAtlas atlas;

	
	private Stage stage;
	private Skin skin;
	
	private Color colorBkg;
	
	public StartScreen(Game game, AssetManager assetManager, TextureAtlas atlas) {
		this.game = game;
		this.assetManager = assetManager;
		this.atlas = atlas;
	}
	
	@Override
	public void show() {

		stage = new Stage(new FillViewport(720, 1280));

		Gdx.input.setInputProcessor(stage);

		Image imageBkg = new Image(atlas.findRegion("menu_bkg"));
		imageBkg.setBounds(0, 0, 928 * 2, 793 * 2);

		stage.addActor(imageBkg);
	}

	@Override
	public void render(float dt) {
		ScreenUtils.clear(colorBkg);
		
		stage.act(dt);
		stage.draw();
	}

	@Override
	public void resize(int w, int h) {
		stage.getViewport().update(w, h);
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
	}
}
