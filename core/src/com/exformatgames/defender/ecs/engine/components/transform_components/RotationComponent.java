package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class RotationComponent implements Component {
	
	public float degress = 0;
	
	public static ComponentMapper<RotationComponent> mapper = ComponentMapper.getFor(RotationComponent.class);
}
