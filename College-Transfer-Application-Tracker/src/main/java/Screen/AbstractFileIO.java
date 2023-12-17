
package Screen;

import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class AbstractFileIO implements FileIO {

    @Override
    public void saveObjectToFile(CollegeTransferApplication object) {
        List<CollegeTransferApplication> objects = loadObjectsFromFile("applications.ser");
        objects.add(object);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("applications.ser"))) {
            oos.writeObject(objects);
            System.out.println("Object saved successfully to " + "applications.ser");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CollegeTransferApplication> loadObjectsFromFile(String fileName) {
        List<CollegeTransferApplication> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            objects = (List<CollegeTransferApplication>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return objects;
    }
}
