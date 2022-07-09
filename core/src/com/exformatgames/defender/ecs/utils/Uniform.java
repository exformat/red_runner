package com.exformatgames.defender.ecs.utils;

import com.badlogic.gdx.math.*;
import com.badlogic.gdx.graphics.*;

public class Uniform{
	public int id;
	public int location;
	public String name;
	public float value = 0;
	public Vector2 vec2 = null;
	public Vector3 vec3 = null;
	public Color color = null;
	
	public Uniform(String name) {
		this.name = name;
	}
}
