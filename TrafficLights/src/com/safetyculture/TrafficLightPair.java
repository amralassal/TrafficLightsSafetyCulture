package com.safetyculture;

// Assume that each pair has 2 traffic light for it as usually streets have 2 ways
public class TrafficLightPair {
    private TrafficLight trafficLightNorth;
    private TrafficLight trafficLightSouth;
    private String name; // Name to make the traffic light distinguishable

    public TrafficLightPair(String name) {
        this.trafficLightNorth = new TrafficLight();
        this.trafficLightSouth = new TrafficLight();
        this.name              = name;
    }

    public TrafficLightPair() {
        this.trafficLightNorth = new TrafficLight();
        this.trafficLightSouth = new TrafficLight();
        this.name              = "Temp";
    }

    public void setTrafficLightPairColor(TrafficLightColor color) {
        this.trafficLightNorth.setTrafficLightColor(color);
        this.trafficLightSouth.setTrafficLightColor(color);
    }

    public TrafficLightColor getTrafficLightColor() {
        return this.trafficLightNorth.getTrafficLightColor();
    }

    @Override
    public String toString() {
        return this.name + " is " + this.trafficLightNorth.getTrafficLightColor().toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
