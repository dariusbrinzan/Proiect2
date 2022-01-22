package xmas;

import enums.Category;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.stream.Stream;

public class Input {
    private final Integer numberOfYears;
    private Double santaBudget;
    private final InitialData initialData;
    private Double budgetUnit;
    private final AnnualChange[] annualChanges;

    public Input(final Integer numberOfYears,
                 final Double santaBudget,
                 final InitialData initialData,
                 final AnnualChange[] annualChanges) {
        this.numberOfYears = numberOfYears;
        this.santaBudget = santaBudget;
        this.initialData = initialData;
        this.annualChanges = annualChanges;
    }

    public final Integer getNumberOfYears() {
        return numberOfYears;
    }

    public final Double getSantaBudget() {
        return santaBudget;
    }

    public final void setSantaBudget(Double santaBudget) {
        this.santaBudget = santaBudget;
    }

    public final InitialData getInitialData() {
        return initialData;
    }

    public final Double getBudgetUnit() {
        return budgetUnit;
    }

    public final void setBudgetUnit(Double budgetUnit) {
        this.budgetUnit = budgetUnit;
    }

    /**
     * Map for gifts
     */
    public final void createMapGiftCategoriesSortedByGiftsPrice() {
        getInitialData().initGiftCategoriesSortedByGiftsPrice(getInitialData().getSantaGiftsList());
    }

    @Override
    public final String toString() {
        return "Input{"
                + "numberOfYears=" + numberOfYears
                + ", santaBudget=" + santaBudget
                + ", initialData=" + initialData + '}';
    }

    private static <T> T[] concatDistinctWithStream(final T[] array1, final T[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2)).distinct()
                .toArray(size ->
                        (T[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }

    private static <T> T[] concatWithStream(final T[] array1, final T[] array2) {
        return Stream.concat(Arrays.stream(array1), Arrays.stream(array2))
                .toArray(size ->
                        (T[]) Array.newInstance(array1.getClass().getComponentType(), size));
    }

    private void calculateBudgetUnit() {
        Double sum = 0.0;
        for (Child child: getInitialData().getChildren()) {
            sum += child.getAverageScore();
        }
        setBudgetUnit(getSantaBudget() / sum);
    }

    private void assignBudgetToChildren() {
        for (Child child: getInitialData().getChildren()) {
            child.setAssignedBudget(getBudgetUnit() * child.getAverageScore());
        }
    }

    private void distributeGifts() {
//        Collections.sort(getInitialData().getChildren());
//        for (Child child: getInitialData().getChildren()) {
//            System.out.print(child.getId());
//            System.out.print(" ");
//        }
//        System.out.println();
//        for (int i = 1; i < getInitialData().getChildren().size(); ++i) {
//            assert getInitialData().getChildren().get(i).getId() >
//            getInitialData().getChildren().get(i - 1).getId();
//            if (getInitialData().getChildren().get(i).getId() <
//            getInitialData().getChildren().get(i - 1).getId()) {
//                System.out.println(
//                "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//                !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//            }
//        }
        for (Child child: getInitialData().getChildren()) {
            boolean isYellowElf = false;
            switch (child.getElf()) {
                case YELLOW:
                    isYellowElf = true;
                    break;
                case BLACK:
                    child.setAssignedBudget(child.getAssignedBudget()
                            - child.getAssignedBudget() * 30.0 / 100.0);
                    break;
                case PINK:
                    child.setAssignedBudget(child.getAssignedBudget()
                            + child.getAssignedBudget() * 30.0 / 100.0);
                    break;
                case WHITE:
                    break;
                default:
                    return;
            }

            boolean hasReceivedGifts = false;
            double childAssignedBudget =  child.getAssignedBudget();
            for (Category giftCategory: child.getGiftsPreferences()) {
                Map<Category, PriorityQueue<Gift>>
                        giftsMap = getInitialData().getGiftCategoriesSortedByGiftsPrice();
                Gift gift = giftsMap.get(giftCategory).peek();
                if (gift != null) {
                    if (childAssignedBudget >= gift.getPrice()) {
                        childAssignedBudget -= gift.getPrice();
                        child.addReceivedGift(gift);
                        hasReceivedGifts = true;
                        gift.decreaseQuantity();
                        if (gift.getQuantity() == 0) {
                            giftsMap.get(giftCategory).poll();
                        }
                    }
                }
            }

            if (isYellowElf) {
                if (!hasReceivedGifts) {
                    Map<Category, PriorityQueue<Gift>>
                            giftsMap = getInitialData().getGiftCategoriesSortedByGiftsPrice();
                    Gift gift = giftsMap.get(child.getGiftsPreferences()[0]).peek();
                    if (gift != null) {
                        child.addReceivedGift(gift);
                        gift.decreaseQuantity();
                        if (gift.getQuantity() == 0) {
                            giftsMap.get(child.getGiftsPreferences()[0]).poll();
                        }
                    }
                }
            }
        }
    }

    private void updateChildren(final AnnualChange annualChange) {
        for (ChildUpdate childUpdate: annualChange.getChildrenUpdates()) {
            for (Child child: getInitialData().getChildren()) {
                if (childUpdate.getId().equals(child.getId())) {
                    if (childUpdate.getNiceScore() != null) {
                        child.addNiceScoreToHistory(childUpdate.getNiceScore());
                        child.setNiceScore(childUpdate.getNiceScore());
                    }
                    if (childUpdate.getElf() != null) {
                        child.setElf(childUpdate.getElf());
                    }
                    child.setGiftsPreferences(concatDistinctWithStream(
                            childUpdate.getGiftsPreferences(), child.getGiftsPreferences()));
                }
            }
        }
    }

    private void applyTheAnnualChange(final AnnualChange annualChange) {
        getInitialData().incrementChildrenAge();
        getInitialData().removeAdultsChildrenFromChildrenList();
        setSantaBudget(annualChange.getNewSantaBudget());
        getInitialData().setSantaGiftsList(
                concatWithStream(getInitialData().getSantaGiftsList(), annualChange.getNewGifts()));
        getInitialData().addGiftsToEachCategoryInMap(annualChange.getNewGifts());
        getInitialData().getChildren().addAll(Arrays.asList(annualChange.getNewChildren()));
        getInitialData().initChildrenNiceScoreHistory();
        getInitialData().removeAdultsChildrenFromChildrenList();
        updateChildren(annualChange);
        getInitialData().setAverageScoreForEachChild();
        getInitialData().sortChildrenByStrategy(annualChange.getStrategy());
    }

    private void initRound() {
        getInitialData().resetChildrenReceivedGifts();
        calculateBudgetUnit();
        assignBudgetToChildren();
        distributeGifts();
        Collections.sort(getInitialData().getChildren()); // by id
    }

    /**
     * Start of a new simulation, new years
     * @return annualChildren
     * @throws Exception
     */
    public final AnnualChildren startSimulation() throws Exception {
        AnnualChildren annualChildren = new AnnualChildren(numberOfYears);
        getInitialData().removeAdultsChildrenFromChildrenList();
        getInitialData().initChildrenNiceScoreHistory();
        getInitialData().setAverageScoreForEachChild();
        initRound();
        annualChildren.addChildren(0,
                (ArrayList<Child>) ObjectCloner.deepCopy(getInitialData().getChildren()));
        for (int i = 0; i < numberOfYears; ++i) {
            applyTheAnnualChange(annualChanges[i]);
            initRound();
            annualChildren.addChildren(i + 1,
                    (ArrayList<Child>) ObjectCloner.deepCopy(getInitialData().getChildren()));
        }
        return annualChildren;
    }
}
