package pl.chylu.ui.gui.reservations;

import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.reservation.dto.ReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationTab {
    private Tab reservationTab;
    private ReservationService reservationService = ObjectPool.getReservationService();
    public ReservationTab(Stage primaryStage) {
        TableView<ReservationDTO> tableView = getReservationDTOTableView();
        Button addReservationButton = new Button("Dodaj rezerwację");

        addReservationButton.setOnAction(actionEvent -> {
            Stage addReservationPopup = new Stage();
            addReservationPopup.initModality(Modality.WINDOW_MODAL);
            addReservationPopup.setScene(new AddNewReservationScene(addReservationPopup, tableView).getMainScene());
            addReservationPopup.initOwner(primaryStage);
            addReservationPopup.setTitle("Dodawanie nowej rezerwacji");
            addReservationPopup.showAndWait();

        });


        VBox layout = new VBox(addReservationButton, tableView);

        List<ReservationDTO> allAsDTO = reservationService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);

        reservationTab = new Tab("Rezerwacje", layout);
        reservationTab.setClosable(false);
    }

    private TableView<ReservationDTO> getReservationDTOTableView() {
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
        return tableView;
    }

    public Tab getReservationTab() {
        return reservationTab;
    }
}
