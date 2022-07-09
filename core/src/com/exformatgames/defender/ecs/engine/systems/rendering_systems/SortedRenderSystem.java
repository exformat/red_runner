package com.exformatgames.defender.ecs.engine.systems.rendering_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.utils.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import com.exformatgames.defender.ecs.engine.systems.*;
import com.exformatgames.defender.ecs.utils.*;

public class SortedRenderSystem extends SortedIteratingSystem {

	private final SpriteBatch batch;
	private final OrthographicCamera camera;

	public static float FRAME_TIME = 0;
	private long startTime = 0;
	
	public SortedRenderSystem(OrthographicCamera camera, SpriteBatch batch) {
		super(Family.all(SpriteComponent.class).get(), new ZComparator());
		
		this.batch = batch;
		this.camera = camera;
	}

	@Override
	public void startProcessing() {
		startTime = TimeUtils.nanoTime();
		
		batch.setProjectionMatrix(camera.combined); 
		batch.begin(); 
	}

	@Override
	public void endProcessing() {
		batch.end();
		FRAME_TIME = (TimeUtils.nanoTime() - startTime) / 1000_000f;
	}
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		SpriteComponent component = SpriteComponent.mapper.get(entity);
		ParticleEffectComponent particleComponent = ParticleEffectComponent.mapper.get(entity);
		B2DParticleEmitterComponent b2dEmitter = B2DParticleEmitterComponent.mapper.get(entity);
		ShaderComponent shaderComponent = ShaderComponent.mapper.get(entity);
		LightRenderComponent lightRenderComponent = LightRenderComponent.mapper.get(entity);
		CullingComponent cullingComponent = CullingComponent.mapper.get(entity);
		
		
		
		if(cullingComponent == null || cullingComponent.inViewport){
			applyShader(shaderComponent);
			batch.draw(component.texture, component.getVertices(), 0, SpriteComponent.SPRITE_SIZE);
		}
			
		if(shaderComponent != null)
			batch.setShader(null);

		if(particleComponent != null)
			particleComponent.effect.draw(batch);
		
		if(b2dEmitter != null)
			b2dEmitter.emitter.draw(batch);
		
		if(lightRenderComponent != null){
			batch.end();
			lightRenderComponent.rayHandler.setCombinedMatrix(camera);
			lightRenderComponent.rayHandler.updateAndRender();
			batch.begin();
		}
	}
	
	private void applyShader(ShaderComponent shaderComponent){
		if(shaderComponent != null){
			batch.setShader(shaderComponent.shader);
			for(int i = 0; i < shaderComponent.uniforms.size(); i++){
				Uniform uniform = shaderComponent.uniforms.get(i);
				if(uniform.vec2 != null){
					shaderComponent.shader.setUniformf(uniform.location, uniform.vec2);
				}
				else if(uniform.vec3 != null){
					shaderComponent.shader.setUniformf(uniform.location, uniform.vec3);
				}
				else if(uniform.color != null){
					shaderComponent.shader.setUniformf(uniform.location, uniform.color);
				}
				else {
					shaderComponent.shader.setUniformf(uniform.location, uniform.value);
				}
			}
		}
	}
}
