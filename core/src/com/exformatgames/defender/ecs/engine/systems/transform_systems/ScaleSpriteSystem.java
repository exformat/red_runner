package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class ScaleSpriteSystem extends IteratingSystem {
	public ScaleSpriteSystem() {
		super(Family.all(ScaleComponent.class, SpriteComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		ScaleComponent scale = ScaleComponent.mapper.get(entity);
		SpriteComponent sprite = SpriteComponent.mapper.get(entity);

		if(scale.isTo){
			sprite.scaleX = scale.to;
			sprite.scaleY = scale.to;
		}
		else{
			sprite.scaleX += scale.by;
			sprite.scaleY += scale.by;
		}
		
		if(sprite.scaleX < 0 || sprite.scaleY < 0){
			sprite.scaleX = 0;
			sprite.scaleY = 0;
		}
		
		sprite.dirty = true;
		
		entity.remove(ScaleComponent.class);
	}
}
