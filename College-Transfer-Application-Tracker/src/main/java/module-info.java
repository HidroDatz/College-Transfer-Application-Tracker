module com.mycompany.college.transfer.application.tracker {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.college.transfer.application.tracker to javafx.fxml;
    exports com.mycompany.college.transfer.application.tracker;
}
