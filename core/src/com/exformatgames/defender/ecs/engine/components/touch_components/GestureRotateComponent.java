package com.exformatgames.defender.ecs.engine.components.touch_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GestureRotateComponent implements Component {
	
	public Vector2 rotationPoint = new Vector2(0, 0);
	public float degres = 0;
	public float delta = 0;
	
	public static final ComponentMapper<GestureRotateComponent> mapper = ComponentMapper.getFor(GestureRotateComponent.class);
}
