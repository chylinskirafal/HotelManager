package pl.chylu.domain.room;

public enum BedType {
    SINGLE("Pojedyńcze", 1),
    DOUBLE("Podwójne", 2),
    KING_SIZE("Małżeńskie", 2);

    private int bedSize;
    private String value;
    BedType(final String value, int bedSize) {
        this.value = value;
        this.bedSize = bedSize;
    }

    public int getBedSize() {
        return bedSize;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.getValue();
    }
}