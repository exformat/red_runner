package com.exformatgames.defender.games_ecs.skedush.entityes;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.EntityBuilder;
import com.exformatgames.defender.games_ecs.skedush.components.*;

public class BackgroundEntityBuilder extends EntityBuilder {
	
	public static Entity build(Vector2 position, int layer){
		Entity entity = engine.createEntity();
		
		SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
		ZIndexComponent zIndexComponent = engine.createComponent(ZIndexComponent.class);
		ParallaxComponent parallaxComponent = engine.createComponent(ParallaxComponent.class);
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		BackgroundComponent backgroundComponent = engine.createComponent(BackgroundComponent.class);
		CullingComponent cullingComponent = engine.createComponent(CullingComponent.class);
		ScrollComponent scrollComponent = engine.createComponent(ScrollComponent.class);
		
		spriteComponent.initialize(atlas.findRegion("forest", layer), 0.025f);
		
		zIndexComponent.zIndex = layer;
		
		parallaxComponent.layer = 12 - layer;
		parallaxComponent.horizontal = true;
		parallaxComponent.vertical = true;
		
		positionComponent.x = position.x;
		positionComponent.y = position.y;
		
		scrollComponent.horizontal = true;
		scrollComponent.moveByX = spriteComponent.width * 2;
		
		entity.add(spriteComponent);
		entity.add(zIndexComponent);
		entity.add(parallaxComponent);
		entity.add(positionComponent);
		entity.add(backgroundComponent);
		entity.add(cullingComponent);
		entity.add(scrollComponent);
		
		return entity;
	}
}
