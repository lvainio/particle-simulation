package com.leo.particlesimulation;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var circle = new Circle(100, Color.GREEN);
        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(circle, label); 

        var scene = new Scene(stackPane, 640, 480);

        stage.setScene(scene);
        stage.setTitle("Particle Simulation");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}