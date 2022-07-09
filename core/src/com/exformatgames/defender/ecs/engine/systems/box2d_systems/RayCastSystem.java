package com.exformatgames.defender.ecs.engine.systems.box2d_systems;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.systems.*;
import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;
import com.exformatgames.defender.ecs.engine.components.box2d.*;

public class RayCastSystem extends IteratingSystem {

	private final RayCastCallback callback;
	
	private final Vector2 collisionPoint = new Vector2();
	private Fixture collisionFixture;
	private float closestFraction = 8;
	
	private final World world;
	
	public RayCastSystem(World world){
		super(Family.all(RayComponent.class).get());
		
		this.world = world;
		
		callback = new RayCastCallback(){
			@Override 
			public float reportRayFixture(Fixture fixture, Vector2 point, Vector2 normal, float fraction) { 
				if(fraction < RayCastSystem.this.closestFraction) { 
					RayCastSystem.this.closestFraction = fraction; 
					RayCastSystem.this.collisionPoint.set(point); 
					RayCastSystem.this.collisionFixture = fixture;
				}

				return 1;
			}
		};
	}
	
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		RayComponent rayComponent = RayComponent.mapper.get(entity);
		
		closestFraction = rayComponent.maxRayLenght;
		
		world.rayCast(callback, rayComponent.fromPoint, rayComponent.toPoint);

		//TODO без проверки и обнуления в конце, collisionPoint будет висеть на старой фикстуре
		if(collisionFixture != null){
			rayComponent.collisionFixture = collisionFixture;
			rayComponent.collisionPoint.set(collisionPoint);
			
			rayComponent.lenght = rayComponent.fromPoint.dst(collisionPoint);
		}
		else{
			rayComponent.collisionFixture = null;
			rayComponent.collisionPoint.set(rayComponent.toPoint);
			//TODO хз откуда берутся лишние 1.5..
			rayComponent.lenght = rayComponent.maxRayLenght - 1.5f;
		}
		
		collisionPoint.set(0,0);
		collisionFixture = null;
	}
}
