package com.exformatgames.defender.games_ecs.skedush.entityes;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.ecs.utils.EntityBuilder;
import com.exformatgames.defender.games_ecs.skedush.audio.*;
import com.exformatgames.defender.games_ecs.skedush.components.*;

public class BonusEntityBuilder extends EntityBuilder {
	
	public static Entity build(Vector2 position){
		Entity entity = engine.createEntity();
		
		SpriteComponent spriteComponent = engine.createComponent(SpriteComponent.class);
		ZIndexComponent zIndexComponent = engine.createComponent(ZIndexComponent.class);
		CullingComponent cullingComponent = engine.createComponent(CullingComponent.class);
		AnimationComponent animationComponent = engine.createComponent(AnimationComponent.class);
		BonusComponent bonusComponent = engine.createComponent(BonusComponent.class);
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		ParallaxComponent parallaxComponent = engine.createComponent(ParallaxComponent.class);
		SoundComponent soundComponent = engine.createComponent(SoundComponent.class);

		//FIXME need animation pool..
		bonusComponent.animationGem = new Animation<>(1f / 5, atlas.findRegions("gem"), Animation.PlayMode.LOOP);
		bonusComponent.animationPickup = new Animation<>(1f / 8, atlas.findRegions("item_pickup"), Animation.PlayMode.NORMAL);
		
		animationComponent.animation = bonusComponent.animationGem;
		
		zIndexComponent.zIndex = 8;
		parallaxComponent.layer = 5;
		parallaxComponent.vertical = true;
		parallaxComponent.horizontal = true;
		
		positionComponent.x = position.x;
		positionComponent.y = position.y;
		
		soundComponent.sound = Sounds.pickedBonusSound;
		soundComponent.isLooping = false;
		soundComponent.volume = 0.3f;
		
		entity.add(spriteComponent);
		entity.add(zIndexComponent);
		entity.add(cullingComponent);
		entity.add(animationComponent);
		entity.add(bonusComponent);
		entity.add(positionComponent);
		entity.add(parallaxComponent);
		entity.add(soundComponent);
		
		return entity;
	}
}
