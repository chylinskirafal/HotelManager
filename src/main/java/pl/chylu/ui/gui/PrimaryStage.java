package pl.chylu.ui.gui;

import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.chylu.util.Properties;

public class PrimaryStage {
    public void initialize(Stage primaryStage) {
        String hotelName = Properties.HOTEL_NAME;
        int systemVersion = Properties.SYSTEM_VERSION;

        MainTabView mainTabView = new MainTabView(primaryStage);

        Scene scene = new Scene(mainTabView.getMainTabs(), 840, 680);
        scene.getStylesheets().add(getClass().getClassLoader()
                .getResource("hotelMenager.css").toExternalForm());
        String title = String.format("System rezerwacji hotelu %s (%d)", hotelName, systemVersion);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
