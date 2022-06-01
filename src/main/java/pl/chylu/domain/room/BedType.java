package pl.chylu.domain.room;

public enum BedType {
    SINGLE(1),
    DOUBLE(2),
    KING_SIZE(2);

    private int bedSize;
    BedType(final int bedSize) {
        this.bedSize = bedSize;
    }

    public int getBedSize() {
        return bedSize;
    }
}