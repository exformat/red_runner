package com.exformatgames.defender.ecs.engine.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.exformatgames.defender.ecs.engine.components.math_components.*;
import com.badlogic.ashley.systems.*;

public class DebugShapesSystem extends IteratingSystem{
	
	private OrthographicCamera camera;
	private ShapeRenderer shapeRenderer;

	public DebugShapesSystem(OrthographicCamera camera, ShapeRenderer shapeRenderer) {
		super(Family.one(CircleComponent.class).get());

		this.camera = camera;
		this.shapeRenderer = shapeRenderer;
	}

	@Override
	public void update(float deltaTime) {

		shapeRenderer.setProjectionMatrix(camera.combined);

		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.GREEN);

		super.update(deltaTime);

		shapeRenderer.end();

	}



	@Override
	protected void processEntity(Entity entity, float dt) {
		CircleComponent circleComponent = CircleComponent.mapper.get(entity);
		if(circleComponent != null){
			shapeRenderer.circle(circleComponent.circle.x, circleComponent.circle.y, circleComponent.circle.radius, 25);
		}
	}
}
