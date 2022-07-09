package com.exformatgames.defender.ecs.engine.systems.debug;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.badlogic.gdx.math.*;

public class DebugSpriteSystem extends IteratingSystem
{

	private final OrthographicCamera camera;
	private final ShapeRenderer shapeRenderer;
	
	public DebugSpriteSystem(OrthographicCamera camera, ShapeRenderer shapeRenderer) {
		super(Family.all(SpriteComponent.class).get());

		this.camera = camera;
		this.shapeRenderer = shapeRenderer;
	}

	@Override
	public void update(float deltaTime) {
		shapeRenderer.setProjectionMatrix(camera.combined);
		
		shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
		shapeRenderer.setColor(Color.CORAL);
		
		super.update(deltaTime);
		
		shapeRenderer.end();
	}
	
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		
		Rectangle rect = spriteComponent.getBoundingRectangle();
		
		shapeRenderer.rect(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}
}
