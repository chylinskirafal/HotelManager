package pl.chylu.ui.gui.rooms;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.room.RoomService;
import pl.chylu.domain.room.dto.RoomDTO;
import pl.chylu.util.Properties;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddNewRoomScene {
    private final Scene mainScene;
    private final List<ComboBox<String>> comboBoxes = new ArrayList<>();
    private final RoomService roomService = ObjectPool.getRoomService();

    public AddNewRoomScene(Stage addRoomPopup, TableView<RoomDTO> tableView) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(20);

        Label roomNumberLabel = new Label("Numer pokoju:");
        TextField roomNumberField = new TextField();

        //Only Numbers in TextField Room Number
        roomNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                roomNumberField.setText(newValue.replaceAll("\\D", ""));
            }
        });

        gridPane.add(roomNumberLabel, 0, 0);
        gridPane.add(roomNumberField, 1, 0);

        Label bedTypeLabel = new Label("Typy łóżek:");
        Button addNewBedBox = new Button();

        Image icon = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("plus.png")));
        ImageView imageView = new ImageView(icon);
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);

        addNewBedBox.setGraphic(imageView);
        addNewBedBox.setPadding(new Insets(5,5,5,5));

        gridPane.add(bedTypeLabel, 0, 1);
        gridPane.add(addNewBedBox, 1, 1);
        VBox bedsVerticalLayout = new VBox(getComboBox());

        //TODO make button to remove last ComboBox
        addNewBedBox.setOnAction(actionEvent -> bedsVerticalLayout.getChildren().add(getComboBox()));

        Button addRoom = new Button("Dodaj pokój do bazy");
        addRoom.setOnAction(actionEvent -> {
            int newRoomNumber = Integer.parseInt(roomNumberField.getText());
            List<String> bedTypes = new ArrayList<>();

            this.comboBoxes.forEach(comboBox -> bedTypes.add(comboBox.getValue()));

            this.roomService.createNewRoom(newRoomNumber, bedTypes);
            tableView.getItems().clear();
            List<RoomDTO> allAsDTO = roomService.getAllAsDTO();
            tableView.getItems().addAll(allAsDTO);
            addRoomPopup.close();
        });

        gridPane.add(bedsVerticalLayout, 1, 2);
        gridPane.add(addRoom, 0, 3);

        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("hotelMenager.css")).toExternalForm());
    }

    private ComboBox<String> getComboBox() {
        ComboBox<String> bedTypeField = new ComboBox<>();
        bedTypeField.getItems().addAll(Properties.SINGLE_BED,Properties.DOUBLE_BED, Properties.KING_SIZE);
        bedTypeField.setValue(Properties.SINGLE_BED);
        this.comboBoxes.add(bedTypeField);
        return bedTypeField;
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
