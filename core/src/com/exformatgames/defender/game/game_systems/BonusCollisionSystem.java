package com.exformatgames.defender.game.game_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;
import com.exformatgames.defender.ecs.engine.components.math_components.*;
import com.exformatgames.defender.ecs.engine.components.platform_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.game.components.*;

public class BonusCollisionSystem extends IteratingSystem {
	
	private Entity playerEntity;
	
	public BonusCollisionSystem(){
		super(Family.one(BonusComponent.class, PlayerComponent.class).get());
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
		if(playerComponent != null){
			playerEntity = entity;
			return;
		}
		
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		BonusComponent bonusComponent = BonusComponent.mapper.get(entity);
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		SoundComponent soundComponent = SoundComponent.mapper.get(entity);
		
		if(cullingComponent.inViewport) {
			CircleComponent playerCircle = CircleComponent.mapper.get(playerEntity);
			playerComponent = PlayerComponent.mapper.get(playerEntity);
			
			boolean isCollision = Intersector.overlaps(playerCircle.circle, spriteComponent.getBoundingRectangle());
			
			if(isCollision && ! bonusComponent.isPicked) {
				animationComponent.animation = bonusComponent.animationPickup;
				animationComponent.timeAnimation = 0;
				bonusComponent.isPicked = true;
				TranslateComponent translateComponent = getEngine().createComponent(TranslateComponent.class);
				translateComponent.x = -(32*Constants.SCL / 2) + spriteComponent.width / 2;
				translateComponent.y = -(32*Constants.SCL / 2) + spriteComponent.height / 2;
				entity.add(translateComponent);
				
				playerComponent.gems++;
				soundComponent.play = true;
				
				VibrationComponent vibrationComponent = getEngine().createComponent(VibrationComponent.class);
				vibrationComponent.millis = 40;
				entity.add(vibrationComponent);
			}
		}
	}
}
