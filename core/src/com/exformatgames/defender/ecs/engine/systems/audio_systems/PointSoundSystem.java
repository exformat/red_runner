package com.exformatgames.defender.ecs.engine.systems.audio_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;

public class PointSoundSystem extends IteratingSystem {
	
	private final OrthographicCamera camera;
	private final Vector2 camPos = new Vector2();
	private final Interpolation fun = Interpolation.circleIn;;
	private final Vector2 normal = new Vector2();
	
	public PointSoundSystem(OrthographicCamera camera){
		super(Family.all(PointSoundComponent.class).get());
		
		this.camera = camera;
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		PointSoundComponent pointSoundComponent = PointSoundComponent.mapper.get(entity);
		SoundComponent soundComponent = SoundComponent.mapper.get(entity);
		
		camPos.set(camera.position.x, camera.position.y);
		float dst = Math.abs(camPos.dst(pointSoundComponent.position));
		
		normal.set(pointSoundComponent.position.x - camPos.x, pointSoundComponent.position.y - camPos.y);
		normal.nor();
		
		if(dst > pointSoundComponent.hearingRadius){
			soundComponent.volume = 0;
		}
		else{
			soundComponent.volume = fun.apply(1 - dst / pointSoundComponent.hearingRadius);
			soundComponent.volume *= soundComponent.mul;
			soundComponent.pan = normal.x;
		}
	}
}
