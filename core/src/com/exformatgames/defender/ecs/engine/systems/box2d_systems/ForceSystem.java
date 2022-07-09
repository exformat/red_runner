package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class ForceSystem extends IteratingSystem
{

	public ForceSystem(){
		super(Family.all(ForceComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		ForceComponent forceComponent = ForceComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);
		
		if(forceComponent.center){
			bodyComponent.body.applyForceToCenter(forceComponent.force, true);
		}
		else{
			bodyComponent.body.applyForce(forceComponent.force, forceComponent.point, true);
		}
		
		entity.remove(ForceComponent.class);
	}
}
