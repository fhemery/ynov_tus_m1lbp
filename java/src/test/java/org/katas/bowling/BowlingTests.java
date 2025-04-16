package org.katas.bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowlingTests {

    private BowlingScoreManager scoreManager;

    @BeforeEach()
    public void setup() {
        scoreManager = new BowlingScoreManager();
    }

    @Test
    public void shouldReturn0_IfAllThrowsAreMisses() {
        var result = scoreManager.compute("-- -- -- -- -- -- -- -- -- --");

        assertEquals(0, result.score());
    }

    @Test
    public void shouldReturnThrows_IfOneThrowIsNotMissed() {
        var result = scoreManager.compute("-2 -- -- -- -- -- -- -- -- --");

        assertEquals(2, result.score());
    }

    @Test
    public void shouldAddScoreFromEachFrame_WhenNoStrikeNoSpare() {
        var result = scoreManager.compute("-2 -- 31 -- 1- -- -- -- -- --");

        assertEquals(7, result.score());
    }

    @Test
    public void shouldCount10ForASpare() {
        var result = scoreManager.compute("2/ -- -- -- -- -- -- -- -- --");

        assertEquals(10, result.score());
    }

    @Test
    public void shouldDoubleNextThrow_InCaseOfASpare() {
        var result = scoreManager.compute("2/ 4- -- -- -- -- -- -- -- --");

        assertEquals(14 + 4, result.score());
    }

    @Test
    public void shouldCount10ForAStrike() {
        var result = scoreManager.compute("X -- -- -- -- -- -- -- -- --");

        assertEquals(10, result.score());
    }

    @Test
    public void shouldDoubleNextTwoThrowsForAStrike() {
        var result = scoreManager.compute("X 23 -- -- -- -- -- -- -- --");

        assertEquals(15 + 5, result.score());
    }

    @Test
    public void shouldManageSpareAfterStrikeProperly() {
        var result = scoreManager.compute("X 2/ -- -- -- -- -- -- -- --");

        assertEquals(20 + 10, result.score());
    }

    @Test
    public void shouldManageStrikeAfterStrikeProperly() {
        var result = scoreManager.compute("X X -- -- -- -- -- -- -- --");

        assertEquals(20 + 10, result.score());
    }

    @Test
    public void shouldManageStrikeAndOtherAfterStrikeProperly() {
        var result = scoreManager.compute("X X 2- -- -- -- -- -- -- --");

        assertEquals(22 + 12 + 2, result.score());
    }

    @Test
    public void shouldManageStrikeAndOtherAfterStrikeProperly2() {
        var result = scoreManager.compute("X X 25 -- -- -- -- -- -- --");

        assertEquals(22 + 17 + 7, result.score());
    }

    @Test
    public void shouldManageStrikeEverywhere() {
        var result = scoreManager.compute("X X X X X X X X X X X X");

        assertEquals(300, result.score());
    }
}
