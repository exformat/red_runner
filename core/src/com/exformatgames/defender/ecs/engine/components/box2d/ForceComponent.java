package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class ForceComponent implements Component 
{
	public Vector2 point = new Vector2(0, 0);
	public Vector2 force = new Vector2(0, 0);
	
	public boolean center = false;
	
	public static ComponentMapper<ForceComponent> mapper = ComponentMapper.getFor(ForceComponent.class);
}
