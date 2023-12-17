package Screen;

import com.mycompany.college.transfer.application.tracker.College;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CollegeListDisplay extends Application {

    final static ArrayList<College> colleges = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        colleges.add(new College("Harvard University", "Cambridge, MA", "Ivy League institution known for excellence in education and research.", "/images/harvard_image.jpg"));
        colleges.add(new College("Massachusetts Institute of Technology (MIT)", "Cambridge, MA", "Renowned for its focus on science, engineering, and technology.", "/images/mit_image.jpg"));
        colleges.add(new College("Stanford University", "Stanford, CA", "Leading research university with a strong emphasis on innovation.", "/images/stanford_image.jpg"));
        colleges.add(new College("Yale University", "New Haven, CT", "Ivy League university known for its distinguished faculty and rich history.", "/images/yale_image.jpg"));
        colleges.add(new College("University of California, Los Angeles (UCLA)", "Los Angeles, CA", "A public research university with a diverse and vibrant campus.", "/images/ucla_image.jpg"));
        Scene scene = listCollege(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void searchCollege(String searchTerm, TableView<College> tableView) {
        ObservableList<College> filteredColleges = FXCollections.observableArrayList();

        for (College college : colleges) {
            if (college.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredColleges.add(college);
            }
        }

        tableView.setItems(filteredColleges);
    }

    private void clearSearch(TableView<College> tableView, ObservableList<College> observableColleges) {
        tableView.setItems(observableColleges);
    }

    public Scene listCollege(Stage primaryStage) {
        primaryStage.setTitle("College List Display");
        TableView<College> tableView = new TableView<>();
        ObservableList<College> observableColleges = FXCollections.observableArrayList(colleges);

        TableColumn<College, Integer> numberColumn = new TableColumn<>("No.");
        numberColumn.setCellValueFactory(cellData -> {
            int rowIndex = tableView.getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });

        TableColumn<College, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // Button column to show transfer applications
        TableColumn<College, Void> transferApplicationsColumn = new TableColumn<>("Transfer Applications");

        Button showTransferApplicationsButton = new Button("Show Transfer Applications");

        showTransferApplicationsButton.setOnAction(event -> {
            TransferApplicationList transferForm = new TransferApplicationList();
            Scene transferListScene = transferForm.TransferListDisplay(primaryStage);
            primaryStage.setScene(transferListScene);
        });

        tableView.getColumns().addAll(numberColumn, nameColumn, transferApplicationsColumn);
        tableView.setItems(observableColleges);
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) { // Single click
                College selectedCollege = tableView.getSelectionModel().getSelectedItem();
                if (selectedCollege != null) {
                    showCollegeDetails(selectedCollege, primaryStage);
                }
            }
        });

        TextField searchField = new TextField();
        Button searchButton = new Button("Search");
        searchButton.setOnAction(e -> searchCollege(searchField.getText(), tableView));
        Button clearButton = new Button("Clear Search");
        clearButton.setOnAction(e -> clearSearch(tableView, observableColleges));

        VBox vbox = new VBox(searchField, searchButton, clearButton, showTransferApplicationsButton, tableView);
        Scene scene = new Scene(vbox, 600, 400);
        return scene;
    }

    public void showCollegeDetails(College college, Stage primaryStage) {
        VBox detailsLayout = new VBox(10);
        detailsLayout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name: " + college.getName());
        Label addressLabel = new Label("Address: " + college.getAddress());
        Label descriptionLabel = new Label("Description: " + college.getDescription());

        // Load and display the full-size image
        Image smallImage = new Image(getClass().getResourceAsStream(college.getImage()), 200, 200, true, true);
        ImageView imageView = new ImageView(smallImage);

        detailsLayout.getChildren().addAll(nameLabel, addressLabel, descriptionLabel, imageView);
        Button applyTransferButton = new Button("Apply Transfer Form");
        CollegeTransferApplicationForm newForm = new CollegeTransferApplicationForm();
        applyTransferButton.setOnAction(event -> {
            Scene transferFormScene = newForm.Form(primaryStage, college);
            primaryStage.setScene(transferFormScene);
        });
        detailsLayout.getChildren().add(applyTransferButton);
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            primaryStage.setScene(listCollege(primaryStage));
        });
        detailsLayout.getChildren().add(backButton);
        Scene detailsScene = new Scene(detailsLayout, primaryStage.getWidth(), primaryStage.getHeight());
        primaryStage.setScene(detailsScene);
    }
}
