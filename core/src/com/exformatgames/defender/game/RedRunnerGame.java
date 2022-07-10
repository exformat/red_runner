package com.exformatgames.defender.game;

import com.badlogic.gdx.*;
import com.exformatgames.defender.game.screens.*;

public class RedRunnerGame extends Game {

	@Override
	public void create() {
		setScreen(new LoadingScreen(this));
	}
}
