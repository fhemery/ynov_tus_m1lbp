package org.katas.bowling;

import java.util.Arrays;
import java.util.List;

public class BowlingSession {
    public static final int NB_FRAMES = 10;
    List<Frame> frames;

    BowlingSession(String sessionAsStr) {
        frames = Arrays.stream(sessionAsStr.split(" ")).map(Frame::new).toList();
    }

    int getScore() {
        int result = 0;
        for (int i = 0; i < NB_FRAMES; ++i) {
            Frame currentFrame = frames.get(i);
            if (currentFrame.isStrike()) {
                result = computeStrike(i, result);
            }
            else if (currentFrame.isSpare()) {
                result = computeSpare(i, result);
            }
            result += currentFrame.getScore();
        }

        return result;
    }

    private int computeSpare(int i, int result) {
        Frame nextFrame = frames.get(i +1);
        result += nextFrame.throw1.getScore();
        return result;
    }

    private int computeStrike(int i, int result) {
        Frame nextFrame = frames.get(i +1);
        if (nextFrame.isStrike()) {
            result += Frame.STRIKE_BASE_VALUE;
            Frame nextnextFrame = frames.get(i+2);
            if (nextnextFrame.isStrike()) {
                result += Frame.STRIKE_BASE_VALUE;
            } else {
                result += nextnextFrame.throw1.getScore();
            }
        }
        else if (nextFrame.isSpare()) {
            result += Frame.SPARE_BASE_VALUE;
        } else {
            result += nextFrame.throw1.getScore();
            result += nextFrame.throw2.getScore();
        }
        return result;
    }
}
