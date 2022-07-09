package com.exformatgames.defender.ecs.utils;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.physics.box2d.*;

public class EntityBuilder {
	protected static World world;
	protected static Engine engine;
	protected static TextureAtlas atlas;
	protected static OrthographicCamera camera;
	
	public static void set(World w, Engine e, TextureAtlas a, OrthographicCamera c){
		EntityBuilder.world = w;
		EntityBuilder.engine = e;
		EntityBuilder.atlas = a;
		EntityBuilder.camera = c;
	}
}
