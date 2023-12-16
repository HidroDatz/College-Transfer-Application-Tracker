package Screen;

import com.mycompany.college.transfer.application.tracker.College;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CollegeListDisplay extends Application {

 

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("College List Display");

        TableView<College> tableView = new TableView<>();
        ObservableList<College> colleges = FXCollections.observableArrayList(
                new College("Harvard University", "Cambridge, MA", "Ivy League institution known for excellence in education and research.", "/images/harvard_image.jpg"),
                new College("Massachusetts Institute of Technology (MIT)", "Cambridge, MA", "Renowned for its focus on science, engineering, and technology.", "/images/mit_image.jpg"),
                new College("Stanford University", "Stanford, CA", "Leading research university with a strong emphasis on innovation.", "/images/stanford_image.jpg"),
                new College("Yale University", "New Haven, CT", "Ivy League university known for its distinguished faculty and rich history.", "/images/yale_image.jpg"),
                new College("University of California, Los Angeles (UCLA)", "Los Angeles, CA", "A public research university with a diverse and vibrant campus.", "/images/ucla_image.jpg")
        );

        TableColumn<College, Integer> numberColumn = new TableColumn<>("No.");
        numberColumn.setCellValueFactory(cellData -> {
            int rowIndex = tableView.getItems().indexOf(cellData.getValue()) + 1;
            return javafx.beans.binding.Bindings.createObjectBinding(() -> rowIndex);
        });

        TableColumn<College, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<College, String> imageColumn = new TableColumn<>("Image");
        imageColumn.setCellValueFactory(new PropertyValueFactory<>("imagePath"));
        imageColumn.setCellFactory(param -> new javafx.scene.control.TableCell<>() {
            private final ImageView imageView = new ImageView();

            @Override
            protected void updateItem(String imagePath, boolean empty) {
                super.updateItem(imagePath, empty);
                if (empty || imagePath == null) {
                    setGraphic(null);
                } else {
                    Image image = new Image(getClass().getClassLoader().getResourceAsStream(imagePath), 50, 50, true, true);
                    imageView.setImage(image);
                    setGraphic(imageView);
                }
            }
        });

        tableView.getColumns().addAll(numberColumn, nameColumn, imageColumn);
        tableView.setItems(colleges);

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private static College searchCollegeByName(String name, ArrayList<College> colleges) {
        for (College college : colleges) {
            if (college.getName().equalsIgnoreCase(name)) {
                return college;
            }
        }
        return null;
    }
}
