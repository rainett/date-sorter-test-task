package com.rainett.datesorter;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;

/**
 * Marking will be based upon producing a readable, well engineered solution rather than factors
 * such as speed of processing or other performance-based optimizations, which are less
 * important.
 * Implement in single class. Don't chane constructor and signature method.
 */
public class DateSorter {

    /**
     * The implementation of this method should sort dates.
     * The output should be in the following order:
     * Dates with an 'r' in the month,
     * sorted ascending (first to last),
     * then dates without an 'r' in the month,
     * sorted descending (last to first).
     * For example, October dates would come before May dates,
     * because October has an 'r' in it.
     * thus: (2004-07-01, 2005-01-02, 2007-01-01, 2032-05-03)
     * would sort to
     * (2005-01-02, 2007-01-01, 2032-05-03, 2004-07-01)
     *
     * @param unsortedDates - an unsorted list of dates
     * @return the collection of dates now sorted as per the spec
     */
    public Collection<LocalDate> sortDates(List<LocalDate> unsortedDates) {
        List<LocalDate> datesWithR = new ArrayList<>();
        List<LocalDate> datesWithoutR = new ArrayList<>();
        filterDates(unsortedDates, datesWithR, datesWithoutR);

        datesWithR.sort(Comparator.naturalOrder());
        datesWithoutR.sort(Comparator.reverseOrder());
        datesWithR.addAll(datesWithoutR);
        return datesWithR;
    }

    private void filterDates(List<LocalDate> unsortedDates,
                             List<LocalDate> datesWithR,
                             List<LocalDate> datesWithoutR) {
        unsortedDates.forEach(date -> {
            if (isRInMonth(date)) {
                datesWithR.add(date);
            } else {
                datesWithoutR.add(date);
            }
        });
    }

    private static boolean isRInMonth(LocalDate date) {
        String monthName = date.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.US);
        return monthName.contains("r");
    }
}
