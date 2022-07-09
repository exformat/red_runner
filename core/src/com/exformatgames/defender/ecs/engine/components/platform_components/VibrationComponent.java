package com.exformatgames.defender.ecs.engine.components.platform_components;

import com.badlogic.ashley.core.*;

public class VibrationComponent implements Component {
	public int millis = 0;
	public static ComponentMapper<VibrationComponent> mapper = ComponentMapper.getFor(VibrationComponent.class);
}
