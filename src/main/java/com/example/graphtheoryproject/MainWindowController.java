package com.example.graphtheoryproject;

import com.example.graphtheoryproject.astar.AStar;
import com.example.graphtheoryproject.astar.NODE_TYPE;
import com.example.graphtheoryproject.astar.Node;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML private Pane pane;
    private Button[][] buttons = new Button[8][8];
    private AStar aStar;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            /*int startCol = 5;
            int startRow = 6;
            int goalCol = 2;
            int goalRow = 3;
            aStar = new AStar();
            aStar.init();
            aStar.start(startCol,startRow);
            aStar.goal(goalCol, goalRow);
            aStar.obstacle(1,3);
            aStar.obstacle(1,4);
            aStar.obstacle(2,4);
            aStar.obstacle(3,4);
            aStar.obstacle(4,4);
            aStar.obstacle(5,4);
            aStar.run();
            List<Node> pathList = aStar.findPath();


            createNodes();
            start(startCol,startRow);
            goal(goalCol,goalRow);
            addObstacle(1,3);
            addObstacle(1,4);
            addObstacle(2,4);
            addObstacle(3,4);
            addObstacle(4,4);
            addObstacle(5,4);
            createPath(pathList);*/

            int startCol = 1;
            int startRow = 3;
            int goalCol = 7;
            int goalRow = 6;
            aStar = new AStar();
            aStar.init();
            aStar.start(startCol,startRow);
            aStar.goal(goalCol, goalRow);
            aStar.obstacle(1,2);
            aStar.obstacle(2,2);
            aStar.obstacle(3,2);
            aStar.obstacle(3,3);
            aStar.obstacle(3,4);
            aStar.obstacle(3,5);
            aStar.obstacle(4,5);
            aStar.obstacle(5,5);
            aStar.obstacle(5,6);
            aStar.obstacle(5,7);
            aStar.run();
            List<Node> pathList = aStar.findPath();


            createNodes();
            start(startCol,startRow);
            goal(goalCol,goalRow);
            addObstacle(1,2);
            addObstacle(2,2);
            addObstacle(3,2);
            addObstacle(3,3);
            addObstacle(3,4);
            addObstacle(3,5);
            addObstacle(4,5);
            addObstacle(5,5);
            addObstacle(5,6);
            addObstacle(5,7);
            createPath(pathList);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    private void createPath(List<Node> nodeList){
        int pathOrder = 1;
        for(Node path : nodeList){
            int column = path.getColumn();
            int row = path.getRow();

            if(path.getNodeType() != NODE_TYPE.GOAL){
                buttons[column][row].setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                buttons[column][row].setStyle("-fx-text-fill:white");
                buttons[column][row].setText(String.valueOf(pathOrder));
                ++pathOrder;
            }
        }

    }

    private void createNodes(){
        for (int col = 0; col < 8; col++) {
            for (int row = 0; row < 8; row++) {
                Button button = new Button();
                buttons[col][row] = button;
                button.setMinSize(60, 60);
                button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                button.setLayoutX(col * 60);
                button.setLayoutY(row * 60);
                button.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
                button.setStyle("-fx-border-color: red;");

                int finalCol = col;
                int finalRow = row;
                button.setOnAction(event -> {
                    colorBlack(finalCol, finalRow); // Tıklanan butonun kenar rengini siyah yap
                });

                pane.getChildren().add(button);
            }
        }
    }

    private void colorBlack(int col, int row){
        buttons[col][row].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void start(int col, int row){
        buttons[col][row].setBackground(new Background(new BackgroundFill(Color.LAWNGREEN, CornerRadii.EMPTY,Insets.EMPTY)));
        buttons[col][row].setText("START");
        buttons[col][row].setStyle("-fx-text-fill:WHITE");

    }

    private void goal(int col, int row){
        buttons[col][row].setBackground(new Background(new BackgroundFill(Color.BLUE, CornerRadii.EMPTY,Insets.EMPTY)));
        buttons[col][row].setText("GOAL");
        buttons[col][row].setStyle("-fx-text-fill:WHITE");
    }

    private void addObstacle(int col, int row){
        buttons[col][row].setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY,Insets.EMPTY)));
    }

}