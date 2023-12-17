package Screen;

import com.mycompany.college.transfer.application.tracker.College;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;

/**
 * This class represents the transfer application form for a college. It extends
 * the AbstractFileIO class to inherit file input and output functionality.
 * Users can fill out the form with information about their application, and the
 * form can be submitted for processing.
 */
public class TransferApplicationForm extends AbstractFileIO {

    /**
     * Creates and displays the transfer application form scene.
     *
     * @param primaryStage The primary stage for the application.
     * @param college The college for which the application is being submitted.
     * @return The Scene object for the transfer application form.
     */
    public Scene Form(Stage primaryStage, College college) {
        primaryStage.setTitle("College Transfer Application Form");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20, 20, 20, 20));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

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
            if (collegeNameField.getText().isEmpty() || addressField.getText().isEmpty() || applicationDatePicker.getValue() == null
                    || costField.getText().isEmpty() || platformField.getText().isEmpty()
                    || recommendersField.getText().isEmpty() || emailAddressField.getText().isEmpty()
                    || recommendationRequestedDatePicker.getValue() == null || recommendationRequiredDatePicker.getValue() == null
                    || acceptanceLetterDatePicker.getValue() == null) {
                showAlert("Missing values", "Please fill all fields in form.");
                return;
            }
            if (!isValidCost(costField.getText())
                    || !isValidEmail(emailAddressField.getText())) {
                showAlert("Invalid input", "Please check your input and try again.");
                return;
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

    /**
     * Validates the input cost to ensure it is a non-negative double value.
     *
     * @param inputCost The input cost to be validated.
     * @return True if the input is a non-negative double, false otherwise.
     */
    private boolean isValidCost(String inputCost) {
        try {
            double cost = Double.parseDouble(inputCost);
            return cost >= 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates the input email using a regular expression.
     *
     * @param email The email to be validated.
     * @return True if the email is valid, false otherwise.
     */
    private boolean isValidEmail(String email) {
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

    /**
     * Converts a LocalDate object to a Date object.
     *
     * @param localDate The LocalDate object to be converted.
     * @return The corresponding Date object, or null if the input is null.
     */
    private Date convertLocalDateToDate(LocalDate localDate) {
        return localDate != null ? Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()) : null;
    }

    /**
     * Displays a confirmation dialog for a submitted application.
     *
     * @param application The CollegeTransferApplication for which the
     * confirmation is displayed.
     */
    private void showConfirmationDialog(CollegeTransferApplication application) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Application Submitted");
        alert.setHeaderText("Application Submitted Successfully");
        alert.setContentText("Thank you for submitting your application to " + application.getCollegeName() + ".");

        ButtonType okButton = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait();
    }

    /**
     * Displays an error dialog with the specified title and content.
     *
     * @param title The title of the error dialog.
     * @param content The content of the error dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
