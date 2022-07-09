package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class LinearVelocityComponent implements Component {
	
	public Vector2 value = new Vector2(0,0);
	
	public static ComponentMapper<LinearVelocityComponent> mapper = ComponentMapper.getFor(LinearVelocityComponent.class);
}
