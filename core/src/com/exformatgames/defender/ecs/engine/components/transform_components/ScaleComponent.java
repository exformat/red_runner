package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class ScaleComponent implements Component {
	public float to = 1;
	public float by = 0;
	
	public boolean isTo = true;
	
	public static final ComponentMapper<ScaleComponent> mapper = ComponentMapper.getFor(ScaleComponent.class);
}
