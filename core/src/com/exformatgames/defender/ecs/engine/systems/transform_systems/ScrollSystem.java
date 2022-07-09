package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.systems.*;
import com.badlogic.ashley.core.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.badlogic.gdx.graphics.*;

public class ScrollSystem extends IteratingSystem {

	private final OrthographicCamera camera;
	
	public ScrollSystem(OrthographicCamera camera){
		super(Family.all(ScrollComponent.class).get());
		this.camera = camera;
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		ScrollComponent scrollComponent = ScrollComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity); // hmmmmmmm not good
		TranslateComponent translateComponent = TranslateComponent.mapper.get(entity);
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		
		if(spriteComponent != null && !cullingComponent.inViewport){
			if(spriteComponent.x < camera.position.x - camera.viewportWidth / 2){//TODO || spriteComponent.y < camera.position.y - camera.viewportHeight / 2){
				float x = scrollComponent.horizontal ? scrollComponent.moveByX : 0;
				float y = scrollComponent.vertical ? scrollComponent.moveByY : 0;

				if(translateComponent == null){
					translateComponent = getEngine().createComponent(TranslateComponent.class);
					translateComponent.x = x;
					translateComponent.y = y;

					entity.add(translateComponent);
				}else{
					translateComponent.x += x;
					translateComponent.y += y;
				}
			}
		}
	}
}
