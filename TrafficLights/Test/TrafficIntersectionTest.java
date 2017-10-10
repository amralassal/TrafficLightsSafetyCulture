import com.safetyculture.TrafficIntersection;
import com.safetyculture.TrafficLightColor;
import com.safetyculture.TrafficLightPair;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TrafficIntersectionTest {

    @org.junit.Test
    public void isAllTrafficLightsExceptMainAreRed() throws Exception {
        TrafficLightPair northSouthPair  = new TrafficLightPair("North,South");
        TrafficLightPair eastWestPair    = new TrafficLightPair("East,west");
        TrafficLightPair[] pairs         = new TrafficLightPair[]{northSouthPair, eastWestPair};
        TrafficIntersection intersection = new TrafficIntersection(pairs);

        intersection.start();
        try {
            Thread.sleep(1000 * 5 ); // Print state after 5 seconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        assert( intersection.isAllTrafficLightsExceptMainAreRed() );
    }

    @org.junit.Test
    public void stopIntersectionFromWorking() throws Exception {
        TrafficLightPair northSouthPair  = new TrafficLightPair("North,South");
        TrafficLightPair eastWestPair    = new TrafficLightPair("East,west");
        TrafficLightPair[] pairs         = new TrafficLightPair[]{northSouthPair, eastWestPair  };
        TrafficIntersection intersection = new TrafficIntersection(pairs);
        intersection.start();

        try {
            Thread.sleep(1000 * 5 ); // Print state after 5 seconds
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        intersection.stopIntersectionFromWorking();
        assertFalse(intersection.getIntersectionIsWorking());
        assertEquals(northSouthPair.getTrafficLightColor(), TrafficLightColor.RED);
        assertEquals(eastWestPair.getTrafficLightColor(), TrafficLightColor.RED);
    }

    @org.junit.Test
    public void runAfter5minCycle() throws Exception { // Testing run function for 5 mins cycle\
        TrafficLightPair northSouthPair  = new TrafficLightPair("North,South");
        TrafficLightPair eastWestPair    = new TrafficLightPair("East,west");
        TrafficLightPair[] pairs         = new TrafficLightPair[]{northSouthPair, eastWestPair};
        TrafficIntersection intersection = new TrafficIntersection(pairs);

        intersection.start();
        try {
            Thread.sleep(1000 * 20 ); // Print state after 20 seconds
            String name = intersection.getMainTrafficLightName();

            assert(intersection.getIntersectionIsWorking());
            assertEquals(name, northSouthPair.getName());
            assertEquals(northSouthPair.getTrafficLightColor(), TrafficLightColor.GREEN);
            assertEquals(eastWestPair.getTrafficLightColor(), TrafficLightColor.RED);
            assert(intersection.isAllTrafficLightsExceptMainAreRed());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        try {
            Thread.sleep(1000 * 270 ); // Print state after 4 min 30 seconds
            String name = intersection.getMainTrafficLightName();

            assert(intersection.getIntersectionIsWorking());
            assertEquals(name, northSouthPair.getName());
            assertEquals(northSouthPair.getTrafficLightColor(), TrafficLightColor.YELLOW);
            assertEquals(eastWestPair.getTrafficLightColor(), TrafficLightColor.RED);
            assert(intersection.isAllTrafficLightsExceptMainAreRed());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        try {
            Thread.sleep(1000 * 11 ); //Print state 11 seconds
            String name = intersection.getMainTrafficLightName();

            assert(intersection.getIntersectionIsWorking());
            assertEquals(name, eastWestPair.getName());
            assertEquals(northSouthPair.getTrafficLightColor(), TrafficLightColor.RED);
            assertEquals(eastWestPair.getTrafficLightColor(), TrafficLightColor.GREEN);
            assert(intersection.isAllTrafficLightsExceptMainAreRed());
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

}