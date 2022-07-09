package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class TransformLightComponent implements Component
{
	public Vector2 position = new Vector2();
	public float dir = 0;
	
	public static final ComponentMapper<TransformLightComponent> mapper = ComponentMapper.getFor(TransformLightComponent.class);
}
