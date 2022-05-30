package pl.chylu.domain.guest;

import java.util.List;

public class GuestService {

    private static final GuestRepository repository = new GuestRepository();

    public Guest createNewGuest(String firstName, String lastName, int age, boolean isMale) {
        Gender gender = Gender.FEMALE;

        if(isMale) {
            gender = Gender.MALE;
        }
        return repository.createNewGuest(firstName, lastName, age, gender);
    }
    public List<Guest> getAllGuests() {
        return repository.getAll();
    }
    public void saveAll() {
        repository.saveAll();
    }
    public void readAll() {
        repository.readAll();
    }

    public void removeGuest(int id) {
        repository.remove(id);
    }

    public void editGuest(int id, String firstName, String lastName, int age, boolean isMale) {
        Gender gender = Gender.FEMALE;

        if(isMale) {
            gender = Gender.MALE;
        }
        repository.edit(id, firstName, lastName, age, gender);
    }

    public Guest getGuestById(int guestId) {
        return repository.findById(guestId);
    }
}