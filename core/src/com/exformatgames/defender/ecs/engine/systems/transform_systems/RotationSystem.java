package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class RotationSystem extends IteratingSystem {
	
	public RotationSystem(){
		super(Family.all(RotationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		RotationComponent rotation = RotationComponent.mapper.get(entity);
		
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		LightComponent lightComponent = LightComponent.mapper.get(entity);
		CameraComponent cameraComponent = CameraComponent.mapper.get(entity);

		if(lightComponent != null){
			lightComponent.light.setDirection(rotation.degress);
		}

		if(cameraComponent != null){
			cameraComponent.camera.rotate(rotation.degress);
			cameraComponent.camera.update();
		}
		if(spriteComponent != null){
			spriteComponent.rotation = rotation.degress;
			spriteComponent.dirty = true;
		}
		
		entity.remove(RotationComponent.class);
	}
}
