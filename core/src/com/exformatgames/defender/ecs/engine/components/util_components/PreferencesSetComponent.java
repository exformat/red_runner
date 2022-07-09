package com.exformatgames.defender.ecs.engine.components.util_components;

import com.badlogic.ashley.core.*;

public class PreferencesSetComponent implements Component {
	
	public String preferencesName;
	public PreferencesType type;
	
	public String key;
	
	public String stringValue;
	public int intValue;
	public float floatValue;
	public boolean boolValue;
	
	public static ComponentMapper<PreferencesSetComponent> mapper = ComponentMapper.getFor(PreferencesSetComponent.class);
	
	public enum PreferencesType{
		STRING,
		BOOL,
		INT,
		FLOAT;
	}
}
