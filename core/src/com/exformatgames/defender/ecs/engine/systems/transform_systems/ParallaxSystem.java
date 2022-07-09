package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class ParallaxSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	
	private final Vector3 oldPositionCamera = new Vector3();
	private final Vector3 deltaPositionCamera = new Vector3();
	
	public ParallaxSystem(OrthographicCamera camera){
		super(Family.all(ParallaxComponent.class).get());
		this.camera = camera;
		oldPositionCamera.set(camera.position);
	}

	@Override
	public void update(float deltaTime) {
		deltaPositionCamera.set(oldPositionCamera.sub(camera.position));
		
		super.update(deltaTime);
		oldPositionCamera.set(camera.position);
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		ParallaxComponent parallaxComponent = ParallaxComponent.mapper.get(entity);
		TranslateComponent translateComponent = TranslateComponent.mapper.get(entity);
		
		float x = parallaxComponent.horizontal ? deltaPositionCamera.x * (1f / parallaxComponent.layer) : 0;
		float y = parallaxComponent.vertical ?  deltaPositionCamera.y * (1f / parallaxComponent.layer) : 0;
		
		if(translateComponent == null){
			translateComponent = getEngine().createComponent(TranslateComponent.class);
			translateComponent.x = x;
			translateComponent.y = y;
			
			entity.add(translateComponent);
		}else{
			translateComponent.x += x;
			translateComponent.y += y;
		}
	}
}
