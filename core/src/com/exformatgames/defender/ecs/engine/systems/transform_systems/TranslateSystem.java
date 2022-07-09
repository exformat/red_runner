package com.exformatgames.defender.ecs.engine.systems.transform_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.exformatgames.defender.ecs.engine.components.audio_components.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;
import com.exformatgames.defender.ecs.engine.components.light_components.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.components.transform_components.*;

import static com.badlogic.gdx.graphics.g2d.SpriteBatch.*;

public class TranslateSystem extends IteratingSystem {

	public TranslateSystem() {
		super(Family.all(TranslateComponent.class).get());
	}

	@Override
	protected void processEntity(Entity entity, float dt) {
		TranslateComponent translate = TranslateComponent.mapper.get(entity);
		
		SpriteComponent spriteComponent = SpriteComponent.mapper.get(entity);
		ParticleEmitterComponent particleComponent = ParticleEmitterComponent.mapper.get(entity);
		B2DParticleEmitterComponent b2DParticleComponent = B2DParticleEmitterComponent.mapper.get(entity);
		PointSoundComponent pointSoundComponent = PointSoundComponent.mapper.get(entity);
		LightComponent lightComponent = LightComponent.mapper.get(entity);
		CameraComponent cameraComponent = CameraComponent.mapper.get(entity);
		
		if(particleComponent != null){
			float x = particleComponent.emitter.getX() + translate.x;
			float y = particleComponent.emitter.getY() + translate.y;
			
			particleComponent.emitter.setPosition(x, y);
		}
		
		if(b2DParticleComponent != null){
			float x = b2DParticleComponent.emitter.getX() + translate.x;
			float y = b2DParticleComponent.emitter.getY() + translate.y;
			
			b2DParticleComponent.emitter.setPosition(x, y);
		}
		
		if(pointSoundComponent != null){
			pointSoundComponent.position.add(translate.x, translate.y);
		}
		
		if(lightComponent != null && lightComponent.light.getBody() == null){
			Vector2 position = lightComponent.light.getPosition();
			position.add(translate.x, translate.y);
			lightComponent.light.setPosition(position);
		}
		
		if(cameraComponent != null){
			cameraComponent.camera.translate(translate.x, translate.y);
			cameraComponent.camera.update();
		}
		
		if(spriteComponent != null){
			spriteComponent.x += translate.x;
			spriteComponent.y += translate.y;

			if (spriteComponent.dirty){
				entity.remove(TranslateComponent.class);
				return;
			} 
			if (spriteComponent.rotation != 0 || spriteComponent.scaleX != 1 || spriteComponent.scaleY != 1) {			
				spriteComponent.dirty = true;			
				entity.remove(TranslateComponent.class);
				return;		
			} 		

			float[] vertices = spriteComponent.vertices;		

			vertices[X1] += translate.x;		
			vertices[Y1] += translate.y; 		
			vertices[X2] += translate.x;		
			vertices[Y2] += translate.y; 		
			vertices[X3] += translate.x;		
			vertices[Y3] += translate.y; 		
			vertices[X4] += translate.x;		
			vertices[Y4] += translate.y;
		}
		
		entity.remove(TranslateComponent.class);
	}
}

