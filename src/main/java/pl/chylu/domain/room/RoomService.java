package pl.chylu.domain.room;

import pl.chylu.exception.WrongOptionException;

import java.util.List;

public class RoomService {
    private static final RoomRepository repository = new RoomRepository();
    public Room createNewRoom(int number, int[] bedTypesOptions) {
        BedType[] bedTypes = new BedType[bedTypesOptions.length];
        for(int i=0;i<bedTypesOptions.length;i=i+1) {
            BedType bedType;
            if (bedTypesOptions[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesOptions[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesOptions[i] == 3) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }
        return repository.createNewRoom(number, bedTypes);
    }

    public List<Room> getAllRooms() {
        return repository.getAll();
    }

    public void saveAll() {
        repository.saveAll();
    }

    public void readAll() {
        repository.readAll();
    }

    public void removeRoom(int id) {
        repository.remove(id);
    }

    public void editRoom(int id, int number, int[] bedTypesInput) {
        BedType[] bedTypes = new BedType[bedTypesInput.length];
        for(int i=0;i<bedTypesInput.length;i=i+1) {
            BedType bedType;
            if (bedTypesInput[i] == 1) {
                bedType = BedType.SINGLE;
            } else if (bedTypesInput[i] == 2) {
                bedType = BedType.DOUBLE;
            } else if (bedTypesInput[i] == 3) {
                bedType = BedType.KING_SIZE;
            } else {
                throw new WrongOptionException("Wrong option when selecting bed type");
            }
            bedTypes[i] = bedType;
        }

        repository.edit(id, number, bedTypes);
    }

    public Room getRoomById(int roomId) {
        return repository.getById(roomId);
    }
}