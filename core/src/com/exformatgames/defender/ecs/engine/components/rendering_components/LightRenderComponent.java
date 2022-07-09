package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.*;
import box2dLight.*;

public class LightRenderComponent implements Component {
	
	public RayHandler rayHandler = null;
	
	public static ComponentMapper<LightRenderComponent> mapper = ComponentMapper.getFor(LightRenderComponent.class);
}
