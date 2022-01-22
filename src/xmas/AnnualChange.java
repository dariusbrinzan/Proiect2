package xmas;

import enums.CityStrategyEnum;

import java.util.Arrays;

public class AnnualChange {
    private final Double newSantaBudget;
    private final Gift[] newGifts;
    private final Child[] newChildren;
    private final ChildUpdate[] childrenUpdates;
    private final CityStrategyEnum strategy;

    public AnnualChange(final Double newSantaBudget,
                        final Gift[] newGifts,
                        final Child[] newChildren,
                        final ChildUpdate[] childrenUpdates,
                        final CityStrategyEnum strategy) {
        this.newSantaBudget = newSantaBudget;
        this.newGifts = newGifts;
        this.newChildren = newChildren;
        this.childrenUpdates = childrenUpdates;
        this.strategy = strategy;
    }

    public final Double getNewSantaBudget() {
        return newSantaBudget;
    }

    public final Gift[] getNewGifts() {
        return newGifts;
    }

    public final Child[] getNewChildren() {
        return newChildren;
    }

    public final ChildUpdate[] getChildrenUpdates() {
        return childrenUpdates;
    }

    public final CityStrategyEnum getStrategy() {
        return strategy;
    }


    @Override
    public final String toString() {
        return "AnnualChange{" + "newSantaBudget=" + newSantaBudget
                + ", newGifts=" + Arrays.toString(newGifts) + ", newChildren="
                + Arrays.toString(newChildren) + ", childrenUpdates="
                + Arrays.toString(childrenUpdates) + '}';
    }
}
