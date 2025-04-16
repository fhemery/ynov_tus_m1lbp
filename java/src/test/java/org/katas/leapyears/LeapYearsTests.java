package org.katas.leapyears;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearsTests {

    private LeapYears leapYears;

    @BeforeEach
    public void setup() {
        leapYears = new LeapYears();
    }

    @Test
    public void shouldReturnTrue_whenYearIsDivisibleBy400() {
        assertTrue(leapYears.isLeap(2000));
    }

    @Test
    public void shouldReturnFalse_whenYearIsDivisibleBy100ButNot400() {
        assertFalse(leapYears.isLeap(1900));
    }

    @Test
    public void shouldReturnTrue_whenYearIsDivisibleBy4() {
        assertTrue(leapYears.isLeap(1904));
    }

    @Test
    public void shouldReturnFalse_whenYearIsNotDivisibleBy4() {
        assertFalse(leapYears.isLeap(1903));
    }
}
