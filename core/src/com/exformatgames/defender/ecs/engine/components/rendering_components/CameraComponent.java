package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;

public class CameraComponent implements Component {
	public OrthographicCamera camera;
	
	public static ComponentMapper<CameraComponent> mapper = ComponentMapper.getFor(CameraComponent.class);
}
