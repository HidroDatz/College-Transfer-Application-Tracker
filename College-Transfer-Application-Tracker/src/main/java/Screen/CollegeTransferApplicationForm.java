/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screen;

import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.time.LocalDate;
import java.time.ZoneId;
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
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 *
 * @author ASUS
 */
public class CollegeTransferApplicationForm {

    public Scene Form(Stage primaryStage) {
        primaryStage.setTitle("College Transfer Application Form");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Add form elements
        TextField collegeNameField = new TextField();
        collegeNameField.setPromptText("College Name");
        gridPane.add(new Label("College Name:"), 0, 0);
        gridPane.add(collegeNameField, 1, 0);

        TextField addressField = new TextField();
        addressField.setPromptText("College Address");
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

            // Perform further actions with the CollegeTransferApplication object
            // (e.g., send data to a database, process the application, etc.)
            // Show a confirmation dialog
            showConfirmationDialog(application);
        });

        gridPane.add(submitButton, 1, 11);

        Scene scene = new Scene(gridPane, 600, 500);
        return scene;
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
}
