package pl.chylu.ui.gui.rooms;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.room.RoomService;
import pl.chylu.domain.room.dto.RoomDTO;

import java.util.List;

public class RoomsTab {
    private Tab roomTab;
    private RoomService roomService = ObjectPool.getRoomService();

    public RoomsTab(Stage primaryStage) {
        TableView<RoomDTO> tableView = getRoomDTOTableView();

        Button button = new Button("Stwórz nowy");
        button.setOnAction(actionEvent -> {
            Stage addRoomPopup = new Stage();
            addRoomPopup.initModality(Modality.WINDOW_MODAL);
            addRoomPopup.setScene(new AddNewRoomScene(addRoomPopup, tableView).getMainScene());
            addRoomPopup.initOwner(primaryStage);
            addRoomPopup.setTitle("Dodawanie nowego pokoju");
            addRoomPopup.showAndWait();

        });

        VBox layout = new VBox(button, tableView);

        this.roomTab = new Tab("Pokoje", layout);
        this.roomTab.setClosable(false);
    }

    private TableView<RoomDTO> getRoomDTOTableView() {
        TableView<RoomDTO> tableView = new TableView<>();

        TableColumn<RoomDTO, Integer> numberColumn = new TableColumn<>("Numer");
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));

        TableColumn<RoomDTO, String> bedsColumn = new TableColumn<>("Typy łóżek");
        bedsColumn.setCellValueFactory(new PropertyValueFactory<>("beds"));

        TableColumn<RoomDTO, Integer> bedsCountColumn = new TableColumn<>("Ilość łóżek");
        bedsCountColumn.setCellValueFactory(new PropertyValueFactory<>("bedsCount"));

        TableColumn<RoomDTO, Integer> roomSizeColumn = new TableColumn<>("Rozmiar Pokoju");
        roomSizeColumn.setCellValueFactory(new PropertyValueFactory<>("roomSize"));

        TableColumn<RoomDTO, RoomDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            Button deleteButton = new Button("Usuń!");
            @Override
            protected void updateItem(RoomDTO value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null) {
                    setGraphic(null);
                    return;
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(actionEvent -> {
                        roomService.removeRoom(value.getId());
                        tableView.getItems().remove(value );
                    });
                }
            }
        });

        tableView.getColumns().addAll(numberColumn, roomSizeColumn, bedsCountColumn, bedsColumn, deleteColumn);
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);
        return tableView;
    }

    public Tab getRoomTab() {
        return roomTab;
    }
}
