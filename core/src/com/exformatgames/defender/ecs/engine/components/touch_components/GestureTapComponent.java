package com.exformatgames.defender.ecs.engine.components.touch_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GestureTapComponent implements Component {
	public Vector2 position = new Vector2();
	public int count = 0;
	
	public static ComponentMapper<GestureTapComponent> mapper = ComponentMapper.getFor(GestureTapComponent.class);
}
