package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class RemoveBodySystem extends IteratingSystem
{
	private final World world;
	
	public RemoveBodySystem(World world){
		super(Family.all(RemoveBodyComponent.class).get());
		this.world = world;
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);
		
		bodyComponent.body.setActive(false);
		world.destroyBody(bodyComponent.body);
		
		entity.remove(BodyComponent.class);
		entity.remove(RemoveBodyComponent.class);
	}
}
