package com.exformatgames.defender;

import android.content.Context;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidAudio;
import com.exformatgames.defender.games_ecs.skedush.SkedushGame;

import barsoosayque.libgdxoboe.OboeAudio;

public class AndroidLauncher extends AndroidApplication {

	@Override
	public AndroidAudio createAudio(Context context, AndroidApplicationConfiguration config) {

		return new OboeAudio(context.getAssets());
	}

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		config.useWakelock = true;
		config.useImmersiveMode = true;
		config.numSamples = 4;

		initialize(new SkedushGame(), config);
	}
}
