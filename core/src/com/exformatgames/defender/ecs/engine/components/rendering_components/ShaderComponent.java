package com.exformatgames.defender.ecs.engine.components.rendering_components;

import com.badlogic.gdx.graphics.glutils.*;
import com.badlogic.ashley.core.*;
import com.exformatgames.defender.ecs.utils.*;

public class ShaderComponent implements Component
{
	public ShaderProgram shader = null;
	
	public Uniforms uniforms = new Uniforms();
	
	public static ComponentMapper<ShaderComponent> mapper = ComponentMapper.getFor(ShaderComponent.class);
}
