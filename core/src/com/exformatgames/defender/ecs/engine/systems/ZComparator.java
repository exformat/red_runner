package com.exformatgames.defender.ecs.engine.systems;

import com.badlogic.ashley.core.*;
import com.exformatgames.defender.ecs.engine.components.rendering_components.*;
import java.util.*;

public class ZComparator implements Comparator<Entity> { 
	
	private ComponentMapper<ZIndexComponent> zIndexMapper; 
	
	public ZComparator(){ 
		zIndexMapper = ComponentMapper.getFor(ZIndexComponent.class); 
	} 
	
	@Override 
	public int compare(Entity entityA, Entity entityB) { 
		return (int) Math.signum(zIndexMapper.get(entityA).zIndex - zIndexMapper.get(entityB).zIndex); 
	}
}

