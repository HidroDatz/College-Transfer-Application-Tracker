package Screen;

import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.util.Date;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TransferApplicationListView extends AbstractFileIO {

    /**
     * Displays a TableView of college transfer applications, loaded from a
     * serialized file.
     *
     * @param primaryStage The primary stage to set the scene.
     * @return A Scene containing a TableView with transfer application details
     * and a Back button.
     */
    public Scene TransferListDisplay(Stage primaryStage) {
        List<CollegeTransferApplication> transferApplication = loadObjectsFromFile("applications.ser");

        TableView<CollegeTransferApplication> tableView = new TableView<>();
        ObservableList<CollegeTransferApplication> data = FXCollections.observableArrayList(transferApplication);

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

        tableView.getColumns().addAll(
                collegeNameCol, addressCol, applicationDateCol, costCol, applicationPlatformCol,
                recommenderNamesCol, emailAddressCol, recommendationRequestedDateCol,
                recommendationRequiredDateCol, expectedAcceptanceLetterDateCol,
                isEssayWrittenCol, areTranscriptsSubmittedCol
        );

        tableView.setItems(data);
        Button backButton = new Button("Back");
        CollegeListDisplay collegeView = new CollegeListDisplay();
        backButton.setOnAction(event -> {
            primaryStage.setScene(collegeView.listCollege(primaryStage));
        });
        primaryStage.setTitle("College Transfer Applications");

        VBox vbox = new VBox(tableView, backButton);

        Scene scene = new Scene(vbox, 1200, 600);
        return scene;
    }

}
