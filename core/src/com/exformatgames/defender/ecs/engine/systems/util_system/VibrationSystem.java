package com.exformatgames.defender.ecs.engine.systems.util_system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.*;
import com.exformatgames.defender.ecs.engine.components.platform_components.*;

public class VibrationSystem extends IteratingSystem {
	
	public VibrationSystem(){
		super(Family.all(VibrationComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		VibrationComponent vibrationComponent = VibrationComponent.mapper.get(entity);
		Gdx.input.vibrate(vibrationComponent.millis);
		entity.remove(VibrationComponent.class);
	}
}
