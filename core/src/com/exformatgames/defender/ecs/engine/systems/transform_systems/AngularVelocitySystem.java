package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class AngularVelocitySystem extends IteratingSystem {

	public AngularVelocitySystem(){
		super(Family.all(AngularVelocityComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		AngularVelocityComponent angularVelocityComponent = AngularVelocityComponent.mapper.get(entity);
		RotateComponent rotateComponent = RotateComponent.mapper.get(entity);
		
		if(rotateComponent == null){
			rotateComponent = getEngine().createComponent(RotateComponent.class);
			rotateComponent.degress = angularVelocityComponent.value * dt;
			entity.add(rotateComponent);
		}else{
			rotateComponent.degress += angularVelocityComponent.value * dt;
		}
	}
}
