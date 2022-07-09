package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;

public class ScrollComponent implements Component {
	public float moveByX = 0;
	public float moveByY = 0;
	
	public boolean horizontal = false;
	public boolean vertical = false;
	
	public static ComponentMapper<ScrollComponent> mapper = ComponentMapper.getFor(ScrollComponent.class);
}
