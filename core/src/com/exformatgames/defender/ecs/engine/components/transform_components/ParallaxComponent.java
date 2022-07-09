package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class ParallaxComponent implements Component {
	public int layer = 0;
	
	public boolean horizontal = false;
	public boolean vertical = false;
	
	public static ComponentMapper<ParallaxComponent> mapper = ComponentMapper.getFor(ParallaxComponent.class);
}
