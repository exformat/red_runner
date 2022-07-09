package com.exformatgames.defender.games_ecs.skedush.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;

public class BonusComponent implements Component {
	
	public Animation<AtlasRegion> animationGem;
	public Animation<AtlasRegion> animationPickup;
	
	public boolean isPicked = false;
	
	public static ComponentMapper<BonusComponent> mapper = ComponentMapper.getFor(BonusComponent.class);
}
