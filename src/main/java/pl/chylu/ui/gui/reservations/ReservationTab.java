package pl.chylu.ui.gui.reservations;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.reservation.dto.ReservationDTO;
import pl.chylu.domain.room.dto.RoomDTO;

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

        TableColumn<ReservationDTO, Integer> numberRoomColumn = new TableColumn<>("Pokój");
        numberRoomColumn.setCellValueFactory(new PropertyValueFactory<>("numberRoom"));

        TableColumn<ReservationDTO, ReservationDTO> acionColumn = new TableColumn<>("Edytuj/Usuń");
        acionColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));

        acionColumn.setCellFactory(param -> new TableCell<>() {
            Button editButton = new Button("Edytuj");
            Button deleteButton = new Button("Usuń");
            HBox hBox = new HBox(editButton, deleteButton);
            @Override
            protected void updateItem(ReservationDTO value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null) {
                    setGraphic(null);
                    return;
                } else {
                    setGraphic(hBox);
                    deleteButton.setOnAction(actionEvent -> {
                        reservationService.removeReservation(value.getId());
                        tableView.getItems().remove(value );
                    });
                }
            }
        });


        tableView.getColumns().addAll(fromColumn, toColumn, firstNameColumn, lastNameColumn, numberRoomColumn, acionColumn);
        return tableView;
    }

    public Tab getReservationTab() {
        return reservationTab;
    }
}
