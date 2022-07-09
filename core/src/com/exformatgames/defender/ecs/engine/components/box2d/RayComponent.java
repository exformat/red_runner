package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class RayComponent implements Component {
	
	public Vector2 fromPoint = new Vector2();
	public Vector2 toPoint = new Vector2(); 
	public Vector2 collisionPoint = new Vector2(); 
	
	public Fixture collisionFixture;
	
	public float maxRayLenght = 15;
	public float lenght = 0;
	
	public static ComponentMapper<RayComponent> mapper = ComponentMapper.getFor(RayComponent.class);
}
