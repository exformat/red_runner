package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class TransformBodyComponent implements Component {
	public Vector2 position = new Vector2();
	public float angularVelocity = 0;
	public Vector2 linearVelocity = new Vector2();
	public float angle = 0;
	public boolean awake = false;
	
	
	
	public static ComponentMapper<TransformBodyComponent> mapper = ComponentMapper.getFor(TransformBodyComponent.class);
}
