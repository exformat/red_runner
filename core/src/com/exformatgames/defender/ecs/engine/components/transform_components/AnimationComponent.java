package com.exformatgames.defender.ecs.engine.components.transform_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.badlogic.gdx.utils.*;

public class AnimationComponent implements Component {
	public Animation<AtlasRegion> animation;
	public float timeAnimation = 0;
	
	public void initialize(float frameTime, Array<AtlasRegion > regions, Animation.PlayMode mode){
		animation = new Animation<AtlasRegion>(frameTime, regions, mode);
	}
	
	public static ComponentMapper<AnimationComponent> mapper = ComponentMapper.getFor(AnimationComponent.class);
}
