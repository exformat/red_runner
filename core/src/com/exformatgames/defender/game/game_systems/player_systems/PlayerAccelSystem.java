package com.exformatgames.defender.game.game_systems.player_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.game.components.*;
import com.exformatgames.defender.game.state.*;

public class PlayerAccelSystem extends IteratingSystem {

	public PlayerAccelSystem() {
		super(Family.all(PlayerComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);
		
		//accel
		if (linearVelocityComponent.value.x < playerComponent.maxVelocityPlayer && (playerComponent.playerState == PlayerState.RUN || playerComponent.playerState == PlayerState.JUMP)) {
			linearVelocityComponent.value.x += playerComponent.accelPlayer * dt;
			if (linearVelocityComponent.value.x > playerComponent.maxVelocityPlayer) {
				linearVelocityComponent.value.x = playerComponent.maxVelocityPlayer;
			}

			//change animation speed
			if (playerComponent.playerState == PlayerState.RUN) {
				float frameDuration;
				float a = linearVelocityComponent.value.x / playerComponent.maxVelocityPlayer;
				float b = (20 - 6) * a + 6;

				frameDuration = 1f / b;
				animationComponent.animation.setFrameDuration(frameDuration);
			}
		}
	}
}
