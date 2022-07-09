package com.exformatgames.defender.ecs.engine.components.math_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.math.*;

public class CircleComponent implements Component {
	
	public Circle circle = new Circle();
	
	public static ComponentMapper<CircleComponent> mapper = ComponentMapper.getFor(CircleComponent.class);
}
