/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screen;

import com.mycompany.college.transfer.application.tracker.College;
import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.Date;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author ASUS
 */
public class CollegeTransferApplicationForm {

    public Scene Form(Stage primaryStage, College college) {
        primaryStage.setTitle("College Transfer Application Form");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Add form elements
        TextField collegeNameField = new TextField();
        collegeNameField.setText(college.getName());
        gridPane.add(new Label("College Name:"), 0, 0);
        gridPane.add(collegeNameField, 1, 0);

        TextField addressField = new TextField();
        addressField.setText(college.getAddress());
        gridPane.add(new Label("College Address:"), 0, 1);
        gridPane.add(addressField, 1, 1);

        DatePicker applicationDatePicker = new DatePicker();
        gridPane.add(new Label("Application Date:"), 0, 2);
        gridPane.add(applicationDatePicker, 1, 2);

        TextField costField = new TextField();
        costField.setPromptText("Cost");
        gridPane.add(new Label("Cost:"), 0, 3);
        gridPane.add(costField, 1, 3);

        TextField platformField = new TextField();
        platformField.setPromptText("Application Platform");
        gridPane.add(new Label("Application Platform:"), 0, 4);
        gridPane.add(platformField, 1, 4);

        // Recommender names as a comma-separated list
        TextField recommendersField = new TextField();
        recommendersField.setPromptText("Recommender Names (comma-separated)");
        gridPane.add(new Label("Recommender Names:"), 0, 5);
        gridPane.add(recommendersField, 1, 5);

        TextField emailAddressField = new TextField();
        emailAddressField.setPromptText("Email Address");
        gridPane.add(new Label("Email Address:"), 0, 6);
        gridPane.add(emailAddressField, 1, 6);

        DatePicker recommendationRequestedDatePicker = new DatePicker();
        gridPane.add(new Label("Recommendation Requested Date:"), 0, 7);
        gridPane.add(recommendationRequestedDatePicker, 1, 7);

        DatePicker recommendationRequiredDatePicker = new DatePicker();
        gridPane.add(new Label("Recommendation Required Date:"), 0, 8);
        gridPane.add(recommendationRequiredDatePicker, 1, 8);

        DatePicker acceptanceLetterDatePicker = new DatePicker();
        gridPane.add(new Label("Expected Acceptance Letter Date:"), 0, 9);
        gridPane.add(acceptanceLetterDatePicker, 1, 9);

        CheckBox essayWrittenCheckBox = new CheckBox("Is Essay Written?");
        gridPane.add(essayWrittenCheckBox, 0, 10);

        CheckBox transcriptsSubmittedCheckBox = new CheckBox("Are Transcripts Submitted?");
        gridPane.add(transcriptsSubmittedCheckBox, 1, 10);

        Button submitButton = new Button("Submit Application");
        submitButton.setOnAction(e -> {
            // Retrieve values from the form
            if (collegeNameField.getText().isEmpty() || addressField.getText().isEmpty() || applicationDatePicker.getValue() == null
                    || costField.getText().isEmpty() || platformField.getText().isEmpty()
                    || recommendersField.getText().isEmpty() || emailAddressField.getText().isEmpty()
                    || recommendationRequestedDatePicker.getValue() == null || recommendationRequiredDatePicker.getValue() == null
                    || acceptanceLetterDatePicker.getValue() == null) {
                showAlert("Missing values", "Please fill all fields in form.");
                return; // Stop further processing
            }
            if (!isValidCost(costField.getText())
                    || !isValidEmail(emailAddressField.getText())) {
                // Display an alert if validation fails
                showAlert("Invalid input", "Please check your input and try again.");
                return; // Do not proceed with saving
            }
            String collegeName = collegeNameField.getText();
            String address = addressField.getText();
            Date applicationDate = convertLocalDateToDate(applicationDatePicker.getValue());
            double cost = Double.parseDouble(costField.getText());
            String applicationPlatform = platformField.getText();
            List<String> recommenderNames = List.of(recommendersField.getText().split("\\s*,\\s*"));
            String emailAddress = emailAddressField.getText();

            Date recommendationRequestedDate = convertLocalDateToDate(recommendationRequestedDatePicker.getValue());
            Date recommendationRequiredDate = convertLocalDateToDate(recommendationRequiredDatePicker.getValue());
            Date expectedAcceptanceLetterDate = convertLocalDateToDate(acceptanceLetterDatePicker.getValue());
            boolean isEssayWritten = essayWrittenCheckBox.isSelected();
            boolean areTranscriptsSubmitted = transcriptsSubmittedCheckBox.isSelected();

            // Create CollegeTransferApplication object with the retrieved values
            CollegeTransferApplication application = new CollegeTransferApplication(
                    collegeName, address, applicationDate, cost, applicationPlatform,
                    recommenderNames, emailAddress, recommendationRequestedDate,
                    recommendationRequiredDate, expectedAcceptanceLetterDate,
                    isEssayWritten, areTranscriptsSubmitted);
            saveObjectToFile(application);
            showConfirmationDialog(application);
        });
        CollegeListDisplay listDisplay = new CollegeListDisplay();
        Button backButton = new Button("Home");
        backButton.setOnAction(event -> {
            primaryStage.setScene(listDisplay.listCollege(primaryStage));
        });
        gridPane.add(submitButton, 1, 11);
        gridPane.add(backButton, 0, 11);
        Scene scene = new Scene(gridPane, 600, 500);
        return scene;
    }

    private boolean isValidCost(String inputCost) {
        // Add your cost validation logic here
        try {
            double cost = Double.parseDouble(inputCost);
            return cost >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidEmail(String email) {
        // Add your email validation logic here
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    private void saveObjectToFile(CollegeTransferApplication object) {
        List<CollegeTransferApplication> objects = loadObjectsFromFile("applications.ser");
        objects.add(object);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("applications.ser"))) {
            oos.writeObject(objects);
            System.out.println("Object saved successfully to " + "applications.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public  List<CollegeTransferApplication> loadObjectsFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<CollegeTransferApplication>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Date convertLocalDateToDate(LocalDate localDate) {
        return localDate != null ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    private void showConfirmationDialog(CollegeTransferApplication application) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Application Submitted");
        alert.setHeaderText("Application Submitted Successfully");
        alert.setContentText("Thank you for submitting your application to " + application.getCollegeName() + ".");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
