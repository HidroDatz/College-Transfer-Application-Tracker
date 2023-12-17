package com.mycompany.college.transfer.application.tracker;

import Screen.CollegeListDisplay;
import javafx.application.Application;

import javafx.stage.Stage;

import java.io.IOException;



public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        CollegeListDisplay collegeListDisplay = new CollegeListDisplay();
        collegeListDisplay.start(stage);

    }


    public static void main(String[] args) {
        launch();
    }


}
