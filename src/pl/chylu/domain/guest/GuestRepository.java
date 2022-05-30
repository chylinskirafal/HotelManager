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
        Guest newGuest = new Guest(findNewId(), firstName, lastName, age, gender);
        guests.add(newGuest);
        return newGuest;
    }

    Guest addExitsGuest(int id, String firstName, String lastName, int age, Gender gender) {
        Guest newGuest = new Guest(id, firstName, lastName, age, gender);
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
        for (Guest guest : this.guests) {
            sb.append(guest.toCSV());
        }
        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if (!Files.isDirectory(reservation_system_dir)) {
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

        if (!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] guestsAsString = data.split(System.getProperty("line.separator"));

            for (String guestAsStrings : guestsAsString) {
                String[] guestData = guestAsStrings.split(",");
                int id = Integer.parseInt(guestData[0]);
                int ageData = Integer.parseInt(guestData[3]);
                Gender genderData = Gender.valueOf(guestData[4]);
                addExitsGuest(id, guestData[1], guestData[2], ageData, genderData);
            }
        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku z poprzednio zapisanymi danymi.");
            e.printStackTrace();
        }
    }

    private int findNewId() {
        int max = 0;
        for (Guest guest : this.guests) {
            if (guest.getId() > max) {
                max = guest.getId();
            }
        }
        return max + 1;
    }

    public void remove(int id) {
        int guestToBeRemovedIndex = -1;
        for(int i = 0; i <this.guests.size(); i++) {
            if(this.guests.get(i).getId() == id) {
                guestToBeRemovedIndex = i;
                break;
            }
        }

        if (guestToBeRemovedIndex > -1) {
            this.guests.remove(guestToBeRemovedIndex);
        }
    }

    public void edit(int id, String firstName, String lastName, int age, Gender gender) {
        remove(id);
        addExitsGuest(id, firstName, lastName, age, gender);
    }
}