package ru.itis.game;

import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * Date 31.05.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class Wall extends Pane {
    Rectangle rect;
    private int height;
    public Wall(int height){
        this.height=height;
        rect = new Rectangle(20, height);
        getChildren().add(rect);
    }
}
