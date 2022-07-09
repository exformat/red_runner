package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.utils.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class UpdateWorldSystem extends IteratingSystem
{

	public static float FRAME_TIME = 0;
	private long startTime = 0;

	public UpdateWorldSystem(){
		super(Family.all(WorldComponent.class).get());
	}

	@Override
	public void startProcessing() {
		startTime = TimeUtils.nanoTime();
	}

	@Override
	public void endProcessing() {
		FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
	}
	
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		WorldComponent worldComponent = WorldComponent.mapper.get(entity);

		worldComponent.world.step(dt, worldComponent.velocityIter, worldComponent.positionIter);
	}
}


