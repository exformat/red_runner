package com.exformatgames.defender.games_ecs.skedush.game_systems.player_systems;

import com.badlogic.ashley.systems.*;
import com.badlogic.ashley.core.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.SpriteComponent;
import com.exformatgames.defender.games_ecs.skedush.components.*;
import com.exformatgames.defender.games_ecs.skedush.state.*;

public class PlayerScoreSystem extends IntervalIteratingSystem {
	
	public PlayerScoreSystem(){
		super(Family.all(PlayerComponent.class).get(), 1);
	}

	@Override
	protected void processEntity(Entity entity) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);

		if(playerComponent.playerState != PlayerState.DEATH && playerComponent.playerState != PlayerState.IDLE){
			int basisScore = 1;
			float scalar = playerComponent.gems * 0.1f + 1;
			int totalIncrementScore = (int)(basisScore * scalar);
			
			playerComponent.score += totalIncrementScore;

			playerComponent.distance = spriteComponent.x - 1;
			System.out.println("score: " + playerComponent.score);
		}
	}
}
