package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.graphics.*;

public class B2DParticleEmitterComponent implements Component
{
	public ParticleEmitterBox2D emitter = null;

	public static ComponentMapper<B2DParticleEmitterComponent> mapper = ComponentMapper.getFor(B2DParticleEmitterComponent.class);
}
