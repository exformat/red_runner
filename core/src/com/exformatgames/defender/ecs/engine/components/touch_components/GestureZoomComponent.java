package com.exformatgames.defender.ecs.engine.components.touch_components;

import com.badlogic.ashley.core.*;

public class GestureZoomComponent implements Component {
	public float initialDistance;
	public float endDistance;
	
	public float zoom;
	public float delta;

	public static ComponentMapper<GestureZoomComponent> mapper = ComponentMapper.getFor(GestureZoomComponent.class);
}
