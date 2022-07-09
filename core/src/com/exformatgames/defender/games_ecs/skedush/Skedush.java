package com.exformatgames.defender.games_ecs.skedush;

import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.*;
import com.exformatgames.defender.games_ecs.skedush.entityes.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.games_ecs.skedush.game_systems.*;
import com.exformatgames.defender.games_ecs.skedush.game_systems.player_systems.*;

public class Skedush extends DefenderCore {

	public Skedush(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
		super(camera, box2DWorld, spriteBatch, inputMultiplexer, atlas, assetManager);
	}
	
	@Override
	protected void initEntities() {

		for(int c = 0; c < 2; c++){
			for(int i = 0; i < 12; i++){
				engine.addEntity(BackgroundEntityBuilder.build(new Vector2(928 * 0.025f * c, 1.33f), i));
			}
		}
		
		engine.addEntity(PlayerEntityBuilder.build());
	}

	@Override
	protected void initGameSystems() {
		engine.addSystem(new PlayerSystem());
        engine.addSystem(new PlayerAccelSystem());
		engine.addSystem(new PlayerControlSystem());
        engine.addSystem(new PlayerScoreSystem());
		engine.addSystem(new PlayerDeathSystem());
		engine.addSystem(new PlayerJumpSystem());


		engine.addSystem(new BonusSpawnSystem(camera));
		engine.addSystem(new BonusCollisionSystem());
		engine.addSystem(new EnemySpawnSystem(camera));
		engine.addSystem(new EnemyCollisionSystem());
	}
}
