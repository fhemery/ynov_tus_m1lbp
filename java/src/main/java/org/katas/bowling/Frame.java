package org.katas.bowling;

public class Frame {
    public static final int SPARE_BASE_VALUE = 10;
    public static final int STRIKE_BASE_VALUE = 10;

    BallThrow throw1;
    BallThrow throw2;

    Frame(String frameAsStr) {
        throw1 = new BallThrow(frameAsStr.substring(0, 1));
        if (frameAsStr.length() > 1) {
            throw2 = new BallThrow(frameAsStr.substring(1, 2));
        }
    }

    public int getScore() {
        if (isStrike()) {
            return STRIKE_BASE_VALUE;
        }
        if (isSpare()) {
            return SPARE_BASE_VALUE;
        }

        return throw1.getScore() + throw2.getScore();
    }

    boolean isStrike() {
        return throw1.isStrike();
    }

    public boolean isSpare() {
        return throw2.isSpare();
    }
}
