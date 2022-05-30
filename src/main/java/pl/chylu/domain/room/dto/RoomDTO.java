package pl.chylu.domain.room.dto;

public class RoomDTO {
    private final int id;
    private final int number;
    private final String beds;

    public RoomDTO(int id, int number, String beds) {
        this.id = id;
        this.number = number;
        this.beds = beds;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public String getBeds() {
        return beds;
    }
}
