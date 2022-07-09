package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class TransformLightComponentSystem extends IteratingSystem
{

	public TransformLightComponentSystem(){
		super(Family.all(LightComponent.class, TransformLightComponent.class).get());
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		LightComponent lightComponent = LightComponent.mapper.get(entity);
		TransformLightComponent transform = TransformLightComponent.mapper.get(entity);
		
		
		lightComponent.position.set(transform.position);
		lightComponent.dir = transform.dir;
		
		lightComponent.light.setPosition(transform.position.x, transform.position.y);
		lightComponent.light.setDirection(transform.dir);
		
		entity.remove(TransformLightComponent.class);
	}
}
