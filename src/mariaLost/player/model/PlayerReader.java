package mariaLost.player.model;

import javafx.scene.control.Alert;
import mariaLost.mainApp.controller.MainApp;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.prefs.Preferences;


/**
 * Created by elsacollet on 29/01/2017.
 */
public class PlayerReader {

    /**
     * TODO gerer la creation du fichier configuration.xml sans erreur affichée +
     */
    private MainApp mainApp;
    private Preferences preferences;

    private String playerFile = "parametersPlayerFile";
    private String fileName = "configuration.xml";
    private File file;

    public PlayerReader(MainApp mapp){
        this.mainApp= mapp;

        preferences = Preferences.systemNodeForPackage(MainApp.class);
        preferences.put(playerFile, fileName);
        file = new File(preferences.get(playerFile, fileName));

        loadPlayerFromFile();
        //setPlayerFilePath(file);
    }

    public File getPlayerFilePath(){
        return file;
    }

    public void setPlayerFilePath(File file) {
        this.file = file;

    }

    /**
     * Charge les joueurs du fichier .xml dans la liste
     */
    public void loadPlayerFromFile(){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(PlayerListWrapper.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            // Reading XML from the file and unmarshalling.
            PlayerListWrapper wrapper = (PlayerListWrapper) unmarshaller.unmarshal(file);
            mainApp.setPlayerList(wrapper.getListWrapperPlayer());


        }catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Loading file impossible");
            alert.setContentText("An error occurred. The file "+ file.getName() +" into "+ file.getPath()+" can't be open." +
                    " Please be sure it's an existing .xml file.");
            alert.showAndWait();

        }
    }

    /**
     * Permet d'enregistrer les joueurs dans le fichier
     */
    public void savePlayerToFile(){
        try {
            JAXBContext context = JAXBContext.newInstance(PlayerListWrapper.class);

            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            // Wrapping the player .
            PlayerListWrapper wrapper = new PlayerListWrapper();
            wrapper.setListWrapperPlayer(mainApp.getPlayerList());
            // Marshalling and saving XML to the file.
            marshaller.marshal(wrapper, file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());
            alert.showAndWait();
        }
    }

}
