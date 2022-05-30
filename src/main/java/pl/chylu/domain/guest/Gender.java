package pl.chylu.domain.guest;

public enum Gender {
    MALE("Mężczyzna"),
    FEMALE("Kobieta");

    private String value;

    Gender(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}