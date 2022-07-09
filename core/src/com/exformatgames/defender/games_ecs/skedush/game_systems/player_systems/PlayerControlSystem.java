package com.exformatgames.defender.games_ecs.skedush.game_systems.player_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.defender.ecs.engine.components.touch_components.GestureFlingComponent;
import com.exformatgames.defender.ecs.engine.components.touch_components.GestureTapComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.LinearVelocityComponent;
import com.exformatgames.defender.games_ecs.skedush.audio.Sounds;
import com.exformatgames.defender.games_ecs.skedush.components.PlayerComponent;
import com.exformatgames.defender.games_ecs.skedush.components.PlayerJumpComponent;
import com.exformatgames.defender.games_ecs.skedush.state.PlayerState;

public class PlayerControlSystem extends IteratingSystem {

    public PlayerControlSystem() {
        super(Family.all(PlayerComponent.class, GestureFlingComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
        GestureTapComponent gestureTapComponent = GestureTapComponent.mapper.get(entity);
        GestureFlingComponent gestureFlingComponent = GestureFlingComponent.mapper.get(entity);
        AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
        LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);

        if(playerComponent.playerState != PlayerState.DEATH){
            if ( ! gestureTapComponent.position.isZero()){
                if (playerComponent.playerState == PlayerState.JUMP) {
                    playerComponent.playerState = PlayerState.DOUBLE_JUMP;
                    entity.add(getEngine().createComponent(PlayerJumpComponent.class));
                }

                if (playerComponent.playerState == PlayerState.RUN || playerComponent.playerState == PlayerState.CROUCH){
                    playerComponent.playerState = PlayerState.JUMP;
                    entity.add(getEngine().createComponent(PlayerJumpComponent.class));
                }

                if (playerComponent.playerState == PlayerState.IDLE) {
                    playerComponent.playerState = PlayerState.RUN;
                    animationComponent.timeAnimation = 0;
                    animationComponent.animation = playerComponent.animationRun;
                    linearVelocityComponent.value.x = 1;
                }
            }

            switch (gestureFlingComponent.sideFling){
                case UP: {
                    if (playerComponent.playerState == PlayerState.RUN || playerComponent.playerState == PlayerState.CROUCH || playerComponent.playerState == PlayerState.JUMP){
                        if (playerComponent.playerState == PlayerState.JUMP){
                            playerComponent.playerState = PlayerState.DOUBLE_JUMP;
                        }else {
                            playerComponent.playerState = PlayerState.JUMP;
                        }
                        entity.add(getEngine().createComponent(PlayerJumpComponent.class));
                    }
                    break;
                }
                case DOWN: {
                    if (playerComponent.playerState == PlayerState.RUN){
                        playerComponent.playerState = PlayerState.CROUCH;
                        animationComponent.timeAnimation = 0;
                        animationComponent.animation = playerComponent.animationCrouch;
                    }
                    if (playerComponent.playerState == PlayerState.JUMP || playerComponent.playerState == PlayerState.DOUBLE_JUMP){
                        linearVelocityComponent.value.y = -7;
                    }
                    break;
                }
            }
        }
    }
}
