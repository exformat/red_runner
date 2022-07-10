package com.exformatgames.defender.ecs.engine;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.*;
import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.systems.audio_systems.*;
import com.exformatgames.defender.ecs.engine.systems.box2d_systems.*;
import com.exformatgames.defender.ecs.engine.systems.debug.*;
import com.exformatgames.defender.ecs.engine.systems.input_systems.*;
import com.exformatgames.defender.ecs.engine.systems.rendering_systems.*;
import com.exformatgames.defender.ecs.engine.systems.transform_systems.*;
import com.exformatgames.defender.ecs.engine.systems.util_system.*;
import com.exformatgames.defender.ecs.utils.EntityBuilder;

public abstract class DefenderCore {
	
	protected OrthographicCamera camera;
	protected World box2DWorld;
	protected SpriteBatch spriteBatch;
	protected InputMultiplexer inputMultiplexer;
	protected TextureAtlas atlas;
	protected AssetManager assetManager;

	protected PooledEngine engine;

	public DefenderCore(OrthographicCamera camera, World box2DWorld, SpriteBatch spriteBatch, InputMultiplexer inputMultiplexer, TextureAtlas atlas, AssetManager assetManager) {
		this.camera = camera;
		this.box2DWorld = box2DWorld;
		this.spriteBatch = spriteBatch;
		this.inputMultiplexer = inputMultiplexer;
		this.atlas = atlas;
		this.assetManager = assetManager;
		
		engine = new PooledEngine(10, 100, 100, 1000);
	}
	
	protected abstract void initEntities();
	protected abstract void initGameSystems();
	
	public void update(float deltaTime){
		engine.update(deltaTime);
	}
	
	public void create(boolean debug){
		EntityBuilder.set(box2DWorld, engine, atlas, camera);
		
		initEntities();//abstract
		
		initInputSystems();
		
		initGameSystems();//abstract
		
		if(box2DWorld != null){
			initBox2DSystems();
		}
		
		initAudioSystems();
		engine.addSystem(new VibrationSystem());//ok
		initAnimationEffectSystems();
		initTransformSystems();
		initParticleSystems();
		initRenderSystems();
		initUtilsSystems();
		
		if(debug)
			initDebugSystems();
		
		engine.addSystem(new ResetTouchInputSystem()); //ok
		engine.addSystem(new ExitSystem()); //ok
		
		
	}
	
	private void initInputSystems(){
		engine.addSystem(new TouchInputSystem(inputMultiplexer));//ok
	}
	
	private void initBox2DSystems(){
		engine.addSystem(new TransformBodySystem()); //ok
		engine.addSystem(new ForceSystem()); //ok
		engine.addSystem(new AngularImpulseSystem()); //ok
		engine.addSystem(new RemoveBodySystem(box2DWorld)); //ok
		engine.addSystem(new RayCastSystem(box2DWorld)); //ok
		engine.addSystem(new UpdateTransformSystem());//new
		engine.addSystem(new UpdateWorldSystem());//ok
		
	}
	
	private void initAudioSystems(){
		engine.addSystem(new SoundSystem()); // ok
		engine.addSystem(new PointSoundSystem(camera)); // ok
		engine.addSystem(new MusicSystem()); // ok
	}
	
	private void initAnimationEffectSystems(){
		engine.addSystem(new AnimationSpriteSystem()); // ok
		engine.addSystem(new ParallaxSystem(camera)); // ok
		engine.addSystem(new ScrollSystem(camera)); // ok
		engine.addSystem(new AngularVelocitySystem()); // ok
		engine.addSystem(new LinearVelocitySystem()); // ok
	}
	
	private void initTransformSystems(){
		engine.addSystem(new RotationSystem()); // ok
		engine.addSystem(new RotateSystem()); // ok
		engine.addSystem(new ScaleSpriteSystem()); // ok
		engine.addSystem(new PositionSystem()); // ok
		engine.addSystem(new TranslateSystem()); // ok
	}
	
	private void initRenderSystems(){
		engine.addSystem(new CullingSystem(camera)); //ok
		engine.addSystem(new SortedRenderSystem(camera, spriteBatch)); //ok
	}
	
	private void initParticleSystems(){
		engine.addSystem(new ParticlesSystem());
		engine.addSystem(new B2DParticleEmmiterUpateSystem());
	}
	
	private void initUtilsSystems(){
		engine.addSystem(new PreferencesSystem()); // new not work!!!
	}
	
	private void initDebugSystems(){
		ShapeRenderer shapeRenderer = new ShapeRenderer();

		if(box2DWorld != null){
			engine.addSystem(new DebugRayCastSystem(camera, shapeRenderer)); //ok
			engine.addSystem(new DebugPhysicsSystem(camera)); //ok
		}
		engine.addSystem(new DebugSpriteSystem(camera, shapeRenderer)); //ok
		engine.addSystem(new DebugShapesSystem(camera, shapeRenderer));
	}

	public PooledEngine getEngine() {
		return engine;
	}
}
