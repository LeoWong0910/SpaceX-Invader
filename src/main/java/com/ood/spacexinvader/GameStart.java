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

import static com.ood.spacexinvader.GameConfig.*;

public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Title);
        settings.setHeight(HEIGHT);
        settings.setWidth(WIDTH);
        settings.setAppIcon(ICON);

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
                .at((WIDTH - PLAYER_SIZE) / 2  , HEIGHT - PLAYER_SIZE )
                .viewWithBBox(PLAYER_IMAGE)
                .with(new CollidableComponent(true))
                .buildAndAttach();


    }

    @Override
    protected void initInput() {

        FXGL.getInput().addAction(new UserAction("Left"){
            @Override
            protected void onAction() {
                if (isMoving){
                    return;
                }
                isMoving = true;
                player.translateX(-SPEED);
            }
        } , KeyCode.getKeyCode("A"));

        FXGL.getInput().addAction(new UserAction("Right"){
            @Override
            protected void onAction() {
                if (isMoving){
                    return;
                }
                isMoving = true;
                player.translateX(SPEED);
            }
        } , KeyCode.getKeyCode("D"));
    }

    @Override
    protected void onUpdate(double tpf) {
        isMoving = false;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
