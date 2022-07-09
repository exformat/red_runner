package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class AngularVelocityComponent implements Component {
	public float value = 0;
	
	public static ComponentMapper<AngularVelocityComponent> mapper = ComponentMapper.getFor(AngularVelocityComponent.class);
}
