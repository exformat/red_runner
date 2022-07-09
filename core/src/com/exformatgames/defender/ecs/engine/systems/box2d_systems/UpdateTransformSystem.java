package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

public class UpdateTransformSystem extends IteratingSystem {
	
	public static float FRAME_TIME = 0;
	private long startTime = 0;

	public UpdateTransformSystem(){
		super(Family.all(BodyComponent.class).get());
	}

	@Override
	public void startProcessing() {
		startTime = TimeUtils.nanoTime();
	}

	@Override
	public void endProcessing() {
		FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
	}
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		BodyComponent bodyComponent = BodyComponent.mapper.get(entity);
		SizeComponent sizeComponent = SizeComponent.mapper.get(entity);
		
		Vector2 position = bodyComponent.body.getPosition();
		float angle = bodyComponent.body.getAngle() * MathUtils.radiansToDegrees;

		PositionComponent positionComponent = getEngine().createComponent(PositionComponent.class);
		RotationComponent rotationComponent = getEngine().createComponent(RotationComponent.class);
		
		positionComponent.x = position.x - sizeComponent.halfWidth;
		positionComponent.y = position.y - sizeComponent.halfHeight;
		rotationComponent.degress = angle;
		
		entity.add(positionComponent);
		entity.add(rotationComponent);

		bodyComponent.oldPosition.set(position);
		bodyComponent.oldRotation = angle;
	}
}


