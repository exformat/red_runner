package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class AngularImpulseSystem extends IteratingSystem
{

	public AngularImpulseSystem(){
		super(Family.all(AngularImpulseComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		AngularImpulseComponent angularImpulseComponent = AngularImpulseComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

		bodyComponent.body.applyAngularImpulse(angularImpulseComponent.impulse, true);

		entity.remove(AngularImpulseComponent.class);
	}
}
