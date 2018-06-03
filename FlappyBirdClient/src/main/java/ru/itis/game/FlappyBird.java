package ru.itis.game;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ru.itis.websocket.ClientEndPoint;
import ru.itis.websocket.Message;

import javax.websocket.EncodeException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Date 31.05.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class FlappyBird extends Application {

    public static Pane appRoot = new Pane();
    public static Pane gameRoot = new Pane();

    public static List<Wall> walls = new ArrayList<>();

    private Bird bird = new Bird();
    public static int score = 0;
    public Label scoreLabel = new Label("Score: "+score);
    private ClientEndPoint  clientEndPoint = new ClientEndPoint();


    public Parent createContext(){
        gameRoot.setPrefSize(600,600);


        System.out.println("connected");

        for (int i = 0; i < 100; i++){
            int enter = (int) (Math.random()*100+50);
            int height = new Random().nextInt(600-enter);
            Wall wall = new Wall(height);
            wall.setTranslateX(i*350+600);
            wall.setTranslateY(0);
            walls.add(wall);

            Wall wall2 = new Wall(600-enter-height);
            wall2.setTranslateX(i*350+600);
            wall2.setTranslateY(height+enter);
            walls.add(wall2);

            gameRoot.getChildren().addAll(wall, wall2);
        }

        gameRoot.getChildren().add(bird);
        appRoot.getChildren().addAll(gameRoot);
        return appRoot;
    }

    public  Message getMessage() {
        return message;
    }

    public static Message message = new Message();

    public void update(){
        if (bird.velocity.getY() < 5){
            bird.velocity = bird.velocity.add(0,1);
        }

        bird.moveX((int) bird.velocity.getX());
        bird.moveY((int) bird.velocity.getY());
        scoreLabel.setText("Score: "+score);

        message.setMessage(String.valueOf(score));

        try {
            clientEndPoint.getSession().getBasicRemote().sendObject(message);
        } catch (IOException | EncodeException e) {
            e.printStackTrace();
        }

        bird.translateXProperty().addListener((obs, old, newValue)->{
          int offset = newValue.intValue();
          if (offset>200)
              gameRoot.setLayoutX(-(offset-200));
        });
    }


    public static Stage modalStage;
    public static Stage scoreStage;



    public void start(Stage primaryStage) throws Exception {

        Scene scene = new Scene(createContext());
        scene.setOnMouseClicked(event->bird.jump());
        primaryStage.setScene(scene);
        primaryStage.show();

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };
        timer.start();
        modalStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/modal.fxml"));
            modalStage.setTitle("modal");
            modalStage.setScene(new Scene(root));
            modalStage.initModality(Modality.WINDOW_MODAL);
            modalStage.initOwner(scene.getWindow());
            modalStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        scoreStage = new Stage();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/score.fxml"));
            scoreStage.setTitle("score");
            scoreStage.setScene(new Scene(root));
            scoreStage.initModality(Modality.WINDOW_MODAL);
            scoreStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
