package com.exformatgames.defender.ecs.engine.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class PointSoundComponent implements Component {
	public Vector2 position = new Vector2();
	public float hearingRadius;
	
	public static final ComponentMapper<PointSoundComponent> mapper = ComponentMapper.getFor(PointSoundComponent.class);
}
