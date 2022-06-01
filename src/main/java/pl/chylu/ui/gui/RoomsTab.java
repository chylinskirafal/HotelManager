package pl.chylu.ui.gui;


import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
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

        tableView.getColumns().addAll(numberColumn, roomSizeColumn, bedsCountColumn, bedsColumn);
        List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);
        return tableView;
    }

    Tab getRoomTab() {
        return roomTab;
    }
}
