package com.exformatgames.defender.games_ecs.skedush.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;


public class EnemyComponent implements Component {

	public boolean isDeath = false;
	
	public static ComponentMapper<EnemyComponent> mapper = ComponentMapper.getFor(EnemyComponent.class);
}
