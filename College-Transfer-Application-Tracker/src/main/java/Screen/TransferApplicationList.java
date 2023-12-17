/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Screen;

import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.util.Date;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author ASUS
 */
public class TransferApplicationList {

    public Scene TransferListDisplay(Stage primaryStage) {
        CollegeTransferApplicationForm transferForms = new CollegeTransferApplicationForm();
        List<CollegeTransferApplication> transferApplication = transferForms.loadObjectsFromFile("applications.ser");
        // Create TableView
        TableView<CollegeTransferApplication> tableView = new TableView<>();
        ObservableList<CollegeTransferApplication> data = FXCollections.observableArrayList(transferApplication);

        // Create columns
        TableColumn<CollegeTransferApplication, String> collegeNameCol = new TableColumn<>("College Name");
        collegeNameCol.setCellValueFactory(new PropertyValueFactory<>("collegeName"));

        TableColumn<CollegeTransferApplication, String> addressCol = new TableColumn<>("Address");
        addressCol.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<CollegeTransferApplication, Date> applicationDateCol = new TableColumn<>("Application Date");
        applicationDateCol.setCellValueFactory(new PropertyValueFactory<>("applicationDate"));

        TableColumn<CollegeTransferApplication, Double> costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<>("cost"));

        TableColumn<CollegeTransferApplication, String> applicationPlatformCol = new TableColumn<>("Application Platform");
        applicationPlatformCol.setCellValueFactory(new PropertyValueFactory<>("applicationPlatform"));

        TableColumn<CollegeTransferApplication, List<String>> recommenderNamesCol = new TableColumn<>("Recommender Names");
        recommenderNamesCol.setCellValueFactory(new PropertyValueFactory<>("recommenderNames"));

        TableColumn<CollegeTransferApplication, String> emailAddressCol = new TableColumn<>("Email Address");
        emailAddressCol.setCellValueFactory(new PropertyValueFactory<>("emailAddress"));

        TableColumn<CollegeTransferApplication, Date> recommendationRequestedDateCol = new TableColumn<>("Recommendation Requested Date");
        recommendationRequestedDateCol.setCellValueFactory(new PropertyValueFactory<>("recommendationRequestedDate"));

        TableColumn<CollegeTransferApplication, Date> recommendationRequiredDateCol = new TableColumn<>("Recommendation Required Date");
        recommendationRequiredDateCol.setCellValueFactory(new PropertyValueFactory<>("recommendationRequiredDate"));

        TableColumn<CollegeTransferApplication, Date> expectedAcceptanceLetterDateCol = new TableColumn<>("Expected Acceptance Letter Date");
        expectedAcceptanceLetterDateCol.setCellValueFactory(new PropertyValueFactory<>("expectedAcceptanceLetterDate"));

        TableColumn<CollegeTransferApplication, Boolean> isEssayWrittenCol = new TableColumn<>("Is Essay Written");
        isEssayWrittenCol.setCellValueFactory(new PropertyValueFactory<>("isEssayWritten"));

        TableColumn<CollegeTransferApplication, Boolean> areTranscriptsSubmittedCol = new TableColumn<>("Are Transcripts Submitted");
        areTranscriptsSubmittedCol.setCellValueFactory(new PropertyValueFactory<>("areTranscriptsSubmitted"));

        // Add columns to the TableView
        tableView.getColumns().addAll(
                collegeNameCol, addressCol, applicationDateCol, costCol, applicationPlatformCol,
                recommenderNamesCol, emailAddressCol, recommendationRequestedDateCol,
                recommendationRequiredDateCol, expectedAcceptanceLetterDateCol,
                isEssayWrittenCol, areTranscriptsSubmittedCol
        );

        // Add data to the TableView
        tableView.setItems(data);

        primaryStage.setTitle("College Transfer Applications");

        VBox vbox = new VBox(tableView);

        Scene scene = new Scene(vbox, 1200, 600);
        return scene;
    }

}
