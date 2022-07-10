package com.exformatgames.defender.game.components;

import com.badlogic.ashley.core.*;


public class EnemyComponent implements Component {

	public boolean isDeath = false;
	
	public static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);
}
