package com.exformatgames.defender.ecs.engine.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;

public class DebugLightSystem extends IteratingSystem {

	private OrthographicCamera camera;
	private ShapeRenderer ren;
	


	public DebugLightSystem(OrthographicCamera camera, int priority){
		super(Family.all().get(), priority);

		this.camera = camera;
		ren = new ShapeRenderer();
	}

	@Override
	public void update(float deltaTime) {


		ren.setProjectionMatrix(camera.combined);
		
	}



	@Override
	protected void processEntity(Entity entity, float dt) {
		
	}
}
