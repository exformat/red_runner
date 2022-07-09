package com.exformatgames.defender.ecs.engine.components.box2d;

import com.badlogic.ashley.core.*;

public class AngularImpulseComponent implements Component 
{
	public float impulse = 0;
	
	public static ComponentMapper<AngularImpulseComponent> mapper = ComponentMapper.getFor(AngularImpulseComponent.class);
}
