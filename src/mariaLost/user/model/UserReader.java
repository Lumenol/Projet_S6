package mariaLost.user.model;

import javafx.scene.control.Alert;
import mariaLost.mainApp.controller.MainApp;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;


/**
 * Created by elsacollet on 29/01/2017.
 */
public class UserReader {

    /**
     * TODO gerer la creation du fichier configuration.xml sans erreur affichée +
     */
    private MainApp mainApp;

    private String fileName = "configuration.xml";
    private File file;

    public UserReader(MainApp mapp) {
        this.mainApp = mapp;

        file = new File(fileName);

        loadUserFromFile();
    }

    public File getUserFile() {
        return file;
    }

    /**
     * Charge les joueurs du fichier .xml dans la liste
     */
    public void loadUserFromFile() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(UserListWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // Reading XML from the file and unmarshalling.
            UserListWrapper wrapper = (UserListWrapper) unmarshaller.unmarshal(this.file);
            this.mainApp.setUserList(wrapper.getListWrapperUser());

        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Loading file impossible");
            alert.setContentText("An error occurred. The file " + this.file.getName() + " into " + this.file.getPath() + " can't be open." +
                    " Please be sure it's an existing .xml file." + e.getMessage());
            e.printStackTrace();

            alert.showAndWait();

        }
    }

    /**
     * Permet d'enregistrer les joueurs dans le fichier
     */
    public void saveUserToFile() {
        try {
            JAXBContext context = JAXBContext.newInstance(UserListWrapper.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Wrapping the user .
            UserListWrapper wrapper = new UserListWrapper();
            wrapper.setListWrapperUser(this.mainApp.getUserList());
            // Marshalling and saving XML to the file.
            marshaller.marshal(wrapper, this.file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + this.file.getPath());
            alert.showAndWait();
        }
    }

}
