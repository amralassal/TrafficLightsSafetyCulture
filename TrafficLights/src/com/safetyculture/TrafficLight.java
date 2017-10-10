package com.safetyculture;

// Traffic light has 3 different colors and saves its own color state
class TrafficLight {

    private TrafficLightColor trafficLightColor;

    public TrafficLight() {
        this.trafficLightColor = TrafficLightColor.RED;
    }

    public TrafficLight(TrafficLightColor color) {
        this.trafficLightColor = color;
    }

    public TrafficLightColor getTrafficLightColor() {
        return trafficLightColor;
    }
    public void setTrafficLightColor(TrafficLightColor color) {
        this.trafficLightColor = color;
    }
}
