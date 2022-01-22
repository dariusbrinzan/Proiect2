package xmas;

import enums.Category;
import enums.ElvesType;

import java.util.Arrays;

public class ChildUpdate {
    private final Integer id;
    private final Double niceScore;
    private final Category[] giftsPreferences;
    private final ElvesType elf;

    public ChildUpdate(final Integer id,
                       final Double niceScore,
                       final Category[] giftsPreferences,
                       final ElvesType elf) {
        this.id = id;
        this.niceScore = niceScore;
        this.giftsPreferences = giftsPreferences;
        this.elf = elf;
    }

    public final Integer getId() {
        return id;
    }

    public final Double getNiceScore() {
        return niceScore;
    }

    public final Category[] getGiftsPreferences() {
        return giftsPreferences;
    }

    public final ElvesType getElf() {
        return elf;
    }

    @Override
    public final String toString() {
        return "ChildUpdate{"
                + "id=" + id
                + ", niceScore=" + niceScore
                + ", giftsPreferences=" + Arrays.toString(giftsPreferences) + '}';
    }
}
