package com.exformatgames.defender.ecs.engine.components.touch_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class GestureLongPressComponent implements Component {
	public Vector2 position = new Vector2();
	
	public static ComponentMapper<GestureLongPressComponent> mapper = ComponentMapper.getFor(GestureLongPressComponent.class);
}
