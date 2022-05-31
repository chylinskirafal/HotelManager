package pl.chylu.domain.room;

public enum BedType {
    SINGLE("Pojedyńcze"),
    DOUBLE("Podwójne"),
    KING_SIZE("Małżeńskie");

    private String value;
    BedType(final String value) {
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