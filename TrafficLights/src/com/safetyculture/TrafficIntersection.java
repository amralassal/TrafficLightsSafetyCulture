package com.safetyculture;

import java.util.ArrayDeque;
import java.util.Queue;

// Traffic intersection, it could have two or more pairs
public class TrafficIntersection extends Thread{

    private Queue<TrafficLightPair> intersection; // Queue to include traffic lights pairs, so we make sure they rotate in order
    private TrafficLightPair mainPair; // The main pair which is green right now
    private boolean intersectionIsWorking; // Intersection state

    public TrafficIntersection(TrafficLightPair[] pairs ) throws Exception {

        if(pairs.length == 0) {
            throw new Exception("Please provide at least 1 pair of traffic lights");
        }

        this.intersection          = new ArrayDeque<>();
        this.intersectionIsWorking = true;
        for (int i = 0; i < pairs.length; i++ ) {
            this.intersection.add( pairs[i] );
        }
    }

    public void run() {

        while (intersectionIsWorking) { // Check if intersection state is already working

            this.mainPair = intersection.poll(); // Poll first traffic light pair
            mainPair.setTrafficLightPairColor(TrafficLightColor.GREEN); // Change its state to be green
            intersection.add(mainPair);

            try {
                Thread.sleep( 1000 * 270); // Wait for 4.5 minutes
            } catch (InterruptedException ex) {
                killAllTrafficLights(); // If any error happen, kill all traffic lights
                Thread.currentThread().interrupt();
            }

            if (intersectionIsWorking) {
                this.mainPair.setTrafficLightPairColor(TrafficLightColor.YELLOW); // Change main pair to yellow
            }

            try {
                Thread.sleep(1000 *30); // Wait for 30 seconds
            } catch (InterruptedException ex) {
                killAllTrafficLights();
                Thread.currentThread().interrupt();
            }

            if (intersectionIsWorking) {
                this.mainPair.setTrafficLightPairColor(TrafficLightColor.RED); // Change main pair to red
            }
        }
    }

    // Stop the intersection from working
    public void stopIntersectionFromWorking() {
        this.intersectionIsWorking = false;
        this.killAllTrafficLights();
    }

    // Make sure no two pairs are green at the same time
    public boolean isAllTrafficLightsExceptMainAreRed() {
        for(TrafficLightPair pair : intersection) {
            if (this.mainPair != pair && pair.getTrafficLightColor() != TrafficLightColor.RED) {
                return false;
            }
        }
        return true;
    }

    // Make all pairs red
    private void killAllTrafficLights() {
        for(TrafficLightPair pair : intersection) {
            pair.setTrafficLightPairColor(TrafficLightColor.RED);
        }
    }

    public String getMainTrafficLightName() {
        return this.mainPair.getName();
    }

    public boolean getIntersectionIsWorking() {
        return intersectionIsWorking;
    }

    public void printIntersectionState() {

        if (intersectionIsWorking) {
            System.out.println( "Intersection is working" );
            for(TrafficLightPair pair : intersection) {
                System.out.println(pair.toString());
            }
        } else {
            System.out.println( "Intersection is not working" );
        }
    }
}
