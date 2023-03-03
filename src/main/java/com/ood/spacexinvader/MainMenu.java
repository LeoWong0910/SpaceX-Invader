package com.ood.spacexinvader;
import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.core.util.EmptyRunnable;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.ui.FontType;
import javafx.geometry.Point2D;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static com.ood.spacexinvader.GameConfig.HEIGHT;
import static com.ood.spacexinvader.GameConfig.WIDTH;

public class MainMenu extends FXGLMenu {

    private static final int size = 150;
    Image BI = new Image("assets/textures/background/bg.jpeg");
    private Animation<?> animation;

    public MainMenu() {
        super(MenuType.MAIN_MENU);

        Text txtWelcome = FXGL.getUIFactoryService().newText("Welcome to our game", Color.WHITE, FontType.GAME, 27.0);

        Button btnNewStart = new Button("Start new game");
        btnNewStart.setTranslateY(40);
        btnNewStart.setOnAction(e -> fireNewGame());

        Button btnContinue = new Button("Continue");
        btnContinue.setTranslateY(70);
        btnContinue.setOnAction(e -> fireContinue());

        Button btnExit = new Button("Exit");
        btnExit.setTranslateY(100);
        btnExit.setOnAction(e -> fireExit());

        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setFill(new ImagePattern(BI));

        getContentRoot().getChildren().addAll(new StackPane(bg, txtWelcome, btnNewStart, btnContinue, btnExit));
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