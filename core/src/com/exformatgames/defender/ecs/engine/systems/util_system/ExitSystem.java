package com.exformatgames.defender.ecs.engine.systems.util_system;
import com.badlogic.ashley.systems.*;
import com.badlogic.ashley.core.*;
import com.exformatgames.defender.ecs.engine.components.*;
import com.badlogic.gdx.*;

public class ExitSystem extends IteratingSystem
{

	public ExitSystem(){
		super(Family.all(ExitComponent.class).get());
	}
	
	@Override
	protected void processEntity(Entity entity, float dt) {
		System.out.println("exit");
		
		Gdx.app.exit();
	}
}
