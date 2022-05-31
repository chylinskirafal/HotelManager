package pl.chylu;


import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.Reservation;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.room.RoomService;

import javafx.application.Application;
import javafx.stage.Stage;
import pl.chylu.exception.PersistenceToFileException;
import pl.chylu.ui.gui.PrimaryStage;
import pl.chylu.util.Properties;

import java.io.IOException;

public class App extends Application {
    private static final GuestService guestService = new GuestService();
    private static final RoomService roomService = new RoomService();
    private static final ReservationService reservationService = new ReservationService();

    public static void main(String[] args) {
        try {
            Properties.createDataDirectory();
            System.out.println("Trwa ładowanie danych...");
            guestService.readAll();
            roomService.readAll();
            reservationService.readAll();
        } catch (IOException e) {
            throw new PersistenceToFileException(Properties.DATA_DIRECTORY.toString(), "create", "directory");
        }

        Application.launch(args);
        //TextUI textUI = new TextUI();
        //textUI.showSystemInfo();
        //textUI.showMainMenu();
    }

    public void start(Stage primaryStage) {
        PrimaryStage primary = new PrimaryStage();
        primary.initialize(primaryStage);
    }
}