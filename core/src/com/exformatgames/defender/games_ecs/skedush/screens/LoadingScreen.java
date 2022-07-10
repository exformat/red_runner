package com.exformatgames.defender.games_ecs.skedush.screens;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.audio.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.*;
import com.badlogic.gdx.utils.viewport.*;
import com.exformatgames.defender.games_ecs.skedush.audio.*;
import com.exformatgames.defender.util.FreeTypeSkinLoader;

public class LoadingScreen implements Screen {

	private Game game;
	
	private AssetManager assetManager;
	private Viewport viewport;
	private OrthographicCamera camera;
    private Sprite backgroundSprite;
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;

	public LoadingScreen(Game game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		camera = new OrthographicCamera();
		viewport = new ExtendViewport(7.2f, 12.8f, 10.8f, 19.2f, camera);
		viewport.apply(true);
		
		batch = new SpriteBatch();
		shapeRenderer = new ShapeRenderer();
		assetManager = new AssetManager();

		assetManager.setLoader(Skin.class, new FreeTypeSkinLoader(assetManager.getFileHandleResolver()));


		backgroundSprite = new Sprite(new Texture("loading_background.png"));
		backgroundSprite.setSize(7.2f, 12.8f);

		assetManager.load("atlas/atlas.atlas", TextureAtlas.class);

		assetManager.load("background.png", Texture.class);
		
		assetManager.load("sounds/detoxio/picked_coin.ogg", Sound.class);
		assetManager.load("sounds/detoxio/end_game.ogg", Sound.class);
		assetManager.load("sounds/jalastram/jump.wav", Sound.class);
		assetManager.load("sounds/jalastram/end_jump.wav", Sound.class);

		assetManager.load("skins/SkinData.json", Skin.class);

		assetManager.load("music/Forest.mp3", Music.class);
	}

	@Override
	public void render(float dt) {
		ScreenUtils.clear(0, 0, 0, 1, true);

		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		batch.begin();
			backgroundSprite.draw(batch);
		batch.end();
		
		assetManager.update();
		
		float loadingLine = viewport.getWorldWidth() * assetManager.getProgress();
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
			shapeRenderer.rect(0, 0.5f, loadingLine, 1);
		shapeRenderer.end();
		
		
		if(assetManager.isFinished()){
			Sounds.init(assetManager);
			Musics.init(assetManager);

			StartScreen startScreen = new StartScreen(game, assetManager, initAtlas());
			game.setScreen(startScreen);
			//GameScreen gameScreen = new GameScreen(game, assetManager, initAtlas());
			//game.setScreen(gameScreen);
		}
	}

	@Override
	public void resize(int w, int h) {
		viewport.update(w, h, true);
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
		batch.dispose();
		backgroundSprite.getTexture().dispose();
		shapeRenderer.dispose();
	}
	
	private TextureAtlas initAtlas() {
		TextureAtlas atlas = assetManager.get("atlas/atlas.atlas", TextureAtlas.class);
		
		atlas.addRegion("menu_bkg", new TextureRegion(assetManager.get("background.png", Texture.class)));
		return atlas;
	}
}
