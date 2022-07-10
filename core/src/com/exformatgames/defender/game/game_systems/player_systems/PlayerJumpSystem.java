package com.exformatgames.defender.game.game_systems.player_systems;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.exformatgames.defender.ecs.engine.components.transform_components.AnimationComponent;
import com.exformatgames.defender.ecs.engine.components.transform_components.LinearVelocityComponent;
import com.exformatgames.defender.game.audio.Sounds;
import com.exformatgames.defender.game.components.PlayerComponent;
import com.exformatgames.defender.game.components.PlayerJumpComponent;

public class PlayerJumpSystem extends IteratingSystem {

    public PlayerJumpSystem() {
        super(Family.all(PlayerJumpComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        PlayerComponent playerComponent = PlayerComponent.mapper.get(entity);
        AnimationComponent animationComponent = AnimationComponent.mapper.get(entity);
        LinearVelocityComponent linearVelocityComponent = LinearVelocityComponent.mapper.get(entity);

        animationComponent.timeAnimation = 0;
        animationComponent.animation = playerComponent.animationJump;
        linearVelocityComponent.value.y = 5;
        Sounds.jumpSound.play();

        entity.remove(PlayerJumpComponent.class);
    }
}
