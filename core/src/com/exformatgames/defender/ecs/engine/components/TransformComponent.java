package com.exformatgames.defender.ecs.engine.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class TransformComponent implements Component {
	
	public Vector2 position = new Vector2();
	public Vector2 deltaPosition = new Vector2();
	public Vector2 oldPosition = new Vector2();
	
	public float angle = 0;
	public float deltaAngle = 0;
	public float radians = 0;
	public float deltaRadians = 0;
	
	
	public static ComponentMapper<TransformComponent> mapper = ComponentMapper.getFor(TransformComponent.class);
}
