package com.exformatgames.defender.ecs.engine.components.util_components;

import com.badlogic.ashley.core.*;

public class PreferencesGetComponent implements Component {

	public String preferencesName;

	public String key;

	public String stringValue;
	public int intValue;
	public float floatValue;
	public boolean boolValue;

	public static ComponentMapper<PreferencesGetComponent> mapper = ComponentMapper.getFor(PreferencesGetComponent.class);
}
