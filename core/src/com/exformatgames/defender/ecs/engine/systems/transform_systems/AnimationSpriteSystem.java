package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.exformatgames.defender.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class AnimationSpriteSystem extends IteratingSystem {
	
	public AnimationSpriteSystem(){
		super(Family.all(AnimationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		SpriteComponent sprite = SpriteComponent.mapper.get(entity);
		
		animationComponent.timeAnimation += dt;
		
		if(animationComponent.animation.getPlayMode() == Animation.PlayMode.LOOP || ! animationComponent.animation.isAnimationFinished(animationComponent.timeAnimation)){
			sprite.initialize(animationComponent.animation.getKeyFrame(animationComponent.timeAnimation), Constants.SCL);
		}
	}
}
