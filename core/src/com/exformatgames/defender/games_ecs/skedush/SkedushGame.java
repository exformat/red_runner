package com.exformatgames.defender.games_ecs.skedush;

import com.badlogic.gdx.*;
import com.exformatgames.defender.games_ecs.skedush.screens.*;

public class SkedushGame extends Game {

	@Override
	public void create() {
		setScreen(new LoadingScreen(this));
	}
}
