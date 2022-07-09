package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;

public class LightRenderSystem extends IteratingSystem {
	
	private OrthographicCamera camera;
	public static RayHandler rayHandler;

	public LightRenderSystem(OrthographicCamera camera, World world) {
		super(Family.all().get());
		this.camera = camera;
		
		rayHandler = new RayHandler(world);
		rayHandler.setAmbientLight(0.7f, 0.7f, 0.7f, 0.9f);

		rayHandler.setGammaCorrection(true);		
		rayHandler.useDiffuseLight(true);
		rayHandler.setBlurNum(3);
		rayHandler.setCulling(true);
	}
	
	public LightRenderSystem(OrthographicCamera camera, RayHandler rayHandler) {
		super(Family.all().get());
		
		this.camera = camera;
		this.rayHandler = rayHandler;
	}

	@Override
	public void endProcessing() {
		rayHandler.setCombinedMatrix(camera.combined);
		rayHandler.updateAndRender();
	}

	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		
	}
}
