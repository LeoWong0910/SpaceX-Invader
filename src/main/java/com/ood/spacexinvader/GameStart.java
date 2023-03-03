package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(GameConfig.Title);
        settings.setHeight(GameConfig.HEIGHT);
        settings.setWidth(GameConfig.WIDTH);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
