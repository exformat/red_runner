package com.exformatgames.defender.ecs.engine.components.box2d;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.physics.box2d.*;

public class WorldComponent implements Component
{
	public World world;
	public float stepTime = 1f / 90f;
	public int velocityIter = 8;
	public int positionIter = 8;
	
	public static ComponentMapper<WorldComponent> mapper = ComponentMapper.getFor(WorldComponent.class);
}
