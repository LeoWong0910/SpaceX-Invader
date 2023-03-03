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

        Button btn_start = new Button("Start new game");
        btn_start.setTranslateX(170);
        btn_start.setTranslateY(250);
        btn_start.setOnAction(e -> getController().startNewGame());

        Button btn_exit = new Button("Exit");
        btn_exit.setTranslateX(200);
        btn_exit.setTranslateY((280));
        btn_exit.setOnAction(e -> getController().exit());

        getContentRoot().getChildren().addAll(welcome, btn_start, btn_exit);
    }
}