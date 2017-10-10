package com.safetyculture;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws Exception {

        TrafficLightPair northSouthPair  = new TrafficLightPair("North,South");
        TrafficLightPair eastWestPair    = new TrafficLightPair("East,west");
        TrafficLightPair[] pairs         = new TrafficLightPair[]{northSouthPair, eastWestPair  };
        TrafficIntersection intersection = new TrafficIntersection(pairs);

        intersection.start();
        long time   = System.currentTimeMillis();
        Random rand = new Random();
        while( true ) {
            long newTime = System.currentTimeMillis();
            long millis  = newTime - time;

            String elapsedTime = String.format("%d min, %d sec",
                    TimeUnit.MILLISECONDS.toMinutes(millis),
                    TimeUnit.MILLISECONDS.toSeconds(millis) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
            );
            System.out.println("Elapsed time : " + elapsedTime);
            intersection.printIntersectionState();
            try {
                Thread.sleep(1000 * (rand.nextInt(25) + 1) ); // Print state after random amount seconds
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }

    }
}
