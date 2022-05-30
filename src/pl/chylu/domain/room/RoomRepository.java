package pl.chylu.domain.room;

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

public class RoomRepository extends Repository {
    private final List<Room> rooms = new ArrayList<>();

    Room createNewRoom(int number, BedType[] bedTypes) {
        Room newRoom = new Room(findNewId(), number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    Room addRoomExist(int id, int number, BedType[] bedTypes) {
        Room newRoom = new Room(id, number, bedTypes);
        rooms.add(newRoom);
        return newRoom;
    }

    public List<Room> getAll() {
        return this.rooms;
    }

    @Override
    protected void saveAll() {
        String name = "room.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        StringBuilder sb = new StringBuilder("");
        for (Room room : this.rooms) {
            sb.append(room.toCSV());
        }
        try {
            Path reservation_system_dir = Paths.get(System.getProperty("user.home"), "reservation_system");
            if (!Files.isDirectory(reservation_system_dir)) {
                Files.createDirectory(reservation_system_dir);
            }
            Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new PersistenceToFileException(file.toString(), "save", "room data");
        }
    }

    @Override
    protected void readAll() {
        String name = "room.csv";
        Path file = Paths.get(Properties.DATA_DIRECTORY.toString(), name);

        if (!Files.exists(file)) {
            return;
        }

        try {
            String data = Files.readString(file, StandardCharsets.UTF_8);
            String[] roomAsString = data.split(System.getProperty("line.separator"));

            for (String roomAsData : roomAsString) {
                String[] roomData = roomAsData.split(",");
                int number = Integer.parseInt(roomData[0]);
                int id = Integer.parseInt(roomData[0]);
                String bedTypesData = roomData[2];
                String[] bedsTypesAsString = bedTypesData.split("#");
                BedType[] bedTypes = new BedType[bedsTypesAsString.length];
                for (int i = 0; i < bedTypes.length; i++) {
                    bedTypes[i] = BedType.valueOf(bedsTypesAsString[i]);
                }
                addRoomExist(id, number, bedTypes);
            }

        } catch (IOException e) {
            System.out.println("Nie udało się odczytać pliku z poprzednio zapisanymi danymi.");
            e.printStackTrace();
        }
    }

    private int findNewId() {
        int max = 0;
        for (Room room : this.rooms) {
            if (room.getId() > max) {
                max = room.getId();
            }
        }
        return max + 1;
    }

    public void remove(int id) {
        int roomToBeRemovedIndex = -1;
        for(int i = 0; i <this.rooms.size(); i++) {
            if(this.rooms.get(i).getId() == id) {
                roomToBeRemovedIndex = i;
                break;
            }
        }

        if (roomToBeRemovedIndex > -1) {
            this.rooms.remove(roomToBeRemovedIndex);
        }
    }

    public void edit(int id, int number, BedType[] bedTypes) {
        remove(id);
        addRoomExist(id, number, bedTypes);
    }
}