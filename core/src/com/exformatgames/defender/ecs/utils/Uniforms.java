package com.exformatgames.defender.ecs.utils;

import com.badlogic.gdx.utils.*;

public class Uniforms {
	private Array<Uniform> arr = new Array<>();
	
	public Uniform get(int id){
		return arr.get(id);
	}
	
	public void add(Uniform u){
		u.id = arr.size;
		arr.add(u);
	}
	
	public int size(){
		return arr.size;
	}
	
	public void removeAll(){
		arr.clear();
	}
}
