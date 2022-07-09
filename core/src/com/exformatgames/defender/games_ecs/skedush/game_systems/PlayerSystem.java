package com.exformatgames.defender.games_ecs.skedush.game_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.touch_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.games_ecs.skedush.audio.Sounds;
import com.exformatgames.defender.games_ecs.skedush.components.*;
import com.exformatgames.defender.games_ecs.skedush.state.*;
import com.exformatgames.defender.ecs.engine.components.math_components.*;

public class PlayerSystem extends IteratingSystem {

	public PlayerSystem() {
		super(Family.all(PlayerComponent.class).get());
	}


	@Override
	protected void processEntity(Entity entity, float dt) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		CameraComponent cameraComponent = CameraComponent.mapper.get(entity);


		//transition from jump to run
		if (spriteComponent.y < 3) {
			playerComponent.playerState = PlayerState.RUN;
			animationComponent.timeAnimation = 0;
			animationComponent.animation = playerComponent.animationRun;
			linearVelocityComponent.value.y = 0;
			spriteComponent.y = 3;
			cameraComponent.camera.position.y = cameraComponent.camera.viewportHeight / 2;
			Sounds.endJumpSound.play();
		}

		//transition from crouch to run
		if (playerComponent.playerState == PlayerState.CROUCH) {
			if ((playerComponent.crouchTime -= dt) < 0) {
				playerComponent.crouchTime = 1;
				playerComponent.playerState = PlayerState.RUN;
				animationComponent.timeAnimation = 0;
				animationComponent.animation = playerComponent.animationRun;
			}
		}

		//gravity
		if (spriteComponent.y > 3 && (playerComponent.playerState == PlayerState.JUMP || playerComponent.playerState == PlayerState.DOUBLE_JUMP)) {
			linearVelocityComponent.value.y -= 9.8f * dt;
		}

		//transform circle sensor
		float x = spriteComponent.x + spriteComponent.width / 2;
		float y = spriteComponent.y + spriteComponent.height / 2;
		CircleComponent circleComponent = CircleComponent.mapper.get(entity);
		circleComponent.circle.setPosition(x, y);
	}
}
