package com.exformatgames.defender.game.game_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.game.components.*;
import com.exformatgames.defender.game.entityes.*;

public class BonusSpawnSystem extends IntervalIteratingSystem {

	private OrthographicCamera camera;

	public BonusSpawnSystem(OrthographicCamera camera) {
		super(Family.all(BonusComponent.class).get(), 1f / 3);
		this.camera = camera;
	}

	@Override
	public void startProcessing() {
		if(camera.position.x > 20 && MathUtils.random() > 0.5f){
			Vector2 position = Pools.obtain(Vector2.class);
			
			float x = camera.position.x + 15;
			float y = MathUtils.random() > 0.5 ? 3.2f : 4.5f;
			
			position.set(x, y);

			getEngine().addEntity(BonusEntityBuilder.build(position));
			
			Pools.free(position);
		}
	}


	@Override
	protected void processEntity(Entity entity) {
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		BonusComponent bonusComponent = BonusComponent.mapper.get(entity);
		
		if (cullingComponent.inViewport && spriteComponent.x < camera.position.x - camera.viewportWidth / 2) {
			getEngine().removeEntity(entity);
			return;
		}
		
		if(bonusComponent.isPicked && animationComponent.animation.isAnimationFinished(animationComponent.timeAnimation)){
			bonusComponent.isPicked = false;
			getEngine().removeEntity(entity);
		}
	}
}
