package com.exformatgames.defender.games_ecs.skedush.game_systems.player_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.exformatgames.defender.Constants;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.LinearVelocityComponent;
import com.exformatgames.defender.games_ecs.skedush.components.PlayerComponent;
import com.exformatgames.defender.games_ecs.skedush.components.PlayerDeathComponent;
import com.exformatgames.defender.games_ecs.skedush.state.PlayerState;

public class PlayerDeathSystem extends IteratingSystem {

    public PlayerDeathSystem() {
        super(Family.all(PlayerDeathComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
        AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
        LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);

        playerComponent.playerState = PlayerState.DEATH;

        animationComponent.timeAnimation = 0;
        animationComponent.animation = playerComponent.animationDeath;
        linearVelocityComponent.value.setZero();

        Preferences preferences = Gdx.app.getPreferences(Constants.prefsName);
        int maxScore = preferences.getInteger(Constants.prefsMaxScoreKey);

        System.out.println("this score: " + playerComponent.score);
        System.out.println("max score: " + maxScore);

        if(maxScore < playerComponent.score){
            preferences.putInteger(Constants.prefsMaxScoreKey, playerComponent.score);
            preferences.flush();
            System.out.println("write new value");
        }

        entity.remove(PlayerDeathComponent.class);
    }
}
