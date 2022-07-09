package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.*;

public class CullingComponent implements Component {
	public boolean inViewport = true;
	public float visiblRadius = 0;
	
	public static ComponentMapper<CullingComponent> mapper = ComponentMapper.getFor(CullingComponent.class);
}
