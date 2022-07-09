package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class B2DParticleEmmiterUpateSystem extends IteratingSystem
{

	public B2DParticleEmmiterUpateSystem(){
		super(Family.all(B2DParticleEmitterComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		B2DParticleEmitterComponent part = B2DParticleEmitterComponent.mapper.get(entity);
		part.emitter.update(dt);
	}
}
