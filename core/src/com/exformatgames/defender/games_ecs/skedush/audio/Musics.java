package com.exformatgames.defender.games_ecs.skedush.audio;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class Musics {

    public static Music forestMusic; // Sirental https://soundcloud.com/user-885064321

    public static void init(AssetManager assetManager){
        forestMusic = assetManager.get("music/Forest.mp3", Music.class);
    }
}
