package com.ood.spacexinvader;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class MainMenu extends FXGLMenu {
    public MainMenu() {
        super(MenuType.MAIN_MENU);

        Label welcome = new Label("Welcome to our game");
        welcome.setTranslateX(165);
        welcome.setTranslateY(225);

        Button button1 = new Button("Start new game");
        button1.setTranslateX(165);
        button1.setTranslateY(250);

        getContentRoot().getChildren().addAll(welcome, button1);
    }
}