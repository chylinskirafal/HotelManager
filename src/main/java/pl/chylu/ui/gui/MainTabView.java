package pl.chylu.ui.gui;

import javafx.scene.control.TabPane;
import javafx.stage.Stage;

public class MainTabView {
    private final TabPane mainTabs;

    public MainTabView(Stage primaryStage) {
        this.mainTabs = new TabPane();

        ReservationTab reservationTab = new ReservationTab();
        RoomsTab roomsTab = new RoomsTab(primaryStage);
        GuestTab guestTab = new GuestTab();

        this.mainTabs.getTabs().addAll(reservationTab.getReservationTab(), guestTab.getGuestTab(), roomsTab.getRoomTab());
    }

    protected TabPane getMainTabs() {
        return mainTabs;
    }
}
