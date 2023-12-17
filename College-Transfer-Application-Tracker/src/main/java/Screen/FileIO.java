
package Screen;

import com.mycompany.college.transfer.application.tracker.CollegeTransferApplication;
import java.util.List;


public interface FileIO {
void saveObjectToFile(CollegeTransferApplication object);
    List<CollegeTransferApplication> loadObjectsFromFile(String fileName);
}
