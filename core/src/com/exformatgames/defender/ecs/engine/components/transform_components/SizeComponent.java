package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class SizeComponent implements Component {
	public float width = 0;
	public float height = 0;
	
	public float halfWidth = 0;
	public float halfHeight = 0;
	
	public Vector2 size = new Vector2();
	
	public static ComponentMapper<SizeComponent> mapper = ComponentMapper.getFor(SizeComponent.class);
	
}
