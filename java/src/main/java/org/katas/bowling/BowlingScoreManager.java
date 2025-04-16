package org.katas.bowling;

public class BowlingScoreManager {
    public ScoreDetails compute(String session) {
        int total =  new BowlingSession(session).getScore();

        return new ScoreDetails(total);
    }
}
