package pl.chylu.ui.gui.guests;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pl.chylu.domain.ObjectPool;
import pl.chylu.domain.guest.Gender;
import pl.chylu.domain.guest.GuestService;
import pl.chylu.domain.guest.dto.GuestDTO;
import pl.chylu.util.Properties;

import java.util.List;
import java.util.Objects;

public class AddNewGuestScene {
    private final Scene mainScene;
    private final GuestService guestService = ObjectPool.getGuestService();
    public AddNewGuestScene(Stage addGuestPopup, TableView<GuestDTO> tableView) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setVgap(15);

        Label guestFirstNameLabel = new Label("Imię gościa");
        TextField guestFirstNameField = new TextField();
        gridPane.add(guestFirstNameLabel, 0, 0);
        gridPane.add(guestFirstNameField, 2,0);

        guestFirstNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                guestFirstNameField.setText(oldValue);
            }
        }));

        Label guestLastNameLabel = new Label("Nazwisko gościa");
        TextField guestLastNameField = new TextField();
        gridPane.add(guestLastNameLabel, 0, 1);
        gridPane.add(guestLastNameField, 2,1);

        guestLastNameField.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            if (!newValue.matches("\\p{L}*")) {
                guestLastNameField.setText(oldValue);
            }
        }));

        Label guestAgeLabel = new Label("Wiek gościa");
        TextField guestAgeField = new TextField();
        gridPane.add(guestAgeLabel, 0, 2);
        gridPane.add(guestAgeField, 2,2);

        guestAgeField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                guestAgeField.setText(newValue.replaceAll("\\D", ""));
            }
            int checkAge = Integer.parseInt(newValue);
            if (checkAge > 150) {
                guestAgeField.setText(oldValue);
            }
        });



        ComboBox genderBox = new ComboBox<>();
        genderBox.getItems().addAll(Properties.MALE, Properties.FEMALE);
        genderBox.setValue(Properties.MALE);
        Label guestGenderLabel = new Label("Płeć gościa");
        gridPane.add(genderBox, 2, 3);
        gridPane.add(guestGenderLabel, 0, 3);

        Button addGuestButton = new Button("Dodaj gościa!");
        addGuestButton.setOnAction(actionEvent -> {
            String firstName = guestFirstNameField.getText();
            String lastName = guestLastNameField.getText();
            int age = Integer.parseInt(guestAgeField.getText());
            boolean gender = true;
            if (genderBox.getValue().equals(Properties.FEMALE)) {
                gender = false;
            }
            guestService.createNewGuest(firstName, lastName, age, gender);
            tableView.getItems().clear();
            List<GuestDTO> allAsDTO = guestService.getAllAsDTO();
            tableView.getItems().addAll(allAsDTO);
            addGuestPopup.close();

        });




        gridPane.add(addGuestButton, 1, 4);
        this.mainScene = new Scene(gridPane, 640, 480);
        this.mainScene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader()
                .getResource("hotelMenager.css")).toExternalForm());
    }

    public Scene getMainScene() {
        return mainScene;
    }
}
