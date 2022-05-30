package pl.chylu.ui.gui;


import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainTabView {
    private final TabPane mainTabs;

    public MainTabView() {
        this.mainTabs = new TabPane();

        Tab reservationTab = new Tab("Rezerwacje", new Label("Obsługa rezerwacji"));

        RoomsTab roomsTab = new RoomsTab();
        GuestTab guestTab = new GuestTab();

        this.mainTabs.getTabs().addAll(reservationTab, guestTab.getGuestTab(), roomsTab.getRoomTab());
    }

    protected TabPane getMainTabs() {
        return mainTabs;
    }
}
