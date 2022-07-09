package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class PositionComponent implements Component {
	public float x = 0;
	public float y = 0;
	
	public static ComponentMapper<PositionComponent> mapper = ComponentMapper.getFor(PositionComponent.class);
}
