package pl.chylu.ui.gui;

import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationTab {
    private Tab reservationTab;
    private ReservationService reservationService = ObjectPool.getReservationService();
    public ReservationTab() {
        TableView<ReservationDTO> tableView = new TableView<>();

        TableColumn<ReservationDTO, LocalDateTime> fromColumn = new TableColumn<>("Od kiedy?");
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));

        TableColumn<ReservationDTO, LocalDateTime> toColumn = new TableColumn<>("Do kiedy?");
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));

        TableColumn<ReservationDTO, String> firstNameColumn = new TableColumn<>("Imię");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<ReservationDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<ReservationDTO, Integer> numberRoomColumn = new TableColumn<>("Numer Pokoju");
        numberRoomColumn.setCellValueFactory(new PropertyValueFactory<>("numberRoom"));


        tableView.getColumns().addAll(fromColumn, toColumn, firstNameColumn, lastNameColumn, numberRoomColumn);

        List<ReservationDTO> allAsDTO = reservationService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);

        reservationTab = new Tab("Rezerwacje", tableView);
        reservationTab.setClosable(false);
    }
    public Tab getReservationTab() {
        return reservationTab;
    }
}
