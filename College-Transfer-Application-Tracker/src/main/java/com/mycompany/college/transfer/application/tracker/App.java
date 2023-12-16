package com.mycompany.college.transfer.application.tracker;

import Screen.CollegeListDisplay;
import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static ArrayList<College> colleges = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {
        CollegeListDisplay collegeListDisplay = new CollegeListDisplay();
        collegeListDisplay.start(stage);

    }


    public static void main(String[] args) {
        launch();
    }


}
