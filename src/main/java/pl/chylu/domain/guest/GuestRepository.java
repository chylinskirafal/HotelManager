package pl.chylu.domain.guest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public interface GuestRepository {
    Guest createNewGuest(String firstName, String lastName, int age, Gender gender);

    List<Guest> getAll();

    void saveAll();

    void readAll();

    void remove(long id);

    void edit(long id, String firstName, String lastName, int age, Gender gender);

    Guest findById(long id);
}