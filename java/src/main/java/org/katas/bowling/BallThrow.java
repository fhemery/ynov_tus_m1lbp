package org.katas.bowling;

import java.util.Objects;

public record BallThrow(String ballThrow) {
    public static final String SPARE_SYMBOL = "/";
    private static final String STRIKE_SYMBOL = "X";

    int getScore() {
        if (Objects.equals(ballThrow, "-")) {
            return 0;
        }
        return Integer.parseInt(String.valueOf(ballThrow));
    }

    public boolean isSpare() {
        return ballThrow.contains(SPARE_SYMBOL);
    }

    public boolean isStrike() {

        return ballThrow.contains(STRIKE_SYMBOL);
    }
}
