package com.exformatgames.defender.ecs.engine.systems.util_system;

import com.badlogic.ashley.core.*;
import com.badlogic.ashley.utils.*;

public abstract class EventSystem extends EntitySystem {
	
	private Family family;	
	protected ImmutableArray<Entity> entities;
	
	public EventSystem (Family family, int priority) {		
		super(priority);		
		this.family = family;	
	}
	
	public EventSystem (Family family) {		
		super();		
		this.family = family;	
	}
	
	public void startProcessing() {}
	public void endProcessing() {}

	@Override
	public void update(float deltaTime) {}
	
	public void update(){
		for (int i = 0; i < entities.size(); ++i) {			
			processEntity(entities.get(i));		
		}
	}
	
	protected abstract void processEntity(Entity entity);
	
	@Override	
	public void addedToEngine (Engine engine) {		
		entities = engine.getEntitiesFor(family);	
	}
	
	public ImmutableArray<Entity> getEntities () {		
		return entities;	
	}
	
	public Family getFamily () {		
		return family;	
	}
	
	public void setFamily (Family family) {		
		this.family = family;
	}
}
