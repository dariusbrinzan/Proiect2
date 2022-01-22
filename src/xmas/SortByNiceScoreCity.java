package xmas;

import enums.Cities;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SortByNiceScoreCity implements Comparator<Child> {

    private Map<Cities, Double> averageScoreCity;

    SortByNiceScoreCity(final List<Child> children) {
        averageScoreCity = new HashMap<>();
        for (Cities city: Cities.values()) {
            averageScoreCity.put(city, 0.0);
        }
        Map<Cities, Integer> numberOfChildrenInCity = new HashMap<>();
        for (Cities city: Cities.values()) {
            numberOfChildrenInCity.put(city, 0);
        }
        for (Child child: children) {
            averageScoreCity.put(child.getCity(),
                    averageScoreCity.get(child.getCity()) + child.getAverageScore());
            numberOfChildrenInCity.put(child.getCity(),
                    numberOfChildrenInCity.get(child.getCity()) + 1);
        }
        for (Cities city: Cities.values()) {
            if (numberOfChildrenInCity.get(city) > 0) {
                averageScoreCity.put(city,
                        averageScoreCity.get(city) / numberOfChildrenInCity.get(city));
            }
        }
    }

    @Override
    public final int compare(final Child o1,
                             final Child o2) {
        if (averageScoreCity.get(o1.getCity()) > averageScoreCity.get(o2.getCity())) {
            return -1;
        } else if (averageScoreCity.get(o1.getCity()).equals(averageScoreCity.get(o2.getCity()))) {
            if (o1.getCity().toString().compareTo(o2.getCity().toString()) != 0) {
                return o1.getCity().toString().compareTo(o2.getCity().toString());
            } else {
                return o1.compareTo(o2);
            }
        }
        return 1;
    }
}
