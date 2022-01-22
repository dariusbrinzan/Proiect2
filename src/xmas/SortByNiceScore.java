package xmas;

import java.util.Comparator;

public class SortByNiceScore implements Comparator<Child> {

    @Override
    public final int compare(final Child o1,
                             final Child o2) {
        if (o1.getAverageScore() > o2.getAverageScore()) {
            return -1;
        } else if (o1.getAverageScore().equals(o2.getAverageScore())) {
            return o1.compareTo(o2);
        }
        return 1;
    }
}
