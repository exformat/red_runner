package com.exformatgames.defender.ecs.engine.components.audio_components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.audio.*;

public class SoundComponent implements Component {

	public Sound sound = null;
	public long ID = 0;
	public float volume = 0;
	public boolean isPlaying = false;
	public boolean isStop = false;
	public boolean isLooping = false;
	//public float timePlaying = 0;
	public boolean play = false;
	public float pan = 0;
	public float mul = 1; //0-1

	public static ComponentMapper<SoundComponent> mapper = ComponentMapper.getFor(SoundComponent.class);
}
