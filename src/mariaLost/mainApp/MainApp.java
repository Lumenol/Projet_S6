package mariaLost.mainApp;

import javafx.application.Application;
import javafx.stage.Stage;
import mariaLost.parameters.Parameters_MariaLost;


public class MainApp extends Application {

    private Stage primaryStage;
    private Starter frontalControler;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.frontalControler = Starter.getInstance(this);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Maria Lost");
        this.primaryStage.getIcons().add(Parameters_MariaLost.IMAGE_LOGO);

        this.primaryStage.setMinWidth(Parameters_MariaLost.PAGE_WIDTH);
        this.primaryStage.setMinHeight(Parameters_MariaLost.PAGE_HEIGHT);
        this.frontalControler.start();
    }


    public Stage getPrimaryStage() {
        return primaryStage;
    }


}
