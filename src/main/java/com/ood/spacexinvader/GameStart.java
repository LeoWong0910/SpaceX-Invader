package com.ood.spacexinvader;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.Map;

import static com.ood.spacexinvader.GameConfig.*;

public class GameStart extends GameApplication {
    @Override
    protected void initSettings(GameSettings settings) {
        settings.setTitle(Title);
        settings.setHeight(HEIGHT);
        settings.setWidth(WIDTH);
        settings.setAppIcon(ICON);

        settings.setMainMenuEnabled(true);
        settings.setCloseConfirmation(true);
        settings.setSceneFactory(new SceneFactory() {
            @Override
            public FXGLMenu newMainMenu() {
                return new MainMenu();
            }

            public FXGLMenu newGameMenu() {
                return new PauseMenu();
            }
        });
    }

    //Override to Adding entity.
    private Entity player;
    @Override
    protected void initGame() {
        shootTimer = FXGL.newLocalTimer();
        //Spawn 30 Enemy
        for (int i = 0; i < 30; i++) {
            Enemy();
        }
        player = FXGL.entityBuilder()
                .type(EntityType.PLAYER)
                .at((WIDTH - PLAYER_SIZE) / 2  , HEIGHT - PLAYER_SIZE )
                .viewWithBBox(PLAYER_IMAGE)
                .with(new CollidableComponent(true))
                .buildAndAttach();
    }

    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.BULLET,EntityType.ENEMY) {
                    @Override
                    protected void onCollisionBegin(Entity bullet, Entity enemy) {
                        bullet.removeFromWorld();
                        enemy.removeFromWorld();
                        FXGL.getWorldProperties().increment("score",+1);
                    }
                });
        FXGL.getPhysicsWorld().addCollisionHandler(
                new CollisionHandler(EntityType.ENEMY,EntityType.PLAYER) {
                    @Override
                    protected void onCollisionBegin(Entity enemy, Entity player) {
                        enemy.removeFromWorld();
                        if (FXGL.getWorldProperties().getInt("hp") <= 0){
                            player.removeFromWorld();
                        }
                        FXGL.getWorldProperties().increment("hp",-1);;
                    }
                });
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

        FXGL.getInput().addAction(new UserAction("Shoot") {
            @Override
            protected void onAction() {
//                System.out.println("Biu Biu Biu~");

                //Limit bullet shoot time
                if (!shootTimer.elapsed(shootDelay)) return;
                shootTimer.capture();

                //Test Bullet
                FXGL.entityBuilder()
                        .at(player.getCenter().getX() - 4, player.getCenter().getY() - 50)
                        .viewWithBBox(new Rectangle(BULLET_SIZE,BULLET_SIZE, Color.BLACK))
                        .with(new ProjectileComponent(new Point2D(0, -2),BULLET_SPEED))
                        .with(new OffscreenCleanComponent())
                        .type(EntityType.BULLET)
                        .collidable()
                        .buildAndAttach();

            }
        },KeyCode.getKeyCode("J"));
    }

    @Override
    protected void initUI() {
        Text showScore = FXGL.getUIFactoryService().newText("Score: ",Color.BLACK, 22);
        Text realScore = FXGL.getUIFactoryService().newText("",Color.BLACK, 22);

        Text showHP = FXGL.getUIFactoryService().newText("HP: " ,Color.RED, 22);
        Text realHP = FXGL.getUIFactoryService().newText("" ,Color.RED, 22);


        showHP.setTranslateX(10);
        showHP.setTranslateY(50);
        realHP.setTranslateX(10 + 40);
        realHP.setTranslateY(50);

        showScore.setTranslateX(10);
        showScore.setTranslateY(50 + 30);
        realScore.setTranslateX(10 + 70);
        realScore.setTranslateY(50 + 30);

        realHP.textProperty().bind(FXGL.getWorldProperties().intProperty("hp").asString());
        realScore.textProperty().bind(FXGL.getWorldProperties().intProperty("score").asString());

        FXGL.getGameScene().addUINodes(showHP, showScore,realHP,realScore);

    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("score",0);
        vars.put("hp",20);
    }

    @Override
    protected void onUpdate(double tpf) {
        isMoving = false;
    }


    private void Enemy(){
        FXGL.entityBuilder()
                .viewWithBBox(ALIEN_IMAGE)
                .at(FXGLMath.random(-30,WIDTH-90), FXGLMath.random(0,ENEMY_SIZE))
                .type(EntityType.ENEMY)
                .with(new ProjectileComponent(new Point2D(0, 1),ENEMY_SPEED))
                .rotate(270)
                .collidable()
                .buildAndAttach();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
