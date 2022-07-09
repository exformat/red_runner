package com.exformatgames.defender.ecs.engine.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.components.box2d.WorldComponent;

public class DebugPhysicsSystem extends IteratingSystem
{

	private final Box2DDebugRenderer debugRenderer;
	private final OrthographicCamera camera;

	public DebugPhysicsSystem(OrthographicCamera camera) {
		super(Family.all(WorldComponent.class).get());
		debugRenderer = new Box2DDebugRenderer();
		this.camera = camera;
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		WorldComponent worldComponent = WorldComponent.mapper.get(entity);
		debugRenderer.render(worldComponent.world, camera.combined);
	}
}
