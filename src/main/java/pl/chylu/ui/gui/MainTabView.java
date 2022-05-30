package pl.chylu.ui.gui;


import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainTabView {
    private final TabPane mainTabs;

    public MainTabView() {
        this.mainTabs = new TabPane();

        Tab reservationTab = new Tab("Rezerwacje", new Label("Obsługa rezerwacji"));
        Tab guestTab = new Tab("Goście", new Label("Obsługa gości"));


        //brak możliwości zamykania tabów
        reservationTab.setClosable(false);
        guestTab.setClosable(false);

        RoomsTab roomsTab = new RoomsTab();

        this.mainTabs.getTabs().addAll(reservationTab, guestTab, roomsTab.getRoomTab());
    }

    protected TabPane getMainTabs() {
        return mainTabs;
    }
}
