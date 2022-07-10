package com.exformatgames.defender.game.audio;

import com.badlogic.gdx.assets.*;
import com.badlogic.gdx.audio.*;

public class Sounds {
	
	public static Sound pickedBonusSound; // detoxio https://www.youtube.com/channel/UCLcb4e3PpDvtE8PurRkazIQ
	public static Sound endGameSound; // detoxio https://www.youtube.com/channel/UCLcb4e3PpDvtE8PurRkazIQ
	public static Sound jumpSound; // Jesús Lastra http://lilbang.deviantart.com
	public static Sound endJumpSound; //Jesús Lastra http://lilbang.deviantart.com


	public static void init(AssetManager assetManager){
		pickedBonusSound = assetManager.get("sounds/detoxio/picked_coin.ogg");
		endGameSound = assetManager.get("sounds/detoxio/end_game.ogg");
		jumpSound = assetManager.get("sounds/jalastram/jump.wav", Sound.class);
		endJumpSound = assetManager.get("sounds/jalastram/end_jump.wav", Sound.class);
	}
}
