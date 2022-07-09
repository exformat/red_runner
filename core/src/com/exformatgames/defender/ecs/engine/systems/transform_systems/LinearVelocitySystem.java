package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.badlogic.ashley.core.*;

public class LinearVelocitySystem extends IteratingSystem {
	
	public LinearVelocitySystem() {
		super(Family.all(LinearVelocityComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);
		TranslateComponent translateComponent = TranslateComponent.mapper.get(entity);

		if(translateComponent == null){
			translateComponent = getEngine().createComponent(TranslateComponent.class);
			translateComponent.x = linearVelocityComponent.value.x * dt;
			translateComponent.y = linearVelocityComponent.value.y * dt;
			
			entity.add(translateComponent);
		}else{
			translateComponent.x += linearVelocityComponent.value.x * dt;
			translateComponent.y += linearVelocityComponent.value.y * dt;
		}
	}
}
