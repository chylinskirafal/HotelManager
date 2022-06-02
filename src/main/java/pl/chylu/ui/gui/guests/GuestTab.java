package pl.chylu.ui.gui.guests;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.guest.dto.GuestDTO;

import java.util.List;

public class GuestTab {
    private Tab guestTab;
    private GuestService guestService = ObjectPool.getGuestService();

    public GuestTab(Stage primaryStage) {
        TableView<GuestDTO> tableView = getGuestDTOTableView();

        Button addGuestButton = new Button("Dodaj Gościa");
        addGuestButton.setOnAction(actionEvent -> {
            Stage addGuestPopup = new Stage();
            addGuestPopup.initModality(Modality.WINDOW_MODAL);
            addGuestPopup.setScene(new AddNewGuestScene(addGuestPopup, tableView).getMainScene());
            addGuestPopup.initOwner(primaryStage);
            addGuestPopup.setTitle("Dodawanie nowego gościa");
            addGuestPopup.showAndWait();

        });

        VBox mainLayout = new VBox(addGuestButton, tableView);

        guestTab = new Tab("Goście", mainLayout);
        guestTab.setClosable(false);
    }

    private TableView<GuestDTO> getGuestDTOTableView() {
        TableView<GuestDTO> tableView = new TableView<>();

        TableColumn<GuestDTO, String> firstNameColumn = new TableColumn<>("Imię");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));

        TableColumn<GuestDTO, String> lastNameColumn = new TableColumn<>("Nazwisko");
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        TableColumn<GuestDTO, Integer> ageColumn = new TableColumn<>("Wiek");
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<GuestDTO, String> genderColumn = new TableColumn<>("Płeć");
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<GuestDTO, GuestDTO> deleteColumn = new TableColumn<>("Usuń");
        deleteColumn.setCellValueFactory(value -> new ReadOnlyObjectWrapper(value.getValue()));

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            Button deleteButton = new Button("Usuń!");
            @Override
            protected void updateItem(GuestDTO value, boolean empty) {
                super.updateItem(value, empty);
                if (value == null) {
                    setGraphic(null);
                    return;
                } else {
                    setGraphic(deleteButton);
                    deleteButton.setOnAction(actionEvent -> {
                        guestService.removeGuest(value.getId());
                        tableView.getItems().remove(value );
                    });
                }
            }
        });

        tableView.getColumns().addAll(firstNameColumn, lastNameColumn, ageColumn, genderColumn, deleteColumn);

        List<GuestDTO> allAsDTO = guestService.getAllAsDTO();
        tableView.getItems().addAll(allAsDTO);
        return tableView;
    }

    public Tab getGuestTab() {
        return guestTab;
    }
}
