package com.exformatgames.defender.games_ecs.skedush.components;

import com.badlogic.ashley.core.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.*;
import com.exformatgames.defender.games_ecs.skedush.state.*;

public class PlayerComponent implements Component {
	
	public float distance = 0;
	public int score = 0;
	public int gems = 0;
	
	public Animation<AtlasRegion> animationIdle;
	public Animation<AtlasRegion> animationRun;
	public Animation<AtlasRegion> animationJump;
	public Animation<AtlasRegion> animationCrouch;
	public Animation<AtlasRegion> animationDeath;
	
	public PlayerState playerState = PlayerState.IDLE;
	public float accelPlayer = 0.1f;
	public float maxVelocityPlayer = 6;
	public float crouchTime = 1;
	
	public static ComponentMapper<PlayerComponent> mapper = ComponentMapper.getFor(PlayerComponent.class);
}


