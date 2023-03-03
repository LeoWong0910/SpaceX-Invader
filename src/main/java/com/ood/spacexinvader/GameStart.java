package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;

public class GameStart extends GameApplication {

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(GameConfig.Title);
        settings.setHeight(GameConfig.HEIGHT);
        settings.setWidth(GameConfig.WIDTH);
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
