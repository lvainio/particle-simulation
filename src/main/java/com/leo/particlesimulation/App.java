package com.leo.particlesimulation;

import com.leo.particlesimulation.graphic.Graphics1;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Graphics1 g1 = new Graphics1(stage);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
