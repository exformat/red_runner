package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;

public class ParticleEffectComponent implements Component {
	
	public ParticleEffect effect = null;
	
	public static ComponentMapper<ParticleEffectComponent> mapper = ComponentMapper.getFor(ParticleEffectComponent.class);
}
