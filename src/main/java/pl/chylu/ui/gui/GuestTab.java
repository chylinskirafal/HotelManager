package pl.chylu.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.guest.dto.GuestDTO;
import pl.chylu.domain.reservation.ReservationService;

import java.util.List;

public class GuestTab {
    private Tab guestTab;
    private GuestService guestService = GuestService.getInstance();

    public GuestTab() {
        TableView<GuestDTO> tableView = new TableView<>();

        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("Imię");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<GuestDTO, Integer> ageColumn = new TableColumn<>("Wiek");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Płeć");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn);

        List<GuestDTO> allAsDTO = guestService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);

        guestTab = new Tab("Goście", tableView);
        guestTab.setClosable(false);
    }

    Tab getGuestTab() {
        return guestTab;
    }
}
