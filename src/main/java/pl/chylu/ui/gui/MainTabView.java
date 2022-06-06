package pl.chylu.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;
import pl.chylu.ui.gui.guests.GuestsTab;
import pl.chylu.ui.gui.reservations.ReservationsTab;
import pl.chylu.ui.gui.rooms.RoomsTab;

public class MainTabView {

    private TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        RoomsTab roomsTab = new RoomsTab(primaryStage);
        ReservationsTab reservationTab = new ReservationsTab(primaryStage);
        GuestsTab guestsTab = new GuestsTab(primaryStage);

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTab(), guestsTab.getGuestTab(), roomsTab.getRoomTab());
    }

    TabPane getMainTabs() {
        return mainTabs;
    }
}
