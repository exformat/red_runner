package com.exformatgames.defender.games_ecs.skedush.game_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;
import com.exformatgames.defender.ecs.engine.components.math_components.*;
import com.exformatgames.defender.ecs.engine.components.platform_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.games_ecs.skedush.components.*;
import com.exformatgames.defender.games_ecs.skedush.state.PlayerState;

public class EnemyCollisionSystem extends IteratingSystem {
	
	private Entity playerEntity;

	public EnemyCollisionSystem(){
		super(Family.one(EnemyComponent.class, PlayerComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		if(playerComponent != null){
			playerEntity = entity;
			return;
		}
		
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		SoundComponent soundComponent = SoundComponent.mapper.get(entity);

		//transform circle sensor
		float x = spriteComponent.x + spriteComponent.width / 2;
		float y = spriteComponent.y + spriteComponent.height / 2;
		CircleComponent circleComponent = CircleComponent.mapper.get(entity);
		circleComponent.circle.setPosition(x, y);

		if(cullingComponent.inViewport) {
			CircleComponent playerCircle = CircleComponent.mapper.get(playerEntity);
			playerComponent = PlayerComponent.mapper.get(playerEntity);

			if (Intersector.overlaps(playerCircle.circle, circleComponent.circle) && playerComponent.playerState != PlayerState.DEATH){
				playerEntity.add(getEngine().createComponent(PlayerDeathComponent.class));

				VibrationComponent vibrationComponent = getEngine().createComponent(VibrationComponent.class);
				vibrationComponent.millis = 40;
				entity.add(vibrationComponent);

				soundComponent.play = true;
			}
		}
	}
}
