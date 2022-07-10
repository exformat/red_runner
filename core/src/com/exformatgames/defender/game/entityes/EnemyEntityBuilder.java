package com.exformatgames.defender.game.entityes;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.audio_components.SoundComponent;
import com.exformatgames.defender.ecs.engine.components.math_components.CircleComponent;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.EntityBuilder;
import com.exformatgames.defender.game.audio.Sounds;
import com.exformatgames.defender.game.components.*;
import com.badlogic.gdx.graphics.g2d.*;


public class EnemyEntityBuilder extends EntityBuilder {
	
	public static Entity build(Vector2 position, EnemyType type){
		Entity entity = engine.createEntity();
		
		SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
		ZIndexComponent zIndexComponent = engine.createComponent(ZIndexComponent.class);
		CullingComponent cullingComponent = engine.createComponent(CullingComponent.class);
		AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		ParallaxComponent parallaxComponent = engine.createComponent(ParallaxComponent.class);
		EnemyComponent enemyComponent = engine.createComponent(EnemyComponent.class);
		CircleComponent circleComponent = engine.createComponent(CircleComponent.class);
		SoundComponent soundComponent = engine.createComponent(SoundComponent.class);

		//FIXME need animation pool..
		switch(type){
			case OPOSSUM : {
					animationComponent.animation = new Animation<>(1f / 6, atlas.findRegions("opossum"), Animation.PlayMode.LOOP);
					LinearVelocityComponent linearVelocityComponent = engine.createComponent(LinearVelocityComponent.class);
					linearVelocityComponent.value.x = -0.8f;
					entity.add(linearVelocityComponent);
					break;
				}

			case FROG : {
					animationComponent.animation = new Animation<>(1f / 6, atlas.findRegions("frog_idle"), Animation.PlayMode.LOOP);
					position.y -= 0.15f;
					break;
				}

			case EAGLE : {
					animationComponent.animation = new Animation<>(1f / 6, atlas.findRegions("eagle"), Animation.PlayMode.LOOP);
					LinearVelocityComponent linearVelocityComponent = engine.createComponent(LinearVelocityComponent.class);
					linearVelocityComponent.value.x = -2;
					entity.add(linearVelocityComponent);
					break;
				}
		}

		spriteComponent.initialize(animationComponent.animation.getKeyFrame(0), Constants.SCL);

		float x = position.x + spriteComponent.originX;
		float y = position.y + spriteComponent.originY;
		float radius = spriteComponent.originY;

		circleComponent.circle.set(x, y , radius);

		zIndexComponent.zIndex = 8;
		parallaxComponent.layer = 5;
		parallaxComponent.vertical = true;
		parallaxComponent.horizontal = true;

		positionComponent.x = position.x;
		positionComponent.y = position.y;

		soundComponent.sound = Sounds.endGameSound;

		entity.add(spriteComponent);
		entity.add(zIndexComponent);
		entity.add(cullingComponent);
		entity.add(animationComponent);
		entity.add(positionComponent);
		entity.add(parallaxComponent);
		entity.add(circleComponent);
		entity.add(enemyComponent);
		entity.add(soundComponent);
		
		return entity;
	}
}
