package com.exformatgames.defender.ecs.engine.components.light_components;

import com.badlogic.ashley.core.*;
import box2dLight.*;

public class PointLightComponent implements Component {
	
	public PointLight light = null;
	
	public static final ComponentMapper<PointLightComponent> mapper = ComponentMapper.getFor(PointLightComponent.class);
}
