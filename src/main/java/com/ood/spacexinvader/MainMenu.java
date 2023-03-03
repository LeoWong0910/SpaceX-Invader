package com.ood.spacexinvader;
import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.Stack;

public class MainMenu extends FXGLMenu {

    private static final int size = 150;
    Image bg = new Image("Background/bg.jpeg");
    private Animation<?> animation;

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        Text txtWelcome = new Text("Welcome to our game");
        txtWelcome.setTranslateX(160);
        txtWelcome.setTranslateY(225);

        Button btnNewStart = new Button("Start new game");
        btnNewStart.setTranslateX(170);
        btnNewStart.setTranslateY(250);
        btnNewStart.setOnAction(e -> fireNewGame());

        Button btnContinue = new Button("Continue");
        btnContinue.setTranslateX(185);
        btnContinue.setTranslateY((280));
        btnContinue.setOnAction(e -> fireContinue());

        Button btnExit = new Button("Exit");
        btnExit.setTranslateX(200);
        btnExit.setTranslateY((310));
        btnExit.setOnAction(e -> fireExit());

        Rectangle rec = new Rectangle();
        rec.setFill(new ImagePattern(bg));

        getContentRoot().getChildren().addAll(new StackPane(rec, txtWelcome, btnNewStart, btnContinue, btnExit));
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