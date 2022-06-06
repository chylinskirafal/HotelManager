package pl.chylu.domain.guest;

import pl.chylu.util.SystemUtils;

public enum Gender {
    MALE(SystemUtils.MALE),
    FEMALE(SystemUtils.FEMALE);

    private String asStr;

    Gender(String asStr) {
        this.asStr = asStr;
    }

    @Override
    public String toString() {
        return this.asStr;
    }
}
