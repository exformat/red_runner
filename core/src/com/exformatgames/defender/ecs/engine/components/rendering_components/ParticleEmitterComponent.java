package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;

public class ParticleEmitterComponent implements Component {
	
	public ParticleEmitter emitter = null;
	public String name;
	
	public static ComponentMapper<ParticleEmitterComponent> mapper = ComponentMapper.getFor(ParticleEmitterComponent.class);
}
