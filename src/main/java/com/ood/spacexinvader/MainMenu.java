package com.ood.spacexinvader;

import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class MainMenu extends FXGLMenu {

    private static final int size = 150;

    private Animation<?> animation;
    public MainMenu() {
        super(MenuType.MAIN_MENU);

        Text welcome = new Text("Welcome to our game");
        welcome.setTranslateX(165);
        welcome.setTranslateY(225);

        Button btn_start = new Button("Start new game");
        btn_start.setTranslateX(170);
        btn_start.setTranslateY(250);
        btn_start.setOnAction(e -> getController().startNewGame());

        Button btn_exit = new Button("Exit");
        btn_exit.setTranslateX(200);
        btn_exit.setTranslateY((280));
        btn_exit.setOnAction(e -> fireExit());

        getContentRoot().getChildren().addAll(welcome, btn_start, btn_exit);
        getContentRoot().setScaleX(0);
        getContentRoot().setScaleY(0);

        animation = FXGL.animationBuilder()
                .duration(Duration.seconds(0.66))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .scale(getContentRoot())
                .from(new Point2D(0, 0))
                .to(new Point2D(1, 1))
                .build();
    }

    public void onCreate() {
        animation.setOnFinished(EmptyRunnable.INSTANCE);
        animation.stop();
        animation.start();
    }

    protected void onUpdate(double tpf) {
        animation.onUpdate(tpf);
    }
}