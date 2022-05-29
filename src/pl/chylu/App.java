package pl.chylu;

import pl.chylu.exception.PersistenceToFileException;
import pl.chylu.ui.text.TextUI;
import pl.chylu.util.Properties;

import java.io.IOException;

public class App {
    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {
        String hotelName = "TimeToSleep";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;

        try {
            Properties.createDataDirectory();
        } catch (IOException e) {
            throw new PersistenceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }

        textUI.showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        textUI.showMainMenu();
    }
}
