package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class RotateComponent implements Component {
	
	public float degress = 0;

	public static ComponentMapper<RotateComponent> mapper = ComponentMapper.getFor(RotateComponent.class);
}
