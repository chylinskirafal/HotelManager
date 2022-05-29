package pl.chylu;

import pl.chylu.ui.text.TextUI;

public class App {
    private static TextUI textUI = new TextUI();

    public static void main(String[] args) {
        String hotelName = "TimeToSleep";
        int systemVersion = 1;
        boolean isDeveloperVersion = true;

        textUI.showSystemInfo(hotelName, systemVersion, isDeveloperVersion);
        textUI.showMainMenu();
    }
}
