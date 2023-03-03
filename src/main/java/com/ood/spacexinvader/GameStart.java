package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import javafx.scene.input.KeyCode;

public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(GameConfig.Title);
        settings.setHeight(GameConfig.HEIGHT);
        settings.setWidth(GameConfig.WIDTH);
        settings.setAppIcon(GameConfig.ICON);

        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu();
            }
        });
    }

    //Override to Adding entity.
    private Entity player;
    @Override
    protected void initGame() {
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at((GameConfig.WIDTH - GameConfig.PLAYER_SIZE) / 2  ,GameConfig.HEIGHT - GameConfig.PLAYER_SIZE )
                .viewWithBBox(GameConfig.PLAYER_IMAGE)
                .with(new CollidableComponent(true))
                .buildAndAttach();


    }

    @Override
    protected void initInput() {
        FXGL.getInput().addAction(new UserAction("Move Left"){
            @Override
            protected void onAction() {
                player.translateX(-GameConfig.SPEED);
            }
        } , KeyCode.LEFT);

        FXGL.getInput().addAction(new UserAction("Move Right"){
            @Override
            protected void onAction() {
                player.translateX(GameConfig.SPEED);
            }
        } , KeyCode.RIGHT);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
