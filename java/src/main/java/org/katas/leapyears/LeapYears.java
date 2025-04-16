package org.katas.leapyears;

public class LeapYears {
    public boolean isLeap(int year) {
        if (isDivisibleBy(year, 100)) {
            return isDivisibleBy(year, 400);
        }
        return isDivisibleBy(year, 4);
    }

    private boolean isDivisibleBy(int year, int divider) {
        return year % divider == 0;
    }
}
