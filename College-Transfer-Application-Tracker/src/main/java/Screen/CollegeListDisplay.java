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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CollegeListDisplay extends Application {

    /**
     * List of colleges displayed in the TableView.
     */
    final static ArrayList<College> colleges = new ArrayList<>();

    /**
     * The main entry point for the JavaFX application.
     *
     * @param primaryStage The primary stage for the application.
     */
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

    /**
     * Handles the search functionality by filtering the colleges based on the
     * search term.
     *
     * @param searchTerm The term to search for in college names.
     * @param tableView The TableView displaying the colleges.
     */
    private void searchCollege(String searchTerm, TableView<College> tableView) {
        ObservableList<College> filteredColleges = FXCollections.observableArrayList();

        for (College college : colleges) {
            if (college.getName().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredColleges.add(college);
            }
        }

        tableView.setItems(filteredColleges);
    }

    /**
     * Clears the search and restores the original list of colleges.
     *
     * @param tableView The TableView displaying the colleges.
     * @param observableColleges The original list of colleges.
     */
    private void clearSearch(TableView<College> tableView, ObservableList<College> observableColleges) {
        tableView.setItems(observableColleges);
    }

    /**
     * Creates the main scene displaying the list of colleges in a TableView.
     *
     * @param primaryStage The primary stage for the application.
     * @return The Scene object for the college list display.
     */
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
        Button showTransferApplicationsButton = new Button("Show Transfer Applications");

        showTransferApplicationsButton.setOnAction(event -> {
            TransferApplicationListView transferForm = new TransferApplicationListView();
            Scene transferListScene = transferForm.TransferListDisplay(primaryStage);
            primaryStage.setScene(transferListScene);
        });
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.getColumns().addAll(numberColumn, nameColumn);
        tableView.setItems(observableColleges);
        tableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1) {
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
        HBox buttonsBox = new HBox(10, searchButton, clearButton, showTransferApplicationsButton);
        buttonsBox.setAlignment(Pos.CENTER);
        VBox vbox = new VBox(searchField, buttonsBox, tableView);
        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 400);

        return scene;
    }

    /**
     * Displays the details of a selected college and provides the option to
     * apply for a transfer form.
     *
     * @param college The selected college.
     * @param primaryStage The primary stage for the application.
     */
    public void showCollegeDetails(College college, Stage primaryStage) {
        VBox detailsLayout = new VBox(10);
        detailsLayout.setAlignment(Pos.CENTER);

        Label nameLabel = new Label("Name: " + college.getName());
        Label addressLabel = new Label("Address: " + college.getAddress());
        Label descriptionLabel = new Label("Description: " + college.getDescription());

        Image smallImage = new Image(getClass().getResourceAsStream(college.getImage()), 200, 200, true, true);
        ImageView imageView = new ImageView(smallImage);

        detailsLayout.getChildren().addAll(nameLabel, addressLabel, descriptionLabel, imageView);
        Button applyTransferButton = new Button("Apply Transfer Form");
        TransferApplicationForm newForm = new TransferApplicationForm();
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
