package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class TransformBodySystem extends IteratingSystem {

	public TransformBodySystem() {
		super(Family.all(TransformBodyComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		TransformBodyComponent transform = TransformBodyComponent.mapper.get(entity);
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);

		bodyComponent.body.setTransform(transform.position, transform.angle);
		bodyComponent.body.setLinearVelocity(transform.linearVelocity);
		bodyComponent.body.setAngularVelocity(transform.angularVelocity);
		bodyComponent.body.setAwake(transform.awake);

		entity.remove(TransformBodyComponent.class);
	}
}
