package pl.chylu.domain.guest;

import pl.chylu.domain.repository.Repository;
import pl.chylu.exception.PersistenceToFileException;
import pl.chylu.util.Properties;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GuestRepository extends Repository {
    List<Guest> guests = new ArrayList<>();
    Guest createNewGuest(String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }
    public List<Guest> getAll() {
        return this.guests;
    }

    @Override
    protected void saveAll() {
        String name = "guests.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");
        for(Guest guest : this.guests) {
            sb.append(guest.toCSV());
        }
        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if(!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "write", "guests data");
        }
    }

    @Override
    protected void readAll() {
        String name = "guests.csv";

        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));

            for (String guestAsStrings : guestsAsString) {
                String[] guestData = guestAsStrings.split(",");
                int ageData = Integer.parseInt(guestData[2]);
                Gender genderData = Gender.valueOf(guestData[3]);
                createNewGuest(guestData[0], guestData[1], ageData, genderData);
            }
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku z poprzednio zapisanymi danymi.");
            e.printStackTrace();
        }
    }
}