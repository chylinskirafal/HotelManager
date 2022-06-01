package pl.chylu.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private final int id;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String firstName;
    private final String lastName;
    private final int numberRoom;
    public static int countDTO;

    public ReservationDTO(int id, LocalDateTime from, LocalDateTime to, String firstName, String lastName, int numberRoom) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberRoom = numberRoom;
        System.out.println("Rezerwacja zaczytana. Nr:" + countDTO);
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumberRoom() {
        return numberRoom;
    }
}
