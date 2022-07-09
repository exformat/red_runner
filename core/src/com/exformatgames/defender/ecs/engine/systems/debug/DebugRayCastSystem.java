package com.exformatgames.defender.ecs.engine.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;

public class DebugRayCastSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	private final ShapeRenderer ren;

	public DebugRayCastSystem(OrthographicCamera camera, ShapeRenderer ren) {
		super(Family.all(RayComponent.class).get());
		this.camera = camera;
		this.ren = ren;
	}

	@Override
	public void update(float deltaTime) {
		ren.setProjectionMatrix(camera.combined);
		ren.begin(ShapeRenderer.ShapeType.Line);
		
		super.update(deltaTime);
		
		ren.end();
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		RayComponent ray = RayComponent.mapper.get(entity);

		ren.setColor(Color.BLUE);
		ren.line(ray.fromPoint, ray.toPoint);
		
		ren.setColor(Color.RED);
		ren.circle(ray.collisionPoint.x, ray.collisionPoint.y, 0.3f);
	}
}
