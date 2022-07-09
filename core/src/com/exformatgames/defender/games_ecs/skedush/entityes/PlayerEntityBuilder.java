package com.exformatgames.defender.games_ecs.skedush.entityes;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.EntityBuilder;
import com.exformatgames.defender.games_ecs.skedush.components.*;
import com.exformatgames.defender.ecs.engine.components.touch_components.*;
import com.exformatgames.defender.ecs.engine.components.math_components.*;
import com.exformatgames.defender.*;

public class PlayerEntityBuilder extends EntityBuilder {
	
	public static Entity build(){
		Entity entity = engine.createEntity();
		
		SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
		ZIndexComponent zIndexComponent = engine.createComponent(ZIndexComponent.class);
		CullingComponent cullingComponent = engine.createComponent(CullingComponent.class);
		AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
		PlayerComponent playerComponent = engine.createComponent(PlayerComponent.class);
		CameraComponent cameraComponent = engine.createComponent(CameraComponent.class);
		LinearVelocityComponent linearVelocityComponent = engine.createComponent(LinearVelocityComponent.class);
		GestureTapComponent gestureTapComponent = engine.createComponent(GestureTapComponent.class);
		GestureFlingComponent gestureFlingComponent = engine.createComponent(GestureFlingComponent.class);
		CircleComponent circleComponent = engine.createComponent(CircleComponent.class);
		
		spriteComponent.x = 1;
		spriteComponent.y = 3;
		zIndexComponent.zIndex = 9;
		
		playerComponent.animationIdle = new Animation<>(1f / 6, atlas.findRegions("player_idle"), Animation.PlayMode.LOOP);
		playerComponent.animationJump = new Animation<>(1f / 2, atlas.findRegions("player_jump"), Animation.PlayMode.NORMAL);
		playerComponent.animationRun = new Animation<>(1f / 5, atlas.findRegions("player_run"), Animation.PlayMode.LOOP);
		playerComponent.animationCrouch = new Animation<>(1f / 4, atlas.findRegions("player_crouch"), Animation.PlayMode.LOOP);
		playerComponent.animationDeath = new Animation<>(1f / 6, atlas.findRegions("enemy_death"), Animation.PlayMode.NORMAL);

		animationComponent.animation = playerComponent.animationIdle;
		
		cameraComponent.camera = camera;

		float x = 1 + 32 * Constants.SCL;
		float y = 3 + 32 * Constants.SCL;
		float radius = 10 * Constants.SCL;
		
		circleComponent.circle.set(x, y , radius);
		
		entity.add(spriteComponent);
		entity.add(zIndexComponent);
		entity.add(cullingComponent);
		entity.add(animationComponent);
		entity.add(playerComponent);
		entity.add(gestureTapComponent);
		entity.add(gestureFlingComponent);
		entity.add(cameraComponent);
		entity.add(linearVelocityComponent);
		entity.add(circleComponent);
		
		return entity;
	}
}
