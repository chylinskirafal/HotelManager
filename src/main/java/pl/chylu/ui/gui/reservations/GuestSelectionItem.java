package pl.chylu.ui.gui.reservations;

public class GuestSelectionItem {
    private int id;
    private String firstName;
    private String lastName;

    public GuestSelectionItem(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        return String.format("%s %s",this.firstName, this.lastName);
    }
}
