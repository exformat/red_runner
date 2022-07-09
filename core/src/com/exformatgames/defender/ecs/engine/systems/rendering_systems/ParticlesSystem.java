package com.exformatgames.defender.ecs.engine.systems.rendering_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;

public class ParticlesSystem extends IteratingSystem
{
	
	public ParticlesSystem(){
		super(Family.all(ParticleEffectComponent.class).get());
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		ParticleEffectComponent particleComponent = ParticleEffectComponent.mapper.get(entity);
		particleComponent.effect.update(dt);
	}
}
