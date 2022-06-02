package pl.chylu.ui.gui.reservations;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.reservation.ReservationService;
import pl.chylu.domain.reservation.dto.ReservationDTO;
import pl.chylu.domain.room.RoomService;
import pl.chylu.domain.room.dto.RoomDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewReservationScene {
    private GuestService guestService = ObjectPool.getGuestService();
    private RoomService roomService = ObjectPool.getRoomService();
    private final Scene mainScene;
    private ReservationService reservationService = ObjectPool.getReservationService();
    public AddNewReservationScene(Stage addReservationPopup, TableView<ReservationDTO> tableView) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        Label fromDateLabel = new Label("Data rozpoczęcia:");
        DatePicker fromDateField = new DatePicker();
        gridPane.add(fromDateLabel, 0, 0);
        gridPane.add(fromDateField, 2, 0);

        Label toDateLabel = new Label("Data zakończenia:");
        DatePicker toDateField = new DatePicker();
        gridPane.add(toDateLabel, 0, 1);
        gridPane.add(toDateField, 2, 1);

        List<GuestSelectionItem> guestSelectionItems = new ArrayList<>();
        guestService.getAllAsDTO().forEach(dto -> {
            guestSelectionItems.add(new GuestSelectionItem(dto.getId(),
                    dto.getFirstName(), dto.getLastName()));
        });

        Label guestLabel = new Label("Rezerwujący:");
        ComboBox<GuestSelectionItem> guestField = new ComboBox<>();
        guestField.getItems().addAll(guestSelectionItems);
        gridPane.add(guestLabel,0,2);
        gridPane.add(guestField,2,2);

        List<RoomSelectionItem> roomSelectionItems = new ArrayList<>();
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();

        allAsDTO.forEach(dto -> {
            roomSelectionItems.add(
                    new RoomSelectionItem(dto.getNumber(), dto.getId())
            );
        });

        Label roomLabel = new Label("Pokój:");
        ComboBox<RoomSelectionItem> roomField = new ComboBox<>();
        roomField.getItems().addAll(roomSelectionItems);
        gridPane.add(roomLabel,0,3);
        gridPane.add(roomField,2,3);

        Button addReservation = new Button("Dodaj rezerwację!");
        gridPane.add(addReservation, 0, 4);

        addReservation.setOnAction(actionEvent -> {
            LocalDate from = fromDateField.getValue();
            LocalDate to = toDateField.getValue();
            int guestID = guestField.getValue().getId();
            int roomID = roomField.getValue().getId();

            try {
                //TODO make throw to no choice guest/room
                if (to.getDayOfYear() < from.getDayOfYear()) {
                    throw new IllegalArgumentException();
                }
                this.reservationService.createNewReservation(from, to, roomID, guestID);
                tableView.getItems().clear();
                List<ReservationDTO> allSaveAsDTO = reservationService.getAllAsDTO();
                tableView.getItems().addAll(allSaveAsDTO);
                addReservationPopup.close();
            } catch (IllegalArgumentException ex) {
                Label error = new Label("Niepoprawne daty.");
                error.setTextFill(Color.RED);
                gridPane.add(error, 2, 4);
            }
        });

        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("hotelMenager.css")).toExternalForm());
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
