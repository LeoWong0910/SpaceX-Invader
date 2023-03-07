package com.ood.spacexinvader;

import com.almasb.fxgl.time.LocalTimer;
import javafx.util.Duration;

public class GameConfig {
    public static final String Title= "SpaceX Invader";
    public static final int HEIGHT = 600;
    public static final int WIDTH = 450;
    public static final double SPEED = 5f;
    public static final double BULLET_SPEED = 300f;
    public static final double ENEMY_SPEED = 20f;
    public static final int BULLET_SIZE = 6;
    public static LocalTimer shootTimer;
    public static Duration shootDelay = Duration.seconds(0.25);
    public static final int PLAYER_SIZE = 100;
    public static final int ENEMY_SIZE = 128;

    public static final int ENEMY_AMOUNT = 30;

    public static boolean isMoving;
    //follow by the FXGL structure.
    public static final String ICON = "icon/spacex-invader-favicon-black.png";
    public static final String ALIEN_IMAGE = "enemy/alien.png";
    public static final String PLAYER_IMAGE = "player/SpaceX.png";
}
