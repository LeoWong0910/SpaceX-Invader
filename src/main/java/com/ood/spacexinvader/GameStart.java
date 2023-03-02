package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;

public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle("SpaceX Invader");
        settings.setHeight(650);
        settings.setWidth(450);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
