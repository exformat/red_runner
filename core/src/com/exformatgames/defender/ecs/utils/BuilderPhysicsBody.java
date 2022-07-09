package com.exformatgames.defender.ecs.utils;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.physics.box2d.*;

public class BuilderPhysicsBody
{
	
	public static Body buildModel(World world, BodyEditorLoader loader, String name, BodyDef.BodyType type, Vector2 position, float scl){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);

		FixtureDef fixtureDef = new FixtureDef();
		fixtureDef.density = 0.55f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.3f;

		loader.attachFixture(body, name, fixtureDef, scl);
		return body;
	}

	public static Body buildModel(World world, BodyEditorLoader loader, String name, BodyDef.BodyType type, FixtureDef fixtureDef, Vector2 position, float scl){
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);

		loader.attachFixture(body, name, fixtureDef, scl);
		return body;
	}

	public static Body buildBox(World world, BodyDef.BodyType type, Vector2 position, float width, float height) {
		BodyDef bodyDef = new BodyDef();
		bodyDef.type = type;
		bodyDef.position.set(position);
		bodyDef.angle = 0;
		Body body = world.createBody(bodyDef);
		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(width / 2, height / 2);
		fixtureDef.shape = shape;
		fixtureDef.density = 0.5f;
		fixtureDef.friction = 0.4f;
		fixtureDef.restitution = 0.6f;
		body.createFixture(fixtureDef);
		shape.dispose();
		return body;
	} 

	public static Body buildBoxSensor(World world, BodyDef.BodyType type, Vector2 position, float width, float height, float deg) { 
		BodyDef bodyDef = new BodyDef(); 
		bodyDef.type = type; 
		bodyDef.position.set(position); 
		bodyDef.angle = deg; 
		Body body = world.createBody(bodyDef); 
		FixtureDef fixtureDef = new FixtureDef(); 
		PolygonShape shape = new PolygonShape(); 
		shape.setAsBox(width / 2, height / 2); 
		fixtureDef.shape = shape; 
		fixtureDef.isSensor = true; 
		body.createFixture(fixtureDef); 
		shape.dispose(); 

		return body; 
	}

	public static Fixture buildSensor(Body body, float width, float height) { 

		FixtureDef fixtureDef = new FixtureDef(); 
		PolygonShape shape = new PolygonShape(); 
		shape.setAsBox(width / 2, height / 2); 
		fixtureDef.shape = shape; 
		fixtureDef.isSensor = true; 
		Fixture fixtureSensor = body.createFixture(fixtureDef); 
		shape.dispose(); 

		return fixtureSensor; 
	}

	public static Body buildCircle(World world, BodyDef.BodyType type, Vector2 position, float radius) { 
		BodyDef bodyDef = new BodyDef(); 
		bodyDef.type = type; 
		Body body = world.createBody(bodyDef); 
		FixtureDef fixtureDef = new FixtureDef(); 
		CircleShape shape = new CircleShape(); 
		shape.setRadius(radius); 
		fixtureDef.shape = shape; 
		fixtureDef.density = 0.5f; 
		fixtureDef.friction = 0.4f; 
		fixtureDef.restitution = 0.6f; 
		body.createFixture(fixtureDef); 
		shape.dispose(); 
		body.setTransform(position, 0); 

		return body; 
	}
	
	public static Body buildCircleSensor(World world, BodyDef.BodyType type, Vector2 position, float radius) { 
		BodyDef bodyDef = new BodyDef(); 
		bodyDef.type = type; 
		bodyDef.position.set(position);
		Body body = world.createBody(bodyDef); 
		FixtureDef fixtureDef = new FixtureDef(); 
		CircleShape shape = new CircleShape(); 
		shape.setRadius(radius); 
		fixtureDef.shape = shape; 
		fixtureDef.isSensor = true;
		body.createFixture(fixtureDef); 
		shape.dispose(); 
		body.setTransform(position, 0); 

		return body; 
	}
}
