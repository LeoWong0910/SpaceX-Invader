package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;


public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(GameConfig.Title);
        settings.setHeight(GameConfig.HEIGHT);
        settings.setWidth(GameConfig.WIDTH);

        //assets/textures/spacex-invader-favicon-black.png
        settings.setAppIcon(GameConfig.ICON);
    }

    private Entity player;
    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at((GameConfig.WIDTH - 128) / 2  ,GameConfig.HEIGHT - 128 )
                .viewWithBBox(GameConfig.PLAYER_IMAGE)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
