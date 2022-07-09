package com.exformatgames.defender.ecs.engine.systems.input_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.input.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.*;
import com.exformatgames.defender.ecs.engine.components.touch_components.*;
import com.exformatgames.defender.ecs.engine.systems.util_system.*;

public class TouchInputSystem extends EventSystem {

	private TouchEvent currentEvent;

	private final Vector3 tap = new Vector3(0, 0, 0);

	private final Vector2 pan = new Vector2(0, 0);
	private final Vector2 panDelta = new Vector2(0, 0);
	private final Vector2 panStop = new Vector2(0, 0);

	private final Vector2 longPress = new Vector2(0, 0);

	private float zoomInitialDistance;
	private float zoomDistance;
	private float zoomDelta;

	private final Vector2 fling = new Vector2(0, 0);

	private final Vector2 rotationPoint = new Vector2(0, 0);
	private float rotateValue = 0;
	private float rotationValue = 0;
	private float rotationOld = 0;




	public TouchInputSystem(InputMultiplexer inputMultiplexer) {
		super(Family.one(GestureTapComponent.class, GesturePanComponent.class, 
						 GestureLongPressComponent.class, GestureZoomComponent.class,
						 GestureRotateComponent.class, GestureFlingComponent.class).get());

		new InputGestures(inputMultiplexer);
	}

	@Override
	public void update() {

		super.update();

		currentEvent = TouchEvent.NULL;
	}

	@Override
	protected void processEntity(Entity entity) {

		switch (currentEvent) {
			case TAP : {
					GestureTapComponent tapComponent = GestureTapComponent.mapper.get(entity);
					if (tapComponent != null) {
						tapComponent.position.set(tap.x, tap.y);
						tapComponent.count = (int)tap.z;
					}

					break;
				}

			case PAN : {
					GesturePanComponent panComponent = GesturePanComponent.mapper.get(entity);
					if (panComponent != null) {
						panComponent.position.set(pan.x, pan.y);
						panComponent.delta.set(panDelta.x, panDelta.y);
						panComponent.direction.set(panDelta.x, panDelta.y);
						panComponent.direction.nor();
					}

					break;
				}

			case PAN_STOP : {
					GesturePanComponent panComponent = GesturePanComponent.mapper.get(entity);
					if (panComponent != null)
						panComponent.stop.set(panStop.x, panStop.y);

					break;
				}

			case LONG : {
					GestureLongPressComponent longPressComponent = GestureLongPressComponent.mapper.get(entity);
					if (longPressComponent != null)
						longPressComponent.position.set(longPress.x, longPress.y);

					break;
				}

			case ZOOM : {
					GestureZoomComponent zoomComponent = GestureZoomComponent.mapper.get(entity);
					if (zoomComponent != null) {
						zoomComponent.initialDistance = zoomInitialDistance;
						zoomComponent.endDistance = zoomDistance;

						zoomComponent.delta = zoomDelta;
					}

					break;
				}

			case FLING : {
					GestureFlingComponent flingComponent = GestureFlingComponent.mapper.get(entity);
					if (flingComponent != null) {
						flingComponent.velocity.set(fling.x, fling.y);

						if (fling.y > 0 && fling.y > Math.abs(fling.x)){
							flingComponent.up = true;
							flingComponent.sideFling = GestureFlingComponent.SideFling.UP;
						}

						if (fling.y < 0 && Math.abs(fling.y) > Math.abs(fling.x)){
							flingComponent.down = true;
							flingComponent.sideFling = GestureFlingComponent.SideFling.DOWN;
						}

						if (fling.x > 0 && fling.x > Math.abs(fling.y)){
							flingComponent.right = true;
							flingComponent.sideFling = GestureFlingComponent.SideFling.RIGHT;
						}

						if (fling.x < 0 && Math.abs(fling.x) > Math.abs(fling.y)){
							flingComponent.left = true;
							flingComponent.sideFling = GestureFlingComponent.SideFling.LEFT;
						}
					}
					break;
				}

			case ROTATE : {
					GestureRotateComponent rotateComponent = GestureRotateComponent.mapper.get(entity);
					if (rotateComponent != null) {
						rotateComponent.rotationPoint.set(rotationPoint.x, rotationPoint.y);
						rotateComponent.degres = rotationValue;
						rotateComponent.delta = rotateValue;
					}

					break;
				}
		}
	}

	private class InputGestures implements GestureDetector.GestureListener {

		public InputGestures(InputMultiplexer inputMultiplexer) {
			inputMultiplexer.addProcessor(new GestureDetector(this));
		}

		@Override
		public boolean tap(float x, float y, int count, int button) {
			//Gdx.app.log("INPUT SYSTEM", "TAP" );
			y = Gdx.graphics.getHeight() - y;

			tap.set(x, y, 0);
			tap.scl(Constants.SCL);
			tap.z = count;

			currentEvent = TouchEvent.TAP;
			update();

			return false;
		}

		@Override
		public boolean pan(float x, float y, float deltaX, float delteY) {
			//Gdx.app.log("INPUT SYSTEM", "PAN" );
			y = Gdx.graphics.getHeight() - y;

			pan.set(x, y);
			pan.scl(Constants.SCL);
			panDelta.set(deltaX, delteY * -1);
			panDelta.scl(Constants.SCL);

			currentEvent = TouchEvent.PAN;
			update();

			return false;
		}

		@Override
		public boolean longPress(float x, float y) { 
			//Gdx.app.log("INPUT SYSTEM", "LONG" );
			y = Gdx.graphics.getHeight() - y;

			longPress.set(x, y);
			longPress.scl(Constants.SCL);

			currentEvent = TouchEvent.LONG;
			update();

			return true;
		}

		@Override
		public boolean zoom(float initialDistance, float distance) { 
			//Gdx.app.log("INPUT SYSTEM", "ZOOM i: "  + initialDistance + " d: " + distance);
			initialDistance *= Constants.SCL;
			distance *= Constants.SCL;

			float differenceDistance = zoomDistance / distance;
			if ((differenceDistance > 1.02f || differenceDistance < 0.98f) &&
				(differenceDistance > 0.9f || differenceDistance < 1.1f)) {

				float zoomBasis = 0.135f;
				zoomDelta = differenceDistance < 1 ? zoomBasis : -zoomBasis;

				zoomInitialDistance = initialDistance;
				zoomDistance = distance;

				currentEvent = TouchEvent.ZOOM;

				update();
			}

			return false;
		}

		@Override
		public boolean fling(float velocityX, float velocityY, int button) {
			//Gdx.app.log("INPUT SYSTEM", "FLING" );

			fling.set(velocityX, velocityY * -1);
			fling.scl(Constants.SCL);
			currentEvent = TouchEvent.FLING;

			update();

			return false;
		}

		@Override
		public boolean panStop(float x, float y, int pointer, int button) {
			//Gdx.app.log("INPUT SYSTEM", "PAN STOP" );
			y = Gdx.graphics.getHeight() - y;

			panStop.set(x, y);
			panStop.scl(Constants.SCL);
			currentEvent = TouchEvent.PAN_STOP;

			update();

			return false;
		}

		@Override
		public boolean pinch(Vector2 initial1, Vector2 initial2, Vector2 end1, Vector2 end2) {
			//Gdx.app.log("INPUT SYSTEM", "PINCH" );

			Intersector.intersectLines(initial1, initial2, end1, end2, rotationPoint);

			initial2.sub(initial1);
			end2.sub(end1);
				float init = MathUtils.atan2(initial2.y, initial2.x);
				float current = MathUtils.atan2(end2.y, end2.x);
			initial2.add(initial1);
			end2.add(end1);

			current = (init - current) * MathUtils.radiansToDegrees;
			
			rotateValue = (current - rotationOld) * 2;
			rotationValue = current;
			rotationOld = current;
			currentEvent = TouchEvent.ROTATE;

			update();
			
			return false;
		}

		@Override
		public void pinchStop() {
			//Gdx.app.log("INPUT SYSTEM", "PINCH STOP" );
		}

		@Override
		public boolean touchDown(float x, float y, int pointer, int button) {
			return false;
		}
	}

	private enum TouchEvent {
		TAP,
		PAN,
		PAN_STOP,
		LONG,
		ZOOM,
		FLING,
		ROTATE,
		NULL
	}
}

	
