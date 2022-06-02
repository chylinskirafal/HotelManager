package pl.chylu.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.chylu.ui.gui.guests.GuestTab;
import pl.chylu.ui.gui.reservations.ReservationTab;
import pl.chylu.ui.gui.rooms.RoomsTab;

public class MainTabView {
    private final TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        ReservationTab reservationTab = new ReservationTab(primaryStage);
        RoomsTab roomsTab = new RoomsTab(primaryStage);
        GuestTab guestTab = new GuestTab(primaryStage);

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTab(), guestTab.getGuestTab(), roomsTab.getRoomTab());
    }

    protected TabPane getMainTabs() {
        return mainTabs;
    }
}
