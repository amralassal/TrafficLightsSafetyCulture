package com.safetyculture;

// Traffic light colors as enums
public enum TrafficLightColor {
    RED("Red"), GREEN("Green"), YELLOW("Yellow"), NONE("None");

    private String color;
    TrafficLightColor(String color) {
        this.color = color;
    }

    // Return string of the color
    @Override
    public String toString() {
        return color;
    }
}
