package pl.chylu.domain.reservation.dto;

import java.time.LocalDateTime;

public class ReservationDTO {
    private int id;
    private LocalDateTime from;
    private LocalDateTime to;
    private int guestID;
    private String firstName;
    private String lastName;
    private int roomID;
    private int numberRoom;

    public ReservationDTO(int id, LocalDateTime from, LocalDateTime to, int guestID, String firstName, String lastName, int roomID, int numberRoom) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.guestID = guestID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roomID = roomID;
        this.numberRoom = numberRoom;
    }

    public int getId() {
        return id;
    }
    public int getRoomID() {
        return roomID;
    }
    public int getGuestID() {
        return guestID;
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
