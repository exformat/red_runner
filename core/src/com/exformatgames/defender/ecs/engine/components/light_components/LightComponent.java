package com.exformatgames.defender.ecs.engine.components.light_components;

import box2dLight.*;
import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.math.*;

public class LightComponent implements Component
{
	public Light light = null;
	
	public Vector2 position = new Vector2();
	public Color color = new Color(1, 1, 1, 1);
	public float dir = 0;
	public int rays = 0;
	public float lenght = 0;

	public void set(Vector2 position, Color color, float dir, int rays, float lenght) {
		this.position = position;
		this.color = color;
		this.dir = dir;
		this.rays = rays;
		this.lenght = lenght;
	}
	
	public static final ComponentMapper<LightComponent> mapper = ComponentMapper.getFor(LightComponent.class);
}
