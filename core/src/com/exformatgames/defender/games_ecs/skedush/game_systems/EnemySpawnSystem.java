package com.exformatgames.defender.games_ecs.skedush.game_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.utils.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;
import com.exformatgames.defender.games_ecs.skedush.components.*;
import com.exformatgames.defender.games_ecs.skedush.entityes.*;

public class EnemySpawnSystem extends IntervalIteratingSystem {
	
	private OrthographicCamera camera;
	private float defaultCamPosY = 0;
	
	public EnemySpawnSystem(OrthographicCamera camera){
		super(Family.all(EnemyComponent.class).get(), 1.5f);
		this.camera = camera;
		defaultCamPosY = camera.position.y;
	}

	@Override
	public void startProcessing() {
		if(camera.position.x > 30 && MathUtils.random() > 0.5f){
			Vector2 position = Pools.obtain(Vector2.class);

			float x = camera.position.x + 25;
			float y = MathUtils.random() > 0.5 ? 3 : 4.5f;


			EnemyType enemyType = EnemyType.EAGLE;
			
			int random = MathUtils.random(1, 3);
			
			switch(random){
				case 1 : {// opossum
					y = 3;
					enemyType = EnemyType.OPOSSUM;
					break;
				}
				
				case 2 : {// eagle
					y = 4.2f;
					enemyType = EnemyType.EAGLE;
					break;
				}
				
				case 3 : {// frog
						y = 3;
						enemyType = EnemyType.FROG;
						break;
					}
			}
			
			//need for compensation parallax
			float offsetY = (camera.position.y - defaultCamPosY) * (1f / 5); //5 number parallax layer
			y -= offsetY;
			
			position.set(x, y);
			
			getEngine().addEntity(EnemyEntityBuilder.build(position, enemyType));

			Pools.free(position);
		}
	}
	
	@Override
	protected void processEntity(Entity entity) {
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		EnemyComponent enemyComponent = EnemyComponent.mapper.get(entity);
		AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
		
		if (cullingComponent.inViewport && spriteComponent.x < camera.position.x - camera.viewportWidth / 2) {
			getEngine().removeEntity(entity);
		}
		
		if(enemyComponent.isDeath && animationComponent.animation.isAnimationFinished(animationComponent.timeAnimation)){
			enemyComponent.isDeath = false;
			getEngine().removeEntity(entity);
		}
	}
}
