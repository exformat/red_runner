package com.exformatgames.defender.ecs.engine.systems.input_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.exformatgames.defender.ecs.engine.components.touch_components.*;

public class ResetTouchInputSystem extends IteratingSystem {
	public ResetTouchInputSystem() {
		super(Family.one(GestureTapComponent.class, GesturePanComponent.class, 
						 GestureLongPressComponent.class, GestureZoomComponent.class,
						 GestureRotateComponent.class, GestureFlingComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		GestureTapComponent tapComponent = GestureTapComponent.mapper.get(entity);
		GesturePanComponent panComponent = GesturePanComponent.mapper.get(entity);
		GestureLongPressComponent longPressComponent = GestureLongPressComponent.mapper.get(entity);
		GestureZoomComponent zoomComponent = GestureZoomComponent.mapper.get(entity);
		GestureFlingComponent flingComponent = GestureFlingComponent.mapper.get(entity);
		GestureRotateComponent rotateComponent = GestureRotateComponent.mapper.get(entity);

		if (tapComponent != null) {
			tapComponent.position.setZero();
			tapComponent.count = 0;
		}


		if (panComponent != null) {
			panComponent.position.setZero();
			panComponent.delta.setZero();
			panComponent.direction.setZero();
			panComponent.stop.setZero();
		}


		if (longPressComponent != null)
			longPressComponent.position.setZero();


		if (zoomComponent != null) {
			zoomComponent.initialDistance = 0;
			zoomComponent.endDistance = 0;
		}


		if (flingComponent != null){
			flingComponent.velocity.setZero();
			flingComponent.up = false;
			flingComponent.down = false;
			flingComponent.left = false;
			flingComponent.right = false;
			flingComponent.sideFling = GestureFlingComponent.SideFling.NULL;
		}

		if (rotateComponent != null) {
			rotateComponent.rotationPoint.setZero();
			rotateComponent.degres = 0;
		}
	}
}
