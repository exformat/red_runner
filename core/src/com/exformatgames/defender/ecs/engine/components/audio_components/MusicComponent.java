package com.exformatgames.defender.ecs.engine.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.*;

public class MusicComponent implements Component {

	public Music music = null;

	public boolean remove = false;
	public boolean pause = false;
	public boolean stop = false;
	public boolean play = false;

	public float volume = 0;

	public static ComponentMapper<MusicComponent> mapper = ComponentMapper.getFor(MusicComponent.class);
}
